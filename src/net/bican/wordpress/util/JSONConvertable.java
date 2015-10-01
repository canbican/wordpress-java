/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress.util;

import org.json.JSONObject;

/**
 * Interface for objects that can be modified via JSON objects.
 *
 * @author Can Bican
 */
public interface JSONConvertable {
  /**
   * @param jsonObject
   *          JSON object to get values from
   */
  public void fromJSONObject(JSONObject jsonObject);
}
