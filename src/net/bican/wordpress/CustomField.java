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
    return this.id;
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
    return this.key;
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
    return this.value;
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
  @Override
  @SuppressWarnings("nls")
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
