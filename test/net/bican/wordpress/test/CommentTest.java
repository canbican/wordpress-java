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

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import net.bican.wordpress.Comment;
import net.bican.wordpress.CommentCount;
import net.bican.wordpress.CommentStatusList;
import net.bican.wordpress.Post;

@SuppressWarnings({ "static-method", "javadoc", "nls", "boxing" })
public class CommentTest extends AbstractWordpressTest {
  private static final Post post = new Post();
  
  @BeforeClass
  public static void setup() {
    post.setPost_name("test post name");
    post.setPost_content("test content");
    post.setPost_title("test post title");
    post.setPost_excerpt("test post excerpt");
  }
  
  @Test
  public void testCommentCount() throws Exception {
    Integer p = WP.newPost(post);
    WP.newComment(p, 0, "test comment", "admin", "", "");
    WP.newComment(p, 0, "test comment 2nd", "admin", "", "");
    CommentCount c = WP.getCommentsCount(p);
    assertNotNull(c);
    assertEquals(c.getApproved().intValue(), 2);
    WP.deletePost(p);
  }
  
  @Test
  public void testGetComments() throws Exception {
    List<Comment> cs = WP.getComments(null, null, null, null);
    assertNotNull(cs);
    Integer p = WP.newPost(post);
    WP.newComment(p, 0, "test comment", "admin", "", "");
    List<Comment> cs2 = WP.getComments(null, null, null, null);
    assertNotNull(cs);
    assertEquals(cs.size() + 1, cs2.size());
    WP.deletePost(p);
  }
  
  @Test
  public void testGetComment() throws Exception {
    Integer pId = WP.newPost(post);
    Integer cId = WP.newComment(pId, 0, "test comment", "admin", "", "");
    Comment c = WP.getComment(cId);
    assertNotNull(c);
    assertEquals("test comment", c.getContent());
    WP.deletePost(pId);
  }
  
  @Test
  public void testNewComment() throws Exception {
    Integer pId = WP.newPost(post);
    Integer c1 = WP.newComment(pId, 0, "test comment", "admin", "", "");
    assertNotNull(c1);
    assertTrue(c1.intValue() > 0);
    WP.deletePost(pId);
  }
  
  @Test
  public void testGetCommentStatusList() throws Exception {
    CommentStatusList r = WP.getCommentStatusList();
    assertNotNull(r);
    assertNotNull(r.getApprove());
    assertEquals("Approved", r.getApprove());
    assertNotNull(r.getHold());
    assertEquals("Unapproved", r.getHold());
    assertNotNull(r.getSpam());
    assertEquals("Spam", r.getSpam());
  }
  
  @Test
  public void deleteComment() throws Exception {
    Integer pId = WP.newPost(post);
    Integer cId = WP.newComment(pId, 0, "test comment", "admin", "", "");
    WP.deleteComment(cId);
    Comment c = WP.getComment(cId);
    assertNotNull(c);
    assertEquals("trash", c.getStatus());
    WP.deletePost(pId);
  }
  
  @Test
  public void editComment() throws Exception {
    Integer pId = WP.newPost(post);
    Integer cId = WP.newComment(pId, 0, "test comment", "admin", "", "");
    Comment comment = WP.getComment(cId);
    comment.setContent("edited content");
    WP.editComment(comment);
    Comment comment2 = WP.getComment(cId);
    assertNotNull(comment2);
    assertEquals("edited content", comment2.getContent());
    WP.deletePost(pId);
  }
}
