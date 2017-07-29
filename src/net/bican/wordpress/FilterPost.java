/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright 2012-2015 Can Bican
 * <can@bican.net> See the file 'COPYING' in the distribution for licensing terms.
 */
package net.bican.wordpress;

import lombok.Getter;
import lombok.Setter;

/**
 * Filter for retrieving posts
 *
 * @author Can Bican
 */
@Getter
@Setter
public class FilterPost extends XmlRpcMapped {
  Integer number;
  Integer offset;
  String order;
  String orderby;
  String post_status;
  String post_type;
}
