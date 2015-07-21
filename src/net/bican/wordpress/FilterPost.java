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
 * Filter for retrieving posts
 * 
 * @author Can Bican
 *
 */
public class FilterPost extends XmlRpcMapped {
  String post_type;
  String post_status;
  Integer number;
  Integer offset;
  String orderby;
  String order;
  
  /**
   * @return the post_type
   */
  public final String getPost_type() {
    return this.post_type;
  }
  
  /**
   * @param post_type
   *          the post_type to set
   */
  public final void setPost_type(final String post_type) {
    this.post_type = post_type;
  }
  
  /**
   * @return the post_status
   */
  public final String getPost_status() {
    return this.post_status;
  }
  
  /**
   * @param post_status
   *          the post_status to set
   */
  public final void setPost_status(final String post_status) {
    this.post_status = post_status;
  }
  
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
   * @return the orderby
   */
  public final String getOrderby() {
    return this.orderby;
  }
  
  /**
   * @param orderby
   *          the orderby to set
   */
  public final void setOrderby(final String orderby) {
    this.orderby = orderby;
  }
  
  /**
   * @return the order
   */
  public final String getOrder() {
    return this.order;
  }
  
  /**
   * @param order
   *          the order to set
   */
  public final void setOrder(final String order) {
    this.order = order;
  }
}
