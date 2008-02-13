package net.bican.wordpress;

import java.lang.reflect.Field;

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

  /**
   * (non-Javadoc)
   * 
   * @param recordDelimiter
   *          How to delimit records
   * @param fieldDelimiter
   *          How to delimit the key/value pairs
   * @param showFieldName
   *          Whether to show field name or not
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
   * @param x
   *          XmlRpcStruct to create the object from
   */
  @SuppressWarnings("unchecked")
  public void fromXmlRpcStruct(XmlRpcStruct x) {
    Field[] f = this.getClass().getDeclaredFields();
    for (Field field : f) {
      try {
        String k = field.getName();
        Object v = x.get(k);
        if (v != null)
          field.set(this, v);
      } catch (IllegalArgumentException e) {
        try {
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
