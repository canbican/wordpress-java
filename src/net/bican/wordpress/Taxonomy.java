/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress;

import java.util.List;

import net.bican.wordpress.util.StringHeader;

/**
 * Taxonomy object for a blog
 *
 * @author Can Bican
 */
public class Taxonomy extends XmlRpcMapped implements StringHeader {
  boolean _builtin;
  Cap cap;
  boolean hierarchical;
  String label;
  Labels labels;
  String name;
  List<String> object_type;
  boolean p;
  boolean show_ui;
  
  /**
   * @return the cap
   */
  public Cap getCap() {
    return this.cap;
  }
  
  /**
   * @return the label
   */
  public String getLabel() {
    return this.label;
  }
  
  /**
   * @return the labels
   */
  public Labels getLabels() {
    return this.labels;
  }
  
  /**
   * @return the name
   */
  public String getName() {
    return this.name;
  }
  
  /**
   * @return the object type list
   */
  public List<String> getObject_type() {
    return this.object_type;
  }
  
  @Override
  public String getStringHeader() {
    return ""; // this is not a tabulated list //$NON-NLS-1$
  }
  
  /**
   * @return the built-in status
   */
  public boolean is_builtin() {
    return this._builtin;
  }
  
  /**
   * @return the hierarchical status
   */
  public boolean isHierarchical() {
    return this.hierarchical;
  }
  
  /**
   * @return the public status
   */
  public boolean isPublic() {
    return this.p;
  }
  
  /**
   * @return the show-ui status
   */
  public boolean isShow_ui() {
    return this.show_ui;
  }
  
  /**
   * @param _builtin
   *          built-in status
   */
  public void set_builtin(final boolean _builtin) {
    this._builtin = _builtin;
  }
  
  /**
   * @param cap
   *          cap to set
   */
  public void setCap(final Cap cap) {
    this.cap = cap;
  }
  
  /**
   * @param hierarchical
   *          hierarchical status
   */
  public void setHierarchical(final boolean hierarchical) {
    this.hierarchical = hierarchical;
  }
  
  /**
   * @param label
   *          label to set
   */
  public void setLabel(final String label) {
    this.label = label;
  }
  
  /**
   * @param labels
   *          labels to set
   */
  public void setLabels(final Labels labels) {
    this.labels = labels;
  }
  
  /**
   * @param name
   *          name to set
   */
  public void setName(final String name) {
    this.name = name;
  }
  
  /**
   * @param object_type
   *          object type list to set
   */
  public void setObject_type(final List<String> object_type) {
    this.object_type = object_type;
  }
  
  /**
   * @param p
   *          public status
   */
  public void setPublic(final boolean p) {
    this.p = p;
  }
  
  /**
   * @param show_ui
   *          show-ui status
   */
  public void setShow_ui(final boolean show_ui) {
    this.show_ui = show_ui;
  }
}
