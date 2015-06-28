package net.bican.wordpress;

/**
 * Term object for a blog.
 * 
 * @author Can Bican &lt;can@bican.net&gt;
 */
public class Term extends XmlRpcMapped implements StringHeader {
  Integer term_id;
  String name;
  String slug;
  String term_group;
  String term_taxonomy_id;
  String taxonomy;
  String description;
  Integer parent;
  Integer count;
  
  /**
   * @return the term id
   */
  public Integer getTerm_id() {
    return this.term_id;
  }
  
  /**
   * @param term_id
   *          term id to set
   */
  public void setTerm_id(Integer term_id) {
    this.term_id = term_id;
  }
  
  /**
   * @return the name
   */
  public String getName() {
    return this.name;
  }
  
  /**
   * @param name
   *          name to set
   */
  public void setName(String name) {
    this.name = name;
  }
  
  /**
   * @return the slug
   */
  public String getSlug() {
    return this.slug;
  }
  
  /**
   * @param slug
   *          slug to set
   */
  public void setSlug(String slug) {
    this.slug = slug;
  }
  
  /**
   * @return the term group
   */
  public String getTerm_group() {
    return this.term_group;
  }
  
  /**
   * @param term_group
   *          term group to set
   */
  public void setTerm_group(String term_group) {
    this.term_group = term_group;
  }
  
  /**
   * @return the taxonomy id
   */
  public String getTerm_taxonomy_id() {
    return this.term_taxonomy_id;
  }
  
  /**
   * @param term_taxonomy_id
   *          taxonomy id to set
   */
  public void setTerm_taxonomy_id(String term_taxonomy_id) {
    this.term_taxonomy_id = term_taxonomy_id;
  }
  
  /**
   * @return the taxonomy
   */
  public String getTaxonomy() {
    return this.taxonomy;
  }
  
  /**
   * @param taxonomy
   *          taxonomy to set
   */
  public void setTaxonomy(String taxonomy) {
    this.taxonomy = taxonomy;
  }
  
  /**
   * @return the description
   */
  public String getDescription() {
    return this.description;
  }
  
  /**
   * @param description
   *          description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }
  
  /**
   * @return the parent
   */
  public Integer getParent() {
    return this.parent;
  }
  
  /**
   * @param parent
   *          parent to set
   */
  public void setParent(Integer parent) {
    this.parent = parent;
  }
  
  /**
   * @return the count
   */
  public Integer getCount() {
    return this.count;
  }
  
  /**
   * @param count
   *          count to set
   */
  public void setCount(Integer count) {
    this.count = count;
  }
  
  @Override
  public String getStringHeader() {
    return ""; //$NON-NLS-1$
  }
  
}
