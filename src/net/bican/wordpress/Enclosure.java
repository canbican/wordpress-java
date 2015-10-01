/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress;

/**
 * Enclosure data in a post
 *
 * @author Can Bican
 */
public class Enclosure extends XmlRpcMapped {
  Integer length;
  String type;
  String url;
  
  /**
   * @return the length
   */
  public final Integer getLength() {
    return this.length;
  }
  
  /**
   * @return the type
   */
  public final String getType() {
    return this.type;
  }
  
  /**
   * @return the url
   */
  public final String getUrl() {
    return this.url;
  }
  
  /**
   * @param length
   *          the length to set
   */
  public final void setLength(final Integer length) {
    this.length = length;
  }
  
  /**
   * @param type
   *          the type to set
   */
  public final void setType(final String type) {
    this.type = type;
  }
  
  /**
   * @param url
   *          the url to set
   */
  public final void setUrl(final String url) {
    this.url = url;
  }
}
