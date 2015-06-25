package net.bican.wordpress.test;

import java.net.MalformedURLException;
import java.util.Map;

import net.bican.wordpress.Wordpress;

@SuppressWarnings({ "nls", "javadoc" })
public class AbstractWordpressTest {
  public static String    TOPURL    = "http://wordpressjava-test.local";
  public static String    USERNAME  = "admin";
  public static String    PASSWORD  = "admin";
  public static String    XMLRPCURL = null;
  public static Wordpress WP;

  {
    try {
      Map<String, String> env = System.getenv();
      if (env.containsKey("TOPURL"))
        TOPURL = env.get("TOPURL");
      if (env.containsKey("USERNAME"))
        USERNAME = env.get("USERNAME");
      if (env.containsKey("PASSWORD"))
        PASSWORD = env.get("PASSWORD");
      if (env.containsKey("XMLRPCURL"))
        XMLRPCURL = env.get("XMLRPCURL");
      else
        XMLRPCURL = TOPURL + "/xmlrpc.php";
      WP = new Wordpress(USERNAME, PASSWORD, XMLRPCURL);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

}
