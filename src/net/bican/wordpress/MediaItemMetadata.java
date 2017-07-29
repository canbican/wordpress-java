/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright 2012-2015 Can Bican
 * <can@bican.net> See the file 'COPYING' in the distribution for licensing terms.
 */
package net.bican.wordpress;

import lombok.Getter;
import lombok.Setter;

/**
 * Meta data of a media object in a MediaItem class
 *
 * @author Can Bican
 */
@Getter
@Setter
public class MediaItemMetadata extends XmlRpcMapped {
  String file;
  Integer height;
  PostThumbnailImageMeta image_meta;
  MediaItemSizes sizes;
  Integer width;
}
