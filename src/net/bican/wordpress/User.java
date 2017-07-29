/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright 2012-2015 Can Bican
 * <can@bican.net> See the file 'COPYING' in the distribution for licensing terms.
 */
package net.bican.wordpress;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import net.bican.wordpress.util.StringHeader;

/**
 * Class that keeps user information from blogger style calls
 *
 * @author Can Bican
 */
@Getter
@Setter
public class User extends XmlRpcMapped implements StringHeader {
  String bio;
  String display_name;
  String email;
  String first_name;
  String last_name;
  String nicename;
  String nickname;
  Date registered;
  List<String> roles;
  String url;
  Integer user_id;
  String username;

  @Override
  @SuppressWarnings("nls")
  public String getStringHeader() {
    final String TAB = ":";
    return "First name" + TAB + "Last name" + TAB + "Nick name" + TAB + "Url" + TAB + "User ID";
  }
}
