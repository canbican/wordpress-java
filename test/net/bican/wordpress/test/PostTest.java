/*
 * 
 * Wordpress-java
 * https://github.com/canbican/wordpress-java/
 * 
 * Copyright 2012-2015 Can Bican <can@bican.net>
 * See the file 'COPYING' in the distribution for licensing terms.
 * 
 */
package net.bican.wordpress.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import net.bican.wordpress.FilterPost;
import net.bican.wordpress.Post;
import net.bican.wordpress.PostType;
import net.bican.wordpress.Term;

@SuppressWarnings({ "static-method", "javadoc", "nls" })
public class PostTest extends AbstractWordpressTest {
  
  private static final Post post = new Post();
  
  @BeforeClass
  public static void setup() {
    post.setPost_name("test post name");
    post.setPost_content("test content");
    post.setPost_title("test post title");
    post.setPost_excerpt("test post excerpt");
  }
  
  @Test
  public void testGetPostTypesWithFilter() throws Exception {
    Map<String, Object> filter = new HashMap<>();
    filter.put("hierarchical", Boolean.TRUE);
    List<PostType> r = WP.getPostTypes(filter);
    assertNotNull(r);
    assertEquals(1, r.size());
  }
  
  @Test
  public void testGetPostTypes() throws Exception {
    List<PostType> r = WP.getPostTypes();
    assertNotNull(r);
    assertEquals(3, r.size());
    PostType r1 = null;
    for (PostType s : r) {
      if (s.getName().equals("post")) {
        r1 = s;
        break;
      }
    }
    assertNotNull(r1);
  }
  
  @Test
  public void getPostType() throws Exception {
    PostType r = WP.getPostType("post");
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
    assertEquals(newPost.getCustomFields(), null);
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
    assertEquals(newPost.getPost_name(),
        post.getPost_name().replaceAll(" ", "-"));
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
  public void testPosts() throws Exception {
    List<Post> r = WP.getPosts();
    assertNotNull(r);
    assertTrue(r.size() > 0);
  }
  
  @Test
  public void testPostsWithFilter() throws Exception {
    final Integer p = WP.newPost(post);
    List<Post> r = WP.getPosts();
    FilterPost filter = new FilterPost();
    Integer number = Integer.valueOf(r.size() - 1);
    filter.setNumber(number);
    List<Post> rf = WP.getPosts(filter);
    assertNotNull(rf);
    assertEquals(number.intValue(),rf.size());
    WP.deletePost(p);
  }
}
