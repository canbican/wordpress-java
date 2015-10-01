/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress;

import net.bican.wordpress.util.StringHeader;

/**
 * Term object for a blog.
 *
 * @author Can Bican
 */
public class Term extends XmlRpcMapped implements StringHeader {
  Integer count;
  String description;
  String filter;
  String name;
  Integer parent;
  String slug;
  String taxonomy;
  String term_group;
  Integer term_id;
  String term_taxonomy_id;
  
  /**
   * @return the count
   */
  public Integer getCount() {
    return this.count;
  }
  
  /**
   * @return the description
   */
  public String getDescription() {
    return this.description;
  }
  
  /**
   * @return the filter
   */
  public final String getFilter() {
    return this.filter;
  }
  
  /**
   * @return the name
   */
  public String getName() {
    return this.name;
  }
  
  /**
   * @return the parent
   */
  public Integer getParent() {
    return this.parent;
  }
  
  /**
   * @return the slug
   */
  public String getSlug() {
    return this.slug;
  }
  
  @Override
  public String getStringHeader() {
    return ""; //$NON-NLS-1$
  }
  
  /**
   * @return the taxonomy
   */
  public String getTaxonomy() {
    return this.taxonomy;
  }
  
  /**
   * @return the term group
   */
  public String getTerm_group() {
    return this.term_group;
  }
  
  /**
   * @return the term id
   */
  public Integer getTerm_id() {
    return this.term_id;
  }
  
  /**
   * @return the taxonomy id
   */
  public String getTerm_taxonomy_id() {
    return this.term_taxonomy_id;
  }
  
  /**
   * @param count
   *          count to set
   */
  public void setCount(final Integer count) {
    this.count = count;
  }
  
  /**
   * @param description
   *          description to set
   */
  public void setDescription(final String description) {
    this.description = description;
  }
  
  /**
   * @param filter
   *          the filter to set
   */
  public final void setFilter(final String filter) {
    this.filter = filter;
  }
  
  /**
   * @param name
   *          name to set
   */
  public void setName(final String name) {
    this.name = name;
  }
  
  /**
   * @param parent
   *          parent to set
   */
  public void setParent(final Integer parent) {
    this.parent = parent;
  }
  
  /**
   * @param slug
   *          slug to set
   */
  public void setSlug(final String slug) {
    this.slug = slug;
  }
  
  /**
   * @param taxonomy
   *          taxonomy to set
   */
  public void setTaxonomy(final String taxonomy) {
    this.taxonomy = taxonomy;
  }
  
  /**
   * @param term_group
   *          term group to set
   */
  public void setTerm_group(final String term_group) {
    this.term_group = term_group;
  }
  
  /**
   * @param term_id
   *          term id to set
   */
  public void setTerm_id(final Integer term_id) {
    this.term_id = term_id;
  }
  
  /**
   * @param term_taxonomy_id
   *          taxonomy id to set
   */
  public void setTerm_taxonomy_id(final String term_taxonomy_id) {
    this.term_taxonomy_id = term_taxonomy_id;
  }
  
}
