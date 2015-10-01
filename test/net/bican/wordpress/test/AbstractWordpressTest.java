/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress.test;

import java.net.MalformedURLException;
import java.util.Map;

import net.bican.wordpress.Wordpress;

@SuppressWarnings({ "nls", "javadoc" })
public class AbstractWordpressTest {
  static final String BLOG_TAGLINE = "blog_tagline"; //$NON-NLS-1$
  static final String BLOG_TITLE = "blog_title"; //$NON-NLS-1$
  static final String DEFAULT_SITE_TITLE = "WordpressJavaTest"; //$NON-NLS-1$
  static final String DEFAULT_TAGLINE = "Just another WordPress site"; //$NON-NLS-1$
  public static String PASSWORD = "admin";
  static final String SITE_TAGLINE = "Site Tagline"; //$NON-NLS-1$
  static final String TEST_SITE = "test site"; //$NON-NLS-1$
  static final String TEST_SITE_TITLE = "test site title"; //$NON-NLS-1$
  public static String TOPURL = "http://wordpressjava-test.local.";
  public static String USERNAME = "admin";
  public static Wordpress WP;
  public static String XMLRPCURL = null;
  
  {
    try {
      final Map<String, String> env = System.getenv();
      if (env.containsKey("TOPURL")) {
        TOPURL = env.get("TOPURL");
      }
      if (env.containsKey("USERNAME")) {
        USERNAME = env.get("USERNAME");
      }
      if (env.containsKey("PASSWORD")) {
        PASSWORD = env.get("PASSWORD");
      }
      if (env.containsKey("XMLRPCURL")) {
        XMLRPCURL = env.get("XMLRPCURL");
      } else {
        XMLRPCURL = TOPURL + "/xmlrpc.php";
      }
      WP = new Wordpress(USERNAME, PASSWORD, XMLRPCURL);
    } catch (final MalformedURLException e) {
      e.printStackTrace();
    }
  }
  
}
