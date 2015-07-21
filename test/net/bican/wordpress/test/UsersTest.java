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

import net.bican.wordpress.Author;
import net.bican.wordpress.FilterUser;
import net.bican.wordpress.User;
import net.bican.wordpress.UserBlog;

import org.junit.Test;

@SuppressWarnings({ "javadoc", "static-method", "boxing" })
public class UsersTest extends AbstractWordpressTest {
  
  private static final String ROLE_ADMINISTRATOR = "administrator"; //$NON-NLS-1$
  private static final String USER_ADMIN = "admin"; //$NON-NLS-1$
  
  @Test
  public void testGetUsersBlogs() throws Exception {
    List<UserBlog> r = WP.getUsersBlogs();
    assertNotNull(r);
    assertEquals(1, r.size());
    UserBlog blog = r.get(0);
    assertNotNull(blog);
    assertEquals(Integer.valueOf(1), blog.getBlogid());
    assertEquals(DEFAULT_SITE_TITLE, blog.getBlogName());
  }
  
  @Test
  public void testGetUser() throws Exception {
    User r = WP.getUser(1);
    assertNotNull(r);
    assertEquals(USER_ADMIN, r.getNicename());
    assertNotNull(r.getRoles());
    assertEquals(1, r.getRoles().size());
    String role = r.getRoles().get(0);
    assertNotNull(role);
    assertEquals(ROLE_ADMINISTRATOR, role);
  }
  
  @Test
  public void testGetUsers() throws Exception {
    List<User> r = WP.getUsers();
    assertNotNull(r);
    assertEquals(2, r.size());
  }
  
  @Test
  public void testGetUsersWithFilter() throws Exception {
    FilterUser filter = new FilterUser();
    filter.setNumber(1);
    List<User> r = WP.getUsers(filter);
    assertNotNull(r);
    assertEquals(1, r.size());
    filter.setNumber(null);
    filter.setRole(ROLE_ADMINISTRATOR);
    r = WP.getUsers(filter);
    assertNotNull(r);
    assertEquals(1, r.size());
    User user = r.get(0);
    assertNotNull(user);
    assertEquals(ROLE_ADMINISTRATOR, user.getRoles().get(0));
  }
  
  @Test
  public void testGetProfile() throws Exception {
    User r = WP.getProfile();
    assertNotNull(r);
    assertEquals(USER_ADMIN, r.getNickname());
  }
  
  @Test
  public void testAuthors() throws Exception {
    List<Author> r = WP.getAuthors();
    assertNotNull(r);
    assertEquals(2, r.size());
    Author a = r.get(0);
    assertNotNull(a);
    assertNotNull(a.getDisplay_name());
    assertNotNull(a.getUser_id());
    assertNotNull(a.getUser_login());
  }
}
