package net.bican.wordpress.test;

import static org.junit.Assert.*;

import java.util.List;

import net.bican.wordpress.Taxonomy;

import org.junit.Test;

@SuppressWarnings({ "nls", "static-method", "javadoc" })
public class TaxonomyTest extends AbstractWordpressTest {
  @Test
  public void testGetTaxonomies() {
    List<Taxonomy> taxonomies = WP.getTaxonomies();
    assertNotNull(taxonomies);
    assertEquals(3, taxonomies.size()); // currenty wordpress has 3 internal
                                        // taxonomies by default
    for (Taxonomy t : taxonomies) {
      if (t.getName().equals("category")) {
        assertNotNull(t.getLabels());
        assertNotNull(t.getLabels().getAll_items());
        assertEquals("All Categories", t.getLabels().getAll_items());
        assertNotNull(t.getCap());
        assertNotNull(t.getCap().getAssign_terms());
        assertEquals("edit_posts", t.getCap().getAssign_terms());
        assertNotNull(t.getObject_type());
        assertEquals(1, t.getObject_type().size());
        assertEquals("post", t.getObject_type().get(0));
        break;
      }
    }
  }
}
