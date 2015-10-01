/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress;

import net.bican.wordpress.util.StringHeader;

/**
 * Labels object for blog.
 *
 * @author Can Bican
 */
public class Labels extends XmlRpcMapped implements StringHeader {
  String add_new_item;
  String add_or_remove_items;
  String all_items;
  String choose_from_most_used;
  String edit_item;
  String name;
  String new_item_name;
  String not_found;
  String parent_item;
  String parent_item_colon;
  String popular_items;
  String search_items;
  String separate_items_with_commas;
  String singular_name;
  String update_item;
  String view_item;
  
  /**
   * @return the add new item text
   */
  public String getAdd_new_item() {
    return this.add_new_item;
  }
  
  /**
   * @return the add or remove items text
   */
  public String getAdd_or_remove_items() {
    return this.add_or_remove_items;
  }
  
  /**
   * @return the all items text
   */
  public String getAll_items() {
    return this.all_items;
  }
  
  /**
   * @return the choose from most used text
   */
  public String getChoose_from_most_used() {
    return this.choose_from_most_used;
  }
  
  /**
   * @return the edit item text
   */
  public String getEdit_item() {
    return this.edit_item;
  }
  
  /**
   * @return the name
   */
  public String getName() {
    return this.name;
  }
  
  /**
   * @return the new item name text
   */
  public String getNew_item_name() {
    return this.new_item_name;
  }
  
  /**
   * @return the not found text
   */
  public String getNot_found() {
    return this.not_found;
  }
  
  /**
   * @return the parent item text
   */
  public String getParent_item() {
    return this.parent_item;
  }
  
  /**
   * @return the parent item colon text
   */
  public String getParent_item_colon() {
    return this.parent_item_colon;
  }
  
  /**
   * @return the popular items text
   */
  public String getPopular_items() {
    return this.popular_items;
  }
  
  /**
   * @return the search items text
   */
  public String getSearch_items() {
    return this.search_items;
  }
  
  /**
   * @return the separate items with commas text
   */
  public String getSeparate_items_with_commas() {
    return this.separate_items_with_commas;
  }
  
  /**
   * @return the singular name text
   */
  public String getSingular_name() {
    return this.singular_name;
  }
  
  @Override
  public String getStringHeader() {
    return ""; //$NON-NLS-1$
  }
  
  /**
   * @return the update item text
   */
  public String getUpdate_item() {
    return this.update_item;
  }
  
  /**
   * @return the view item text
   */
  public String getView_item() {
    return this.view_item;
  }
  
  /**
   * @param add_new_item
   *          add new item text to set
   */
  public void setAdd_new_item(final String add_new_item) {
    this.add_new_item = add_new_item;
  }
  
  /**
   * @param add_or_remove_items
   *          add or remove items text to set
   */
  public void setAdd_or_remove_items(final String add_or_remove_items) {
    this.add_or_remove_items = add_or_remove_items;
  }
  
  /**
   * @param all_items
   *          all items text to set
   */
  public void setAll_items(final String all_items) {
    this.all_items = all_items;
  }
  
  /**
   * @param choose_from_most_used
   *          choose from most used text to set
   */
  public void setChoose_from_most_used(final String choose_from_most_used) {
    this.choose_from_most_used = choose_from_most_used;
  }
  
  /**
   * @param edit_item
   *          edit item text to set
   */
  public void setEdit_item(final String edit_item) {
    this.edit_item = edit_item;
  }
  
  /**
   * @param name
   *          name to set
   */
  public void setName(final String name) {
    this.name = name;
  }
  
  /**
   * @param new_item_name
   *          new item name text to set
   */
  public void setNew_item_name(final String new_item_name) {
    this.new_item_name = new_item_name;
  }
  
  /**
   * @param not_found
   *          not found text to set
   */
  public void setNot_found(final String not_found) {
    this.not_found = not_found;
  }
  
  /**
   * @param parent_item
   *          parent item text to set
   */
  public void setParent_item(final String parent_item) {
    this.parent_item = parent_item;
  }
  
  /**
   * @param parent_item_colon
   *          parent item colon text to set
   */
  public void setParent_item_colon(final String parent_item_colon) {
    this.parent_item_colon = parent_item_colon;
  }
  
  /**
   * @param popular_items
   *          popular items text to set
   */
  public void setPopular_items(final String popular_items) {
    this.popular_items = popular_items;
  }
  
  /**
   * @param search_items
   *          search items text to set
   */
  public void setSearch_items(final String search_items) {
    this.search_items = search_items;
  }
  
  /**
   * @param separate_items_with_commas
   *          separate items with commas text to set
   */
  public void setSeparate_items_with_commas(
      final String separate_items_with_commas) {
    this.separate_items_with_commas = separate_items_with_commas;
  }
  
  /**
   * @param singular_name
   *          singular name to set
   */
  public void setSingular_name(final String singular_name) {
    this.singular_name = singular_name;
  }
  
  /**
   * @param update_item
   *          update item text to set
   */
  public void setUpdate_item(final String update_item) {
    this.update_item = update_item;
  }
  
  /**
   * @param view_item
   *          view item text to set
   */
  public void setView_item(final String view_item) {
    this.view_item = view_item;
  }
}
