/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import net.bican.wordpress.Author;
import net.bican.wordpress.FilterUser;
import net.bican.wordpress.User;
import net.bican.wordpress.UserBlog;

@SuppressWarnings({ "javadoc", "static-method", "boxing" })
public class UsersTest extends AbstractWordpressTest {
  
  private static final String ROLE_ADMINISTRATOR = "administrator"; //$NON-NLS-1$
  private static final String USER_ADMIN = "admin"; //$NON-NLS-1$
  
  @Test
  public void testAuthors() throws Exception {
    final List<Author> r = WP.getAuthors();
    assertNotNull(r);
    assertEquals(2, r.size());
    final Author a = r.get(0);
    assertNotNull(a);
    assertNotNull(a.getDisplay_name());
    assertNotNull(a.getUser_id());
    assertNotNull(a.getUser_login());
  }
  
  @Test
  public void testGetProfile() throws Exception {
    final User r = WP.getProfile();
    assertNotNull(r);
    assertEquals(USER_ADMIN, r.getNickname());
  }
  
  @Test
  public void testGetUser() throws Exception {
    final User r = WP.getUser(1);
    assertNotNull(r);
    assertEquals(USER_ADMIN, r.getNicename());
    assertNotNull(r.getRoles());
    assertEquals(1, r.getRoles().size());
    final String role = r.getRoles().get(0);
    assertNotNull(role);
    assertEquals(ROLE_ADMINISTRATOR, role);
  }
  
  @Test
  public void testGetUsers() throws Exception {
    final List<User> r = WP.getUsers();
    assertNotNull(r);
    assertEquals(2, r.size());
  }
  
  @Test
  public void testGetUsersBlogs() throws Exception {
    final List<UserBlog> r = WP.getUsersBlogs();
    assertNotNull(r);
    assertEquals(1, r.size());
    final UserBlog blog = r.get(0);
    assertNotNull(blog);
    assertEquals(Integer.valueOf(1), blog.getBlogid());
    assertEquals(DEFAULT_SITE_TITLE, blog.getBlogName());
  }
  
  @Test
  public void testGetUsersWithFilter() throws Exception {
    final FilterUser filter = new FilterUser();
    filter.setNumber(1);
    List<User> r = WP.getUsers(filter);
    assertNotNull(r);
    assertEquals(1, r.size());
    filter.setNumber(null);
    filter.setRole(ROLE_ADMINISTRATOR);
    r = WP.getUsers(filter);
    assertNotNull(r);
    assertEquals(1, r.size());
    final User user = r.get(0);
    assertNotNull(user);
    assertEquals(ROLE_ADMINISTRATOR, user.getRoles().get(0));
  }
}
