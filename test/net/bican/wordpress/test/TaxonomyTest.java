/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import net.bican.wordpress.Taxonomy;
import net.bican.wordpress.Term;
import net.bican.wordpress.TermFilter;

@SuppressWarnings({ "nls", "static-method", "javadoc" })
public class TaxonomyTest extends AbstractWordpressTest {
  @SuppressWarnings("boxing")
  @Test
  public void testEditTerm() throws Exception {
    Term term = WP.getTerm("category", 1);
    final String oldName = term.getName();
    term.setName("newnamedeneme");
    final Term editTerm = new Term();
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
  
  @Test
  public void testGetTaxonomies() throws Exception {
    final List<Taxonomy> taxonomies = WP.getTaxonomies();
    assertNotNull(taxonomies);
    assertEquals(3, taxonomies.size()); // currenty wordpress has 3 internal
                                        // taxonomies by default
    for (final Taxonomy t : taxonomies) {
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
  public void testGetTaxonomy() throws Exception {
    final Taxonomy taxonomy = WP.getTaxonomy("category");
    assertNotNull(taxonomy);
    assertEquals("category", taxonomy.getName());
  }
  
  @Test
  public void testGetTerm() throws Exception {
    @SuppressWarnings("boxing")
    final Term term = WP.getTerm("category", 1);
    assertNotNull(term);
    assertEquals("Uncategorized", term.getName());
  }
  
  @Test
  public void testGetTerms() throws Exception {
    final List<Term> terms = WP.getTerms("category");
    assertNotNull(terms);
    assertEquals(1, terms.size());
    final Term term = terms.get(0);
    assertNotNull(term);
    assertEquals("Uncategorized", term.getName());
  }
  
  @Test
  public void testGetTermsWithFilter() throws Exception {
    final TermFilter filter = new TermFilter();
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
  public void testNewDeleteTerm() throws Exception {
    final Term term = new Term();
    term.setName("denemeterm");
    term.setTaxonomy("category");
    final Integer termId = WP.newTerm(term);
    assertNotNull(termId);
    assertTrue(termId.intValue() > 0);
    assertTrue(WP.deleteTerm("category", termId));
    assertFalse(WP.deleteTerm("category", termId));
  }
}
