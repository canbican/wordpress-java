/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright 2012-2015 Can Bican
 * <can@bican.net> See the file 'COPYING' in the distribution for licensing terms.
 */
package net.bican.wordpress.exceptions;

import redstone.xmlrpc.XmlRpcFault;

/**
 * Signals a failure because the requested object is not in the blog
 *
 * @author Can Bican
 */
public class ObjectNotFoundException extends Exception {
  private static final long serialVersionUID = -7021698699407436868L;

  /**
   * @param e argument value that caused the exception
   */
  public ObjectNotFoundException(final XmlRpcFault e) {
    super(e);
  }

}
