/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright 2012-2015 Can Bican
 * <can@bican.net> See the file 'COPYING' in the distribution for licensing terms.
 */
package net.bican.wordpress;

import lombok.Getter;
import lombok.Setter;

/**
 * Class that keeps info for different sizes of a media item
 *
 * @author Can Bican
 */
@Getter
@Setter
public class MediaItemSizes extends XmlRpcMapped {
  MediaItemSize large;
  MediaItemSize medium;
  MediaItemSize post_thumbnail;
  MediaItemSize thumbnail;
}
