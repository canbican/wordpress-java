/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright 2012-2015 Can Bican
 * <can@bican.net> See the file 'COPYING' in the distribution for licensing terms.
 */
package net.bican.wordpress.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import net.bican.wordpress.Author;

@SuppressWarnings({"static-method", "javadoc", "nls"})
public class AuthorTest extends AbstractWordpressTest {
  @Test
  public void authorStringHeaderTest() throws Exception {
    Author author = new Author();
    author.setUser_id(Integer.valueOf(2));
    author.setUser_login("3");
    author.setDisplay_name("1");
    assertEquals("display_name:User_id:User_login", author.getStringHeader());
    assertEquals("1:2:3",author.toOneLinerString());
  }
}
