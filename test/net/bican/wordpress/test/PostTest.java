package net.bican.wordpress.test;

import static org.junit.Assert.*;

import java.util.List;

import net.bican.wordpress.Page;

import org.junit.Test;

@SuppressWarnings({ "static-method", "javadoc", "nls" })
public class PostTest extends AbstractWordpressTest {

  @Test
  public void testPost() throws Exception {
    List<Page> posts = WP.getRecentPosts(100000);
    for (Page post : posts) {
      WP.deletePost(post.getPostid().intValue(), "true");
    }
    posts = WP.getRecentPosts(100000);
    assertNotNull(posts);
    assertEquals(0, posts.size());
    Page post = new Page();
    post.setDescription("denemepost");
    String newPostIds = WP.newPost(post, true);
    int newPostId = Integer.valueOf(newPostIds).intValue();
    Page postNow = WP.getPost(newPostId);
    assertNotNull(postNow);
    assertEquals("denemepost", postNow.getDescription());
  }
}
