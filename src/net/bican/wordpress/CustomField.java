/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright 2012-2015 Can Bican
 * <can@bican.net> See the file 'COPYING' in the distribution for licensing terms.
 */
package net.bican.wordpress;

import org.json.JSONObject;

import lombok.Getter;
import lombok.Setter;
import net.bican.wordpress.util.StringHeader;

/**
 * This class represents custom fields in wordpress.
 *
 * @author alok
 */
@Getter
@Setter
public class CustomField extends XmlRpcMapped implements StringHeader {
  String id = null;
  String key = null;
  String value = null;

  @Override
  @SuppressWarnings("nls")
  public String getStringHeader() {
    final String TAB = ":";
    return "Id" + TAB + "Key" + TAB + "Value";
  }

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
