/*
 * 
 * Wordpress-java
 * https://github.com/canbican/wordpress-java/
 * 
 * Copyright 2012-2015 Can Bican <can@bican.net>
 * See the file 'COPYING' in the distribution for licensing terms.
 * 
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
  String name;
  String label;
  boolean hierarchical;
  boolean p;
  boolean show_ui;
  boolean _builtin;
  Labels labels;
  Cap cap;
  List<String> object_type;
  
  /**
   * @return the object type list
   */
  public List<String> getObject_type() {
    return this.object_type;
  }
  
  /**
   * @param object_type
   *          object type list to set
   */
  public void setObject_type(final List<String> object_type) {
    this.object_type = object_type;
  }
  
  /**
   * @return the cap
   */
  public Cap getCap() {
    return this.cap;
  }
  
  /**
   * @param cap
   *          cap to set
   */
  public void setCap(final Cap cap) {
    this.cap = cap;
  }
  
  /**
   * @param _builtin
   *          built-in status
   */
  public void set_builtin(final boolean _builtin) {
    this._builtin = _builtin;
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
   * @param name
   *          name to set
   */
  public void setName(final String name) {
    this.name = name;
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
  
  /**
   * @return the built-in status
   */
  public boolean is_builtin() {
    return this._builtin;
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
   * @return the label
   */
  public String getLabel() {
    return this.label;
  }
  
  /**
   * @return the name
   */
  public String getName() {
    return this.name;
  }
  
  /**
   * @return the hierarchical status
   */
  public boolean isHierarchical() {
    return this.hierarchical;
  }
  
  /**
   * @return the labels
   */
  public Labels getLabels() {
    return this.labels;
  }
  
  /**
   * @param labels
   *          labels to set
   */
  public void setLabels(final Labels labels) {
    this.labels = labels;
  }
  
  @Override
  public String getStringHeader() {
    return ""; // this is not a tabulated list //$NON-NLS-1$
  }
}
