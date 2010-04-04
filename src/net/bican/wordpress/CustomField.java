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

/**
 * This class represents custom fields in wordpress.
 * 
 * @author alok
 * 
 */
public class CustomField extends XmlRpcMapped implements StringHeader {
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

}
