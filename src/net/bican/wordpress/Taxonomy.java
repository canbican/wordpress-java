/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright 2012-2015 Can Bican
 * <can@bican.net> See the file 'COPYING' in the distribution for licensing terms.
 */
package net.bican.wordpress;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import net.bican.wordpress.util.StringHeader;

/**
 * Taxonomy object for a blog
 *
 * @author Can Bican
 */
@Getter
@Setter
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

  @Override
  public String getStringHeader() {
    return ""; // this is not a tabulated list //$NON-NLS-1$
  }
}
