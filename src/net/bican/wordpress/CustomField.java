/*
 * This file is part of jwordpress.
 *
 * jwordpress is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * jwordpress is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jwordpress.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.bican.wordpress;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class represents custom fields in wordpress.
 * 
 * @author alok
 * 
 */
public class CustomField extends XmlRpcMapped implements StringHeader,
    JSONConvertable {
  String id = null;

  String key = null;

  String value = null;

  /**
   * (non-Javadoc)
   * 
   * @see net.bican.wordpress.StringHeader#getStringHeader()
   */
  public String getStringHeader() {
    final String TAB = ":";
    return "Id" + TAB + "Key" + TAB + "Value";
  }

  /**
   * (non-Javadoc)
   * 
   * @see net.bican.wordpress.XmlRpcMapped#toString()
   */
  @Override
  public String toString() {
    JSONObject o = new JSONObject();
    try {
      o.put("id", this.getId());
      o.put("key", this.getKey());
      o.put("value", this.getValue());
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return o.toString();
  }

  /**
   * @return id of custom field
   */
  public String getId() {
    return id;
  }

  /**
   * @param id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return key of custom field
   */
  public String getKey() {
    return key;
  }

  /**
   * @param key
   */
  public void setKey(String key) {
    this.key = key;
  }

  /**
   * @return value of custom field
   */
  public String getValue() {
    return value;
  }

  /**
   * @param value
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * 
   * (non-Javadoc)
   * 
   * @see net.bican.wordpress.JSONConvertable#fromJSONObject(org.json.JSONObject)
   * 
   */
  public void fromJSONObject(JSONObject o) {
    try {
      this.setId(o.getString("id"));
    } catch (JSONException e) {
      this.setId(null);
    }
    try {
      this.setKey(o.getString("key"));
    } catch (JSONException e) {
      this.setKey(null);
    }
    try {
      this.setValue(o.getString("value"));
    } catch (JSONException e) {
      this.setValue(null);
    }
  }

}
