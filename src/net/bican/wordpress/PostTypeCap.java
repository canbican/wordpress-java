/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright 2012-2015 Can Bican
 * <can@bican.net> See the file 'COPYING' in the distribution for licensing terms.
 */
package net.bican.wordpress;

import lombok.Getter;
import lombok.Setter;

/**
 * Capabilities for a post type
 *
 * @author Can Bican
 */
@Getter
@Setter
public class PostTypeCap extends XmlRpcMapped {
  String create_posts;
  String delete_others_posts;
  String delete_post;
  String delete_posts;
  String delete_private_posts;
  String delete_published_posts;
  String edit_others_posts;
  String edit_post;
  String edit_posts;
  String edit_private_posts;
  String edit_published_posts;
  String publish_posts;
  String read;
  String read_post;
  String read_private_posts;
}
