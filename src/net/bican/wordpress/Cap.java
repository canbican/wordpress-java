/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright 2012-2015 Can Bican
 * <can@bican.net> See the file 'COPYING' in the distribution for licensing terms.
 */
package net.bican.wordpress;

import lombok.Getter;
import lombok.Setter;
import net.bican.wordpress.util.StringHeader;

/**
 * Cap object for a blog.
 *
 * @author Can Bican
 */
@Getter
@Setter
public class Cap extends XmlRpcMapped implements StringHeader {
  String assign_terms;
  String delete_terms;
  String edit_terms;
  String manage_terms;

  @Override
  public String getStringHeader() {
    return ""; //$NON-NLS-1$
  }
}
