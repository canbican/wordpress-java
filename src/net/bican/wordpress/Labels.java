/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright 2012-2015 Can Bican
 * <can@bican.net> See the file 'COPYING' in the distribution for licensing terms.
 */
package net.bican.wordpress;

import lombok.Getter;
import lombok.Setter;
import net.bican.wordpress.util.StringHeader;

/**
 * Labels object for blog.
 *
 * @author Can Bican
 */
@Getter
@Setter
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

  @Override
  public String getStringHeader() {
    return ""; //$NON-NLS-1$
  }
}
