/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright 2012-2015 Can Bican
 * <can@bican.net> See the file 'COPYING' in the distribution for licensing terms.
 */
package net.bican.wordpress;

import lombok.Getter;
import lombok.Setter;

/**
 * Class that represents a wordpress attachment.
 *
 * @author Can Bican
 */
@Getter
@Setter
class Attachment extends XmlRpcMapped {
  byte[] bits;
  String name;
  Boolean overwrite;
  String type;
}
