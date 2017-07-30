/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright 2012-2015 Can Bican
 * <can@bican.net> See the file 'COPYING' in the distribution for licensing terms.
 */
package net.bican.wordpress;

import lombok.Getter;
import lombok.Setter;
import net.bican.wordpress.util.StringHeader;

/**
 * Author object for a blog.
 *
 * @author Can Bican
 */
@Getter
@Setter
public class Author extends XmlRpcMapped implements StringHeader {
  String display_name;
  Integer user_id;
  String user_login;

  /**
   * (non-Javadoc)
   *
   * @see net.bican.wordpress.util.StringHeader#getStringHeader()
   */
  @Override
  @SuppressWarnings("nls")
  public String getStringHeader() {
    return "display_name:User_id:User_login";
  }
}
