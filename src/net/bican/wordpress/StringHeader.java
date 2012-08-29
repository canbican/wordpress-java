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
 * Interface for supplying a header for tabular output in toString()
 * 
 * @author Can Bican &lt;can@bican.net&gt;
 * 
 */
public interface StringHeader {
  /**
   * @return Header list
   */
  public String getStringHeader();
}
