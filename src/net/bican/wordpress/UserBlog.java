/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright 2012-2015 Can Bican
 * <can@bican.net> See the file 'COPYING' in the distribution for licensing terms.
 */
package net.bican.wordpress;

import lombok.Getter;
import lombok.Setter;

/**
 * The class for keeping user blog entries
 *
 * @author Can Bican
 */
@Getter
@Setter
public class UserBlog extends XmlRpcMapped {
  Integer blogid;
  String blogName;
  boolean isAdmin;
  String url;
  String xmlrpc;
}
