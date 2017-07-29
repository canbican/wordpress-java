/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright 2012-2015 Can Bican
 * <can@bican.net> See the file 'COPYING' in the distribution for licensing terms.
 */
package net.bican.wordpress;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * Class for an media item object for a blog
 *
 * @author Can Bican
 */
@Getter
@Setter
public class MediaItem extends XmlRpcMapped {
  Integer attachment_id;
  String caption;
  Date date_created_gmt;
  String description;
  String link;
  MediaItemMetadata metadata;
  Integer parent;
  String thumbnail;
  String title;
}
