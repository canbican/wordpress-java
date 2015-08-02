/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress;

import org.json.JSONException;
import org.json.JSONObject;

import net.bican.wordpress.util.StringHeader;

/**
 * This class represents custom fields in wordpress.
 * 
 * @author alok
 */
public class CustomField extends XmlRpcMapped implements StringHeader {
  String id = null;
  
  String key = null;
  
  String value = null;
  
  /**
   * (non-Javadoc)
   * 
   * @see net.bican.wordpress.util.StringHeader#getStringHeader()
   */
  @Override
  @SuppressWarnings("nls")
  public String getStringHeader() {
    final String TAB = ":";
    return "Id" + TAB + "Key" + TAB + "Value";
  }
  
  /**
   * (non-Javadoc)
   * 
   * @see net.bican.wordpress.XmlRpcMapped#toString()
   */
  @SuppressWarnings("nls")
  @Override
  public String toString() {
    final JSONObject o = new JSONObject();
    try {
      o.put("id", this.getId());
      o.put("key", this.getKey());
      o.put("value", this.getValue());
    } catch (final JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return o.toString();
  }
  
  /**
   * @return id of custom field
   */
  public String getId() {
    return this.id;
  }
  
  /**
   * @param id
   *          id to set
   */
  public void setId(final String id) {
    this.id = id;
  }
  
  /**
   * @return key of custom field
   */
  public String getKey() {
    return this.key;
  }
  
  /**
   * @param key
   *          key to set
   */
  public void setKey(final String key) {
    this.key = key;
  }
  
  /**
   * @return value of custom field
   */
  public String getValue() {
    return this.value;
  }
  
  /**
   * @param value
   *          the value to set
   */
  public void setValue(final String value) {
    this.value = value;
  }
}
