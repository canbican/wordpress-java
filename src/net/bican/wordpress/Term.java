/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright 2012-2015 Can Bican
 * <can@bican.net> See the file 'COPYING' in the distribution for licensing terms.
 */
package net.bican.wordpress;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.bican.wordpress.util.StringHeader;

/**
 * Term object for a blog.
 *
 * @author Can Bican
 */
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Term extends XmlRpcMapped implements StringHeader {
  Integer count;
  String description;
  String filter;
  String name;
  Integer parent;
  String slug;
  String taxonomy;
  String term_group;
  Integer term_id;
  String term_taxonomy_id;

  @Override
  public String getStringHeader() {
    return ""; //$NON-NLS-1$
  }
}
