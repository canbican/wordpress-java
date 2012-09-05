package net.bican.wordpress.test;

import static org.junit.Assert.*;

import java.util.List;

import net.bican.wordpress.Comment;
import net.bican.wordpress.Page;

import org.junit.Test;

@SuppressWarnings({ "static-method", "javadoc", "nls", "boxing" })
public class CommentTest extends AbstractWordpressTest {

  @Test
  public void testComments() throws Exception {
    List<Comment> comments = WP.getComments(null, null, null, null);
    if (comments != null) {
      for (Comment comment : comments) {
        WP.deleteComment(comment.getComment_id());
      }
    }
    comments = WP.getComments(null, null, null, null);
    assertEquals(0, comments.size());
    Page page = new Page();
    page.setDescription("deneme");
    String pg = WP.newPost(page, true);
    Integer commentid = WP.newComment(Integer.valueOf(pg), null,
        "deneme comment", "Can Bican", "http://a.b.c.com", "a@b.c.d");
    assertNotNull(commentid);
    Comment newComment = WP.getComment(commentid);
    assertEquals("deneme comment", newComment.getContent());
    Integer comment_id = newComment.getComment_id();
    WP.deleteComment(comment_id);
    comments = WP.getComments(null, null, null, null);
    Comment found = null;
    if (comments != null)
      for (Comment comment : comments) {
        if (comment.getComment_id().equals(comment_id)) {
          found = comment;
          break;
        }
      }
    assertNull(found);
  }
}
