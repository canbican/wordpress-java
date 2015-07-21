/*
 * 
 * Wordpress-java
 * https://github.com/canbican/wordpress-java/
 * 
 * Copyright 2012-2015 Can Bican <can@bican.net>
 * See the file 'COPYING' in the distribution for licensing terms.
 * 
 */
package net.bican.wordpress;

/**
 * Class for filtering media library result
 * 
 * @author Can Bican
 */
public class FilterMediaItem extends XmlRpcMapped {
  Integer number;
  Integer offset;
  Integer parent_id;
  String mime_type;
  
  /**
   * @return the number
   */
  public final Integer getNumber() {
    return this.number;
  }
  
  /**
   * @param number
   *          the number to set
   */
  public final void setNumber(final Integer number) {
    this.number = number;
  }
  
  /**
   * @return the offset
   */
  public final Integer getOffset() {
    return this.offset;
  }
  
  /**
   * @param offset
   *          the offset to set
   */
  public final void setOffset(final Integer offset) {
    this.offset = offset;
  }
  
  /**
   * @return the parent_id
   */
  public final Integer getParent_id() {
    return this.parent_id;
  }
  
  /**
   * @param parent_id
   *          the parent_id to set
   */
  public final void setParent_id(final Integer parent_id) {
    this.parent_id = parent_id;
  }
  
  /**
   * @return the mime_type
   */
  public final String getMime_type() {
    return this.mime_type;
  }
  
  /**
   * @param mime_type
   *          the mime_type to set
   */
  public final void setMime_type(final String mime_type) {
    this.mime_type = mime_type;
  }
}
