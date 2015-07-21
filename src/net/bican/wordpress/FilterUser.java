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

import redstone.xmlrpc.XmlRpcStruct;

/**
 * Used for filtering users in getUsers()
 * 
 * @author Can Bican
 */
public class FilterUser {
  String role;
  String who;
  Integer number;
  Integer offset;
  String orderby;
  String order;
  
  /**
   * @return the role
   */
  public final String getRole() {
    return this.role;
  }
  
  /**
   * @param role
   *          the role to set
   */
  public final void setRole(final String role) {
    this.role = role;
  }
  
  /**
   * @return the who
   */
  public final String getWho() {
    return this.who;
  }
  
  /**
   * @param who
   *          the who to set
   */
  public final void setWho(final String who) {
    this.who = who;
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
  
  /**
   * @return only with non-null values
   */
  @SuppressWarnings("unchecked")
  public XmlRpcStruct buildWithNonNullValues() {
    final XmlRpcStruct r = new XmlRpcStruct();
    if (this.role != null) {
      r.put("role", this.role); //$NON-NLS-1$
    }
    if (this.who != null) {
      r.put("who", this.who); //$NON-NLS-1$
    }
    if (this.number != null) {
      r.put("number", this.number); //$NON-NLS-1$
    }
    if (this.offset != null) {
      r.put("offset", this.offset); //$NON-NLS-1$
    }
    if (this.orderby != null) {
      r.put("orderby", this.orderby); //$NON-NLS-1$
    }
    if (this.order != null) {
      r.put("order", this.order); //$NON-NLS-1$
    }
    return r;
  }
}
