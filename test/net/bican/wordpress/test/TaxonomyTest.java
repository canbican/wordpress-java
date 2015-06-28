package net.bican.wordpress.test;

import static org.junit.Assert.*;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;

import net.bican.wordpress.Taxonomy;
import net.bican.wordpress.Term;
import net.bican.wordpress.TermFilter;

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
  
  @Test
  public void testGetTaxonomy() {
    Taxonomy taxonomy = WP.getTaxonomy("category");
    assertNotNull(taxonomy);
    assertEquals("category", taxonomy.getName());
  }
  
  @Test
  public void testGetTerms() {
    List<Term> terms = WP.getTerms("category");
    assertNotNull(terms);
    assertEquals(1, terms.size());
    Term term = terms.get(0);
    assertNotNull(term);
    assertEquals("Uncategorized", term.getName());
  }
  
  @Test
  public void testGetTermsWithFilter() {
    TermFilter filter = new TermFilter();
    filter.setSearch("xxx");
    List<Term> terms = WP.getTerms("category", filter);
    assertNotNull(terms);
    assertEquals(0, terms.size());
    filter.setSearch("cate");
    terms = WP.getTerms("category", filter);
    assertNotNull(terms);
    assertEquals(1, terms.size());
  }
  
  @Test
  public void testGetTerm() {
    @SuppressWarnings("boxing")
    Term term = WP.getTerm("category", 1);
    assertNotNull(term);
    assertEquals("Uncategorized", term.getName());
  }
  
  @Test
  public void testNewDeleteTerm() {
    Term term = new Term();
    term.setName("denemeterm");
    term.setTaxonomy("category");
    Integer termId = WP.newTerm(term);
    assertNotNull(termId);
    assertTrue(termId.intValue() > 0);
    assertTrue(WP.deleteTerm("category", termId));
    try {
      assertFalse(WP.deleteTerm("category", termId));
      fail();
    } catch (UndeclaredThrowableException e) {
      // that's what we expect
    }
  }
  
  @SuppressWarnings("boxing")
  @Test
  public void testEditTerm() {
    Term term = WP.getTerm("category", 1);
    String oldName = term.getName();
    term.setName("newnamedeneme");
    Term editTerm = new Term();
    editTerm.setTaxonomy("category");
    editTerm.setName("newnamedeneme");
    assertTrue(WP.editTerm(term.getTerm_id(), editTerm));
    term = WP.getTerm("category", 1);
    assertEquals("newnamedeneme", term.getName());
    editTerm.setName(oldName);
    assertTrue(WP.editTerm(term.getTerm_id(), editTerm));
    term = WP.getTerm("category", 1);
    assertEquals(oldName, term.getName());
  }
}
