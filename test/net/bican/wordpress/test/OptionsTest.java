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

import net.bican.wordpress.Option;

@SuppressWarnings({ "static-method", "javadoc" })
public class OptionsTest extends AbstractWordpressTest {
  
  @Test
  public void testGetOption() throws Exception {
    final Option option = WP.getOption(BLOG_TAGLINE);
    assertNotNull(option);
    assertEquals(BLOG_TAGLINE, option.getName());
    assertEquals(SITE_TAGLINE, option.getDesc());
  }
  
  @Test
  public void testGetOptionMore() throws Exception {
    final List<Option> options = WP.getOptions(BLOG_TAGLINE, BLOG_TITLE);
    assertNotNull(options);
    assertEquals(2, options.size());
  }
  
  @Test
  public void testGetOptions() throws Exception {
    final List<Option> options = WP.getOptions();
    assertNotNull(options);
    assertEquals(27, options.size()); // This is epic, I chose 27 before
                                      // counting. Marking the day I am lucky in
                                      // the most useless sense: 2015/06/28
  }
  
  @Test
  public void testSetOption() throws Exception {
    final Option option = new Option();
    option.setName(BLOG_TAGLINE);
    option.setValue(TEST_SITE);
    final Option r = WP.setOption(option);
    assertNotNull(r);
    assertEquals(TEST_SITE, r.getValue());
    assertEquals(BLOG_TAGLINE, r.getName());
    option.setValue(DEFAULT_TAGLINE);
    WP.setOption(option);
  }
  
  @Test
  public void testSetOptions() throws Exception {
    final Option option1 = new Option();
    option1.setName(BLOG_TAGLINE);
    option1.setValue(TEST_SITE);
    final Option option2 = new Option();
    option2.setName(BLOG_TITLE);
    option2.setValue(TEST_SITE_TITLE);
    final List<Option> r = WP.setOptions(option1, option2);
    assertNotNull(r);
    assertEquals(2, r.size());
    final Option r1 = r.get(0);
    assertNotNull(r1);
    assertEquals(BLOG_TAGLINE, r1.getName());
    assertEquals(TEST_SITE, r1.getValue());
    final Option r2 = r.get(1);
    assertNotNull(r2);
    assertEquals(BLOG_TITLE, r2.getName());
    assertEquals(TEST_SITE_TITLE, r2.getValue());
    option1.setValue(DEFAULT_TAGLINE);
    option2.setValue(DEFAULT_SITE_TITLE);
    WP.setOptions(option1, option2);
  }
}
