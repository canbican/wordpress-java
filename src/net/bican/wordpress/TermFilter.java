/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress;

/**
 * Object for filtering terms during search
 *
 * @author Can Bican
 */
public class TermFilter extends XmlRpcMapped {
  boolean hide_empty;
  Integer number;
  Integer offset;
  String order;
  String orderby;
  String search;
  
  /**
   * @return the number
   */
  public Integer getNumber() {
    return this.number;
  }
  
  /**
   * @return the offset
   */
  public Integer getOffset() {
    return this.offset;
  }
  
  /**
   * @return the order
   */
  public String getOrder() {
    return this.order;
  }
  
  /**
   * @return the order-by
   */
  public String getOrderby() {
    return this.orderby;
  }
  
  /**
   * @return the search terms
   */
  public String getSearch() {
    return this.search;
  }
  
  /**
   * @return the hide-empty status
   */
  public boolean isHide_empty() {
    return this.hide_empty;
  }
  
  /**
   * @param hide_empty
   *          hide-empty status to set
   */
  public void setHide_empty(final boolean hide_empty) {
    this.hide_empty = hide_empty;
  }
  
  /**
   * @param number
   *          number to set
   */
  public void setNumber(final Integer number) {
    this.number = number;
  }
  
  /**
   * @param offset
   *          offset to set
   */
  public void setOffset(final Integer offset) {
    this.offset = offset;
  }
  
  /**
   * @param order
   *          order to set
   */
  public void setOrder(final String order) {
    this.order = order;
  }
  
  /**
   * @param orderby
   *          order-by to set
   */
  public void setOrderby(final String orderby) {
    this.orderby = orderby;
  }
  
  /**
   * @param search
   *          search terms to set
   */
  public void setSearch(final String search) {
    this.search = search;
  }
}
