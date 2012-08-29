/*
 * 
 * Wordpress-java
 * http://code.google.com/p/wordpress-java/
 * 
 * Copyright 2012 Can Bican <can@bican.net>
 * See the file 'COPYING' in the distribution for licensing terms.
 * 
 */
package net.bican.wordpress;

/**
 * 
 * Media Object, as the result of an upload;
 * 
 * @author Can Bican &lt;can@bican.net&gt;
 * 
 */
public class MediaObject extends XmlRpcMapped {
  String url;

  String file;

  String type;

  /**
   * @return the url
   */
  public String getUrl() {
    return this.url;
  }

  /**
   * @param url the url to set
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * @return the file
   */
  public String getFile() {
    return this.file;
  }

  /**
   * @param file the file to set
   */
  public void setFile(String file) {
    this.file = file;
  }

  /**
   * @return the type
   */
  public String getType() {
    return this.type;
  }

  /**
   * @param type the type to set
   */
  public void setType(String type) {
    this.type = type;
  }
}
