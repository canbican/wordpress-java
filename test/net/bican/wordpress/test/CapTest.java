/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright 2012-2015 Can Bican
 * <can@bican.net> See the file 'COPYING' in the distribution for licensing terms.
 */
package net.bican.wordpress.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import net.bican.wordpress.Cap;

@SuppressWarnings({"static-method", "javadoc", "nls"})
public class CapTest extends AbstractWordpressTest {
  @Test
  public void capStringHeaderTest() throws Exception {
    Cap cap = new Cap();
    cap.setAssign_terms("1");
    cap.setDelete_terms("2");
    cap.setEdit_terms("3");
    cap.setManage_terms("4");
    assertEquals("", cap.getStringHeader());
    assertEquals("1:2:3:4", cap.toOneLinerString());
  }
}
