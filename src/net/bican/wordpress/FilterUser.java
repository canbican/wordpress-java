/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright 2012-2015 Can Bican
 * <can@bican.net> See the file 'COPYING' in the distribution for licensing terms.
 */
package net.bican.wordpress;

import lombok.Getter;
import lombok.Setter;
import redstone.xmlrpc.XmlRpcStruct;

/**
 * Used for filtering users in getUsers()
 *
 * @author Can Bican
 */
@Getter
@Setter
public class FilterUser {
  Integer number;
  Integer offset;
  String order;
  String orderby;
  String role;
  String who;

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
