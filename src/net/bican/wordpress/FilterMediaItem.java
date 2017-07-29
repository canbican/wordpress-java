/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress;

import lombok.Getter;
import lombok.Setter;

/**
 * Class for filtering media library result
 *
 * @author Can Bican
 */
@Getter
@Setter
public class FilterMediaItem extends XmlRpcMapped {
  String mime_type;
  Integer number;
  Integer offset;
  Integer parent_id;  
}
