package net.bican.wordpress.test;

import static org.junit.Assert.*;

import java.util.List;

import net.bican.wordpress.Author;

import org.junit.Test;

@SuppressWarnings({ "static-method", "javadoc", "nls" })
public class AuthorTest extends AbstractWordpressTest {

  @Test
  public void testAuthorList() throws Exception {
    List<Author> result = WP.getAuthors();
    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals(result.get(0).getDisplay_name(), "admin");
  }
}
