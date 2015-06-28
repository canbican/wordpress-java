package net.bican.wordpress;

/**
 * Object for filtering terms during search
 * 
 * @author Can Bican &lt;can@bican.net&gt;
 */
public class TermFilter extends XmlRpcMapped {
  Integer number;
  Integer offset;
  String orderby;
  String order;
  boolean hide_empty;
  String search;
  
  /**
   * @return the number
   */
  public Integer getNumber() {
    return this.number;
  }
  
  /**
   * @param number
   *          number to set
   */
  public void setNumber(Integer number) {
    this.number = number;
  }
  
  /**
   * @return the offset
   */
  public Integer getOffset() {
    return this.offset;
  }
  
  /**
   * @param offset
   *          offset to set
   */
  public void setOffset(Integer offset) {
    this.offset = offset;
  }
  
  /**
   * @return the order-by
   */
  public String getOrderby() {
    return this.orderby;
  }
  
  /**
   * @param orderby
   *          order-by to set
   */
  public void setOrderby(String orderby) {
    this.orderby = orderby;
  }
  
  /**
   * @return the order
   */
  public String getOrder() {
    return this.order;
  }
  
  /**
   * @param order
   *          order to set
   */
  public void setOrder(String order) {
    this.order = order;
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
  public void setHide_empty(boolean hide_empty) {
    this.hide_empty = hide_empty;
  }
  
  /**
   * @return the search terms
   */
  public String getSearch() {
    return this.search;
  }
  
  /**
   * @param search
   *          search terms to set
   */
  public void setSearch(String search) {
    this.search = search;
  }
}
