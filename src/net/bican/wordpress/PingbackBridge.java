/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress;

import redstone.xmlrpc.XmlRpcFault;

interface PingbackBridge {
  String ping(String pagelinkedfrom, String pagelinkedto) throws XmlRpcFault;
}
