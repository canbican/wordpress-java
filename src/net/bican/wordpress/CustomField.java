/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress;

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
   * @return id of custom field
   */
  public String getId() {
    return this.id;
  }
  
  /**
   * @return key of custom field
   */
  public String getKey() {
    return this.key;
  }
  
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
   * @return value of custom field
   */
  public String getValue() {
    return this.value;
  }
  
  /**
   * @param id
   *          id to set
   */
  public void setId(final String id) {
    this.id = id;
  }
  
  /**
   * @param key
   *          key to set
   */
  public void setKey(final String key) {
    this.key = key;
  }
  
  /**
   * @param value
   *          the value to set
   */
  public void setValue(final String value) {
    this.value = value;
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
    o.put("id", this.getId());
    o.put("key", this.getKey());
    o.put("value", this.getValue());
    return o.toString();
  }
}
