/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright 2012-2015 Can Bican
 * <can@bican.net> See the file 'COPYING' in the distribution for licensing terms.
 */
package net.bican.wordpress.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import net.bican.wordpress.FilterPost;
import net.bican.wordpress.MediaItem;
import net.bican.wordpress.MediaItemUploadResult;
import net.bican.wordpress.Post;
import net.bican.wordpress.PostType;
import net.bican.wordpress.Taxonomy;
import net.bican.wordpress.Term;
import net.bican.wordpress.exceptions.FileUploadException;
import net.bican.wordpress.exceptions.InsufficientRightsException;
import net.bican.wordpress.exceptions.InvalidArgumentsException;
import net.bican.wordpress.exceptions.ObjectNotFoundException;
import redstone.xmlrpc.XmlRpcFault;

@SuppressWarnings({"static-method", "javadoc", "nls"})
public class PostTest extends AbstractWordpressTest {
  private static final String TEST_IMAGE = "test.jpg";
  private static final Post post = new Post();

  @BeforeClass
  public static void setup() {
    post.setPost_name("test post name");
    post.setPost_content("test content");
    post.setPost_title("test post title");
    post.setPost_excerpt("test post excerpt");
  }

  @Test
  public void testPostThumbnailNew() throws InsufficientRightsException, FileUploadException,
      XmlRpcFault, IOException, ObjectNotFoundException, InvalidArgumentsException {
    try (InputStream media = new FileInputStream(new File("test/" + TEST_IMAGE))) {
      final String fileName = TEST_IMAGE;
      final MediaItemUploadResult mediaUploaded = WP.uploadFile(media, fileName);
      final MediaItem r = WP.getMediaItem(mediaUploaded.getId());
      post.setPost_thumbnail(r);
      Integer p = WP.newPost(post);
      Post post2 = WP.getPost(p);
      assertNotNull(post2);
      assertNotNull(post2.getPost_thumbnail());
      WP.deletePost(p);
    }
  }

  @Test
  public void testPostThumbnailEdit() throws InsufficientRightsException, FileUploadException,
      XmlRpcFault, IOException, ObjectNotFoundException, InvalidArgumentsException {
    try (InputStream media = new FileInputStream(new File("test/" + TEST_IMAGE))) {
      final String fileName = TEST_IMAGE;
      final MediaItemUploadResult mediaUploaded = WP.uploadFile(media, fileName);
      final MediaItem r = WP.getMediaItem(mediaUploaded.getId());
      post.setPost_thumbnail(null);
      Integer p = WP.newPost(post);
      Post post2 = WP.getPost(p);
      assertNull(post2.getPost_thumbnail());
      post2.setPost_thumbnail(r);
      assertTrue(WP.editPost(p, post2));
      Post post3 = WP.getPost(p);
      assertNotNull(post3.getPost_thumbnail());
      assertEquals(post3.getPost_thumbnail().getAttachment_id(), r.getAttachment_id());
      WP.deletePost(p);
    }
  }

  @Test
  public void testPostTaxonomy() throws InsufficientRightsException, InvalidArgumentsException,
      ObjectNotFoundException, XmlRpcFault {
    Taxonomy taxonomy =
        WP.getTaxonomies().stream().filter(p -> p.getName().equals("category")).findFirst().get();
    Term term = WP.getTerms(taxonomy.getName()).get(0);
    post.setTerms(Arrays.asList(term));
    Integer p = WP.newPost(post);
    assertNotNull(p);
    Post post2 = WP.getPost(p);
    assertNotNull(post2);
    assertNotNull(post2.getTerms());
    assertEquals(1, post2.getTerms().size());
    assertEquals(term, post2.getTerms().get(0));
    WP.deletePost(p);
  }

  @Test
  public void getPostType() throws Exception {
    final PostType r = WP.getPostType("post");
    assertNotNull(r);
    assertEquals(r.getCap().getDelete_posts(), "delete_posts");
    assertEquals(r.getLabel(), "Posts");
    assertEquals(r.getLabels().getAdd_new_item(), "Add New Post");
    assertEquals(r.getMenu_icon(), null);
    assertEquals(r.getMenu_position(), 0);
    assertEquals(r.getName(), "post");
    assertTrue(r.getSupports().isCustomFields().booleanValue());
    assertEquals(r.getTaxonomies().size(), 3);
  }

  @Test
  public void testEditPost() throws Exception {
    final Integer p = WP.newPost(post);
    final Post editPost = new Post();
    editPost.setPost_content("edited");
    assertTrue(WP.editPost(p, editPost));
    final Post ep = WP.getPost(p);
    assertNotNull(ep);
    assertEquals("edited", ep.getPost_content());
    assertEquals("test post title", ep.getPost_title());
    WP.deletePost(p);
  }

  @Test
  public void testGetNewDeletePost() throws Exception {
    final Integer p = WP.newPost(post);
    final Post newPost = WP.getPost(p);
    assertNotNull(newPost);
    assertEquals(newPost.getPost_id(), p);
    assertEquals(newPost.getComment_status(), "closed");
    assertEquals(newPost.getCustom_fields(), null);
    assertEquals(newPost.getEnclosure(), null);
    assertNotNull(newPost.getGuid());
    assertNotNull(newPost.getLink());
    assertEquals(newPost.getMenu_order().intValue(), 0);
    assertEquals(newPost.getPing_status(), "open");
    assertEquals(newPost.getPost_author().intValue(), 1);
    assertEquals(newPost.getPost_content(), post.getPost_content());
    assertNotNull(newPost.getPost_date());
    assertNotNull(newPost.getPost_date_gmt());
    assertEquals(newPost.getPost_excerpt(), post.getPost_excerpt());
    assertEquals(newPost.getPost_format(), "standard");
    assertEquals(newPost.getPost_mime_type(), "");
    assertNotNull(newPost.getPost_modified());
    assertNotNull(newPost.getPost_modified_gmt());
    assertEquals(newPost.getPost_name(), post.getPost_name().replaceAll(" ", "-"));
    assertEquals(newPost.getPost_parent().intValue(), 0);
    assertEquals(newPost.getPost_password(), "");
    assertEquals(newPost.getPost_status(), "draft");
    assertEquals(newPost.getPost_thumbnail(), null);
    assertEquals(newPost.getPost_title(), post.getPost_title());
    assertEquals(newPost.getPost_type(), "post");
    assertFalse(newPost.isSticky().booleanValue());
    assertNotNull(newPost.getTerms());
    assertNotNull(newPost.getTerms().get(0));
    final Term term = newPost.getTerms().get(0);
    assertEquals(term.getFilter(), "raw");
    WP.deletePost(p);
    final Post pp = WP.getPost(p);
    assertEquals(pp.getPost_status(), "trash");
  }

  @Test
  public void testGetPostFormats() throws Exception {
    final Map<String, String> pf = WP.getPostFormats();
    assertNotNull(pf);
    assertTrue(pf.containsKey("standard"));
    assertNotNull(pf.get("standard"));
    final Map<String, String> pf2 = WP.getPostFormats(true);
    assertNotNull(pf2);
    assertFalse(pf2.containsKey("standard"));
    assertTrue(pf2.containsKey("link"));
    assertNotNull(pf2.get("link"));
  }

  @Test
  public void testGetPostStatusList() throws Exception {
    final Map<String, String> r = WP.getPostStatusList();
    assertNotNull(r);
    assertTrue(r.containsKey("private"));
    assertNotNull(r.get("private"));
  }

  @Test
  public void testGetPostTypes() throws Exception {
    final List<PostType> r = WP.getPostTypes();
    assertNotNull(r);
    assertEquals(3, r.size());
    PostType r1 = null;
    for (final PostType s : r) {
      if (s.getName().equals("post")) {
        r1 = s;
        break;
      }
    }
    assertNotNull(r1);
  }

  @Test
  public void testGetPostTypesWithFilter() throws Exception {
    final Map<String, Object> filter = new HashMap<>();
    filter.put("hierarchical", Boolean.TRUE);
    final List<PostType> r = WP.getPostTypes(filter);
    assertNotNull(r);
    assertEquals(1, r.size());
  }

  @Test
  public void testPosts() throws Exception {
    final List<Post> r = WP.getPosts();
    assertNotNull(r);
    assertTrue(r.size() > 0);
  }

  @Test
  public void testPostsWithFilter() throws Exception {
    final Integer p = WP.newPost(post);
    final List<Post> r = WP.getPosts();
    final FilterPost filter = new FilterPost();
    final Integer number = Integer.valueOf(r.size() - 1);
    filter.setNumber(number);
    final List<Post> rf = WP.getPosts(filter);
    assertNotNull(rf);
    assertEquals(number.intValue(), rf.size());
    WP.deletePost(p);
  }

  @SuppressWarnings("unused")
  @Test
  public void testSetCategory() throws Exception {
    final List<Term> terms = WP.getTerms("category");
    final Term term = Term.builder().name("test category1").taxonomy("category").build();
    Integer termId = null;
    try {
      termId = WP.newTerm(term);
    } catch (final Exception e) {
      for (final Term t : terms) {
        if (t.getName().equals("test category1")) {
          termId = t.getTerm_id();
          break;
        }
      }
    }
    term.setName("test category2");
    term.setTaxonomy("category");
    Integer termId2 = null;
    try {
      termId2 = WP.newTerm(term);
    } catch (final Exception e) {
      for (final Term t : terms) {
        if (t.getName().equals("test category2")) {
          termId2 = t.getTerm_id();
          break;
        }
      }
    }
    final Term term1 = WP.getTerm("category", termId);
    final Term term2 = WP.getTerm("category", termId2);
    Post pp = new Post();
    pp.setPost_title("test");
    pp.setPost_excerpt("test test");
    pp.setTerms(Arrays.asList(new Term[] {term1, term2}));
    final Integer pId = WP.newPost(pp);
    pp = WP.getPost(pId);
    assertNotNull(pp);
    assertNotNull(pp.getTerms());
    assertEquals(2, pp.getTerms().size());
    assertEquals(termId, pp.getTerms().get(0).getTerm_id());
    assertEquals(termId2, pp.getTerms().get(1).getTerm_id());
    WP.deletePost(pId);
    WP.deleteTerm("category", termId);
    WP.deleteTerm("category", termId2);
  }

  // public void testPostWithThumbnail() {
  // List<MediaItem> post_thumbnail = new ArrayList<>();
  // MediaItem thumbnail = new MediaItem();
  // thumbnail.setAttachment_id(attachment_id);
  // thumbnail.setCaption("test caption");
  // thumbnail.setDescription("test description");
  // thumbnail.set
  // post.setPost_thumbnail(post_thumbnail);
  // }
}
