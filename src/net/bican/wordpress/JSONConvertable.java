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

import org.json.JSONObject;

/**
 * 
 * Interface for objects that can be modified via JSON objects.
 * 
 * @author Can Bican &lt;can@bican.net&gt;
 * 
 */
public interface JSONConvertable {
  /**
   * @param jsonObject JSON object to get values from
   */
  public void fromJSONObject(JSONObject jsonObject);
}
