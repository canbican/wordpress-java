/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright 2012-2015 Can Bican
 * <can@bican.net> See the file 'COPYING' in the distribution for licensing terms.
 */
package net.bican.wordpress;

import lombok.Getter;
import lombok.Setter;
import net.bican.wordpress.util.StringHeader;

/**
 * Comment Count object for a blog post.
 *
 * @author Fred Potter
 */
@Getter
@Setter
public class CommentCount extends XmlRpcMapped implements StringHeader {
  Integer approved;
  Integer awaiting_moderation;
  Integer spam;
  Integer total_comments;

  @Override
  @SuppressWarnings("nls")
  public String getStringHeader() {
    final String TAB = ":";
    return "Approved" + TAB + "Awaiting Moderation" + TAB + "Spam" + TAB + "Total Comments";
  }
}
