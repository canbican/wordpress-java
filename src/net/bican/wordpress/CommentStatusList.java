/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright 2012-2015 Can Bican
 * <can@bican.net> See the file 'COPYING' in the distribution for licensing terms.
 */
package net.bican.wordpress;

import lombok.Getter;
import lombok.Setter;
import net.bican.wordpress.util.StringHeader;

/**
 * Comment status names for the blog
 *
 * @author Can Bican
 */
@Getter
@Setter
public class CommentStatusList extends XmlRpcMapped implements StringHeader {
  String approve;
  String hold;
  String spam;

  @Override
  @SuppressWarnings("nls")
  public String getStringHeader() {
    final String TAB = ":";
    return "Name" + TAB + "Description";
  }
}
