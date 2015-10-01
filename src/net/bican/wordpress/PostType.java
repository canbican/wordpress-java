/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress;

import java.util.List;

/**
 * Post types for posts
 *
 * @author Can Bican
 */
public class PostType extends XmlRpcMapped {
  boolean _builtin;
  boolean _public;
  PostTypeCap cap;
  boolean has_archive;
  boolean hierarchical;
  String label;
  Labels labels;
  boolean map_meta_cap;
  String menu_icon;
  int menu_position;
  String name;
  boolean show_in_menu;
  boolean show_ui;
  Support supports;
  List<String> taxonomies;
  
  /**
   * @return the cap
   */
  public final PostTypeCap getCap() {
    return this.cap;
  }
  
  /**
   * @return the label
   */
  public final String getLabel() {
    return this.label;
  }
  
  /**
   * @return the labels
   */
  public final Labels getLabels() {
    return this.labels;
  }
  
  /**
   * @return the menu_icon
   */
  public final String getMenu_icon() {
    return this.menu_icon;
  }
  
  /**
   * @return the menu_position
   */
  public final int getMenu_position() {
    return this.menu_position;
  }
  
  /**
   * @return the name
   */
  public final String getName() {
    return this.name;
  }
  
  /**
   * @return the supports
   */
  public final Support getSupports() {
    return this.supports;
  }
  
  /**
   * @return the taxonomies
   */
  public final List<String> getTaxonomies() {
    return this.taxonomies;
  }
  
  /**
   * @return the _builtin
   */
  public final boolean is_builtin() {
    return this._builtin;
  }
  
  /**
   * @return the has_archive
   */
  public final boolean isHas_archive() {
    return this.has_archive;
  }
  
  /**
   * @return the hierarchical
   */
  public final boolean isHierarchical() {
    return this.hierarchical;
  }
  
  /**
   * @return the map_meta_cap
   */
  public final boolean isMap_meta_cap() {
    return this.map_meta_cap;
  }
  
  /**
   * @return the _public
   */
  public final boolean isPublic() {
    return this._public;
  }
  
  /**
   * @return the show_in_menu
   */
  public final boolean isShow_in_menu() {
    return this.show_in_menu;
  }
  
  /**
   * @return the show_ui
   */
  public final boolean isShow_ui() {
    return this.show_ui;
  }
  
  /**
   * @param _builtin
   *          the _builtin to set
   */
  public final void set_builtin(final boolean _builtin) {
    this._builtin = _builtin;
  }
  
  /**
   * @param cap
   *          the cap to set
   */
  public final void setCap(final PostTypeCap cap) {
    this.cap = cap;
  }
  
  /**
   * @param has_archive
   *          the has_archive to set
   */
  public final void setHas_archive(final boolean has_archive) {
    this.has_archive = has_archive;
  }
  
  /**
   * @param hierarchical
   *          the hierarchical to set
   */
  public final void setHierarchical(final boolean hierarchical) {
    this.hierarchical = hierarchical;
  }
  
  /**
   * @param label
   *          the label to set
   */
  public final void setLabel(final String label) {
    this.label = label;
  }
  
  /**
   * @param labels
   *          the labels to set
   */
  public final void setLabels(final Labels labels) {
    this.labels = labels;
  }
  
  /**
   * @param map_meta_cap
   *          the map_meta_cap to set
   */
  public final void setMap_meta_cap(final boolean map_meta_cap) {
    this.map_meta_cap = map_meta_cap;
  }
  
  /**
   * @param menu_icon
   *          the menu_icon to set
   */
  public final void setMenu_icon(final String menu_icon) {
    this.menu_icon = menu_icon;
  }
  
  /**
   * @param menu_position
   *          the menu_position to set
   */
  public final void setMenu_position(final int menu_position) {
    this.menu_position = menu_position;
  }
  
  /**
   * @param name
   *          the name to set
   */
  public final void setName(final String name) {
    this.name = name;
  }
  
  /**
   * @param _public
   *          the _public to set
   */
  public final void setPublic(final boolean _public) {
    this._public = _public;
  }
  
  /**
   * @param show_in_menu
   *          the show_in_menu to set
   */
  public final void setShow_in_menu(final boolean show_in_menu) {
    this.show_in_menu = show_in_menu;
  }
  
  /**
   * @param show_ui
   *          the show_ui to set
   */
  public final void setShow_ui(final boolean show_ui) {
    this.show_ui = show_ui;
  }
  
  /**
   * @param supports
   *          the supports to set
   */
  public final void setSupports(final Support supports) {
    this.supports = supports;
  }
  
  /**
   * @param taxonomies
   *          the taxonomies to set
   */
  public final void setTaxonomies(final List<String> taxonomies) {
    this.taxonomies = taxonomies;
  }
}
