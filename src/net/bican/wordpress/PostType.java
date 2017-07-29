/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright 2012-2015 Can Bican
 * <can@bican.net> See the file 'COPYING' in the distribution for licensing terms.
 */
package net.bican.wordpress;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Post types for posts
 *
 * @author Can Bican
 */
@Getter
@Setter
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
}
