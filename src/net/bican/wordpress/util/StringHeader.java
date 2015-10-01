/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress.util;

/**
 * Interface for supplying a header for tabular output in toString()
 *
 * @author Can Bican
 */
public interface StringHeader {
  /**
   * @return Header list
   */
  public String getStringHeader();
}
