/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright 2012-2015 Can Bican
 * <can@bican.net> See the file 'COPYING' in the distribution for licensing terms.
 */
package net.bican.wordpress;

import lombok.Getter;
import lombok.Setter;

/**
 * Class for keeping image meta data
 *
 * @author Can Bican
 */
@Getter
@Setter
public class PostThumbnailImageMeta extends XmlRpcMapped {
  Double aperture; // TODO wordpress codex tells it's integer but should be
  String camera;
  String caption;
  String copyright;
  Integer created_timestamp;
  // double
  String credit;
  Double focal_length; // TODO wordpress codex tells it's integer but exif
                       // accepts doubles
  String iso;
  Integer orientation; // wordpress codex does not include this TODO
  Double shutter_speed;// TODO wordpress codex tells it's integer but exif
  // accepts doubles
  String title;
}
