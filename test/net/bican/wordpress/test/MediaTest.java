package net.bican.wordpress.test;

import static org.junit.Assert.*;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;

import net.bican.wordpress.MediaObject;

import org.junit.Test;

@SuppressWarnings({ "static-method", "javadoc", "nls", "boxing" })
public class MediaTest extends AbstractWordpressTest {

  @Test
  public void testMedia() throws Exception {
    MediaObject r = WP.newMediaObject("image/png", new File("test/test.png"),
        true);
    URL url = new URL(r.getUrl());
    HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
    urlConn.connect();
    assertEquals(HttpURLConnection.HTTP_OK, urlConn.getResponseCode());
  }
}
