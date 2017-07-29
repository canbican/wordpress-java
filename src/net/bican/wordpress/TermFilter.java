/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright 2012-2015 Can Bican
 * <can@bican.net> See the file 'COPYING' in the distribution for licensing terms.
 */
package net.bican.wordpress;

import lombok.Getter;
import lombok.Setter;

/**
 * Object for filtering terms during search
 *
 * @author Can Bican
 */
@Getter
@Setter
public class TermFilter extends XmlRpcMapped {
  boolean hide_empty;
  Integer number;
  Integer offset;
  String order;
  String orderby;
  String search;
}
