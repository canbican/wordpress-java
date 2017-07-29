/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright 2012-2015 Can Bican
 * <can@bican.net> See the file 'COPYING' in the distribution for licensing terms.
 */
package net.bican.wordpress;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents the result of an uploadFile()
 *
 * @author Can Bican
 */
@Getter
@Setter
public class MediaItemUploadResult extends XmlRpcMapped {
  String file;
  Integer id;
  String type;
  String url;
}
