/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress;

import lombok.Getter;
import lombok.Setter;

/**
 * Enclosure data in a post
 *
 * @author Can Bican
 */
@Getter
@Setter
public class Enclosure extends XmlRpcMapped {
  Integer length;
  String type;
  String url;
}
