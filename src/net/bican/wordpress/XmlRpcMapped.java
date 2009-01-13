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

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import redstone.xmlrpc.XmlRpcStruct;

/**
 * 
 * An object that has the capabilites of converting to/from
 * <code>XmlRpcStruct</code>.
 * 
 * @author Can Bican &lt;can@bican.net&gt;
 * 
 */
public abstract class XmlRpcMapped {

  private static final SimpleDateFormat sdf = new SimpleDateFormat(
      "yyyyMMdd'T'HH:mm:ss");

  /**
   * (non-Javadoc)
   * 
   * @param recordDelimiter How to delimit records
   * @param fieldDelimiter How to delimit the key/value pairs
   * @param showFieldName Whether to show field name or not
   * 
   * @see java.lang.Object#toString()
   */
  private String toGenericString(String recordDelimiter, String fieldDelimiter,
      boolean showFieldName) {
    String result = null;
    try {
      result = "";
      Field[] f = this.getClass().getDeclaredFields();
      for (Field field : f) {
        if (showFieldName)
          result += field.getName() + fieldDelimiter;
        result += field.get(this) + recordDelimiter;
      }
    } catch (IllegalAccessException e) {
      // ignore and skip output
    }
    return result;
  }

  /**
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return toGenericString("\n", ":", true);
  }

  /**
   * @return Something similar to toString() but in one line
   */
  public String toOneLinerString() {
    return toGenericString(":", "", false).replaceAll(":$", "");
  }

  /**
   * @param x XmlRpcStruct to create the object from
   */
  public void fromXmlRpcStruct(XmlRpcStruct x) {
    Field[] f = this.getClass().getDeclaredFields();
    String k = null;
    Object v = null;
    for (Field field : f) {
      try {
        k = field.getName();
        v = x.get(k);
        Class<?> kType = field.getType();
        if (v != null) {
          if (kType == Integer.class) {
            Integer vInt = Integer.valueOf((String) v);
            field.set(this, vInt);
          } else if (kType == Date.class) {
            try {
              Date vDate = sdf.parse((String) v);
              field.set(this, vDate);
            } catch (ParseException e) {
              throw new IllegalArgumentException(e);
            }
          } else {
            field.set(this, v);
          }
        }
      } catch (IllegalArgumentException e) {
        try {
          System.err.println("Warning: value \"" + v + "\" is invalid for \""
              + k + "\", setting it to \"null\"");
          field.set(this, null);
        } catch (IllegalAccessException e1) {
          System.err.println(field.getName() + ":" + field.getType() + ":"
              + x.get(field.getName()));
          e1.printStackTrace();
        }
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * @return An XmlRpcStruct that represents the object.
   */
  @SuppressWarnings("unchecked")
  public XmlRpcStruct toXmlRpcStruct() {
    XmlRpcStruct result = new XmlRpcStruct();
    Field[] f = this.getClass().getDeclaredFields();
    for (Field field : f) {
      try {
        Object o = field.get(this);
        if (o != null) {
          result.put(field.getName(), o);
        }
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }
    return result;
  }
}
