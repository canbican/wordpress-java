/*
 * 
 * Wordpress-java
 * https://github.com/canbican/wordpress-java/
 * 
 * Copyright 2012-2015 Can Bican <can@bican.net>
 * See the file 'COPYING' in the distribution for licensing terms.
 * 
 */
package net.bican.wordpress;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import redstone.xmlrpc.XmlRpcArray;
import redstone.xmlrpc.XmlRpcStruct;

/**
 * An abstract class for an object that has the capabilites of converting
 * to/from <code>XmlRpcStruct</code>.
 * 
 * @author Can Bican
 */
public abstract class XmlRpcMapped {
  
  @SuppressWarnings("nls")
  private static final SimpleDateFormat sdf = new SimpleDateFormat(
      "yyyyMMdd'T'HH:mm:ss");
      
  /**
   * (non-Javadoc)
   * 
   * @param recordDelimiter
   *          How to delimit records
   * @param fieldDelimiter
   *          How to delimit the key/value pairs
   * @param showFieldName
   *          Whether to show field name or not
   * @see java.lang.Object#toString()
   */
  @SuppressWarnings("nls")
  private String toGenericString(String recordDelimiter, String fieldDelimiter,
      boolean showFieldName) {
    String result = null;
    try {
      result = "";
      Field[] f = this.getClass().getDeclaredFields();
      for (Field field : f) {
        if (!Modifier.isStatic(field.getModifiers())) {
          Class<?> fType = field.getType();
          if (showFieldName)
            result += field.getName() + fieldDelimiter;
          if (fType == Date.class) {
            result += sdf.format(field.get(this));
          } else {
            result += field.get(this);
          }
          result += recordDelimiter;
        }
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
  @SuppressWarnings("nls")
  @Override
  public String toString() {
    return toGenericString("\n", ":", true);
  }
  
  /**
   * @return Something similar to toString() but in one line
   */
  @SuppressWarnings("nls")
  public String toOneLinerString() {
    return toGenericString(":", "", false).replaceAll(":$", "");
  }
  
  /**
   * @param x
   *          XmlRpcStruct to create the object from
   */
  @SuppressWarnings({ "nls", "unchecked" })
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
          try {
            XmlRpcMapped object = (XmlRpcMapped) kType.newInstance();
            object.fromXmlRpcStruct((XmlRpcStruct) v);
            field.set(this, object);
          } catch (InstantiationException | ClassCastException e) {
            if (kType == List.class) {
              XmlRpcArray vList = (XmlRpcArray) v;
              @SuppressWarnings("rawtypes")
              List result = new ArrayList();
              if (vList.size() > 0) {
                Class<? extends Object> gType = vList.get(0).getClass();
                Class<? extends XmlRpcMapped> clList;
                String[] typeStr = ((ParameterizedType) field.getGenericType())
                    .getActualTypeArguments()[0].toString().split(" ");
                String type = String.join(" ",
                    Arrays.copyOfRange(typeStr, 1, typeStr.length));
                try {
                  clList = (Class<? extends XmlRpcMapped>) Class.forName(type);
                  @SuppressWarnings("rawtypes")
                  Iterator it = vList.iterator();
                  while (it.hasNext()) {
                    if (gType == String.class) {
                      String itemToInsert = (String) it.next();
                      result.add(itemToInsert);
                    } else {
                      XmlRpcStruct item = (XmlRpcStruct) it.next();
                      try {
                        XmlRpcMapped itemToInsert = clList.newInstance();
                        clList.cast(itemToInsert);
                        itemToInsert.fromXmlRpcStruct(item);
                        result.add(itemToInsert);
                      } catch (InstantiationException e1) {
                        System.err.println("Warning: field \"" + k
                            + "\" contains invalid types in the response, skipping");
                      }
                    }
                  }
                  field.set(this, result);
                } catch (ClassNotFoundException e2) {
                  System.err.println("cannot find class \"" + type + "\"");
                }
              }
            } else if (kType == Integer.class) {
              if (v.getClass() != Integer.class) {
                Integer vInt = Integer.valueOf((String) v);
                field.set(this, vInt);
              } else {
                field.set(this, v);
              }
            } else if (kType == Double.class) {
              if (v.getClass() != Double.class) {
                Double vDouble = Double.valueOf((String) v);
                field.set(this, vDouble);
              } else {
                field.set(this, v);
              }
            } else if (kType == Date.class) {
              try {
                if (v.getClass() != Date.class) {
                  Date vDate = sdf.parse((String) v);
                  field.set(this, vDate);
                } else {
                  field.set(this, v);
                }
              } catch (ParseException e1) {
                throw new IllegalArgumentException(e1);
              }
            } else {
              if ((v instanceof Boolean) && (kType == String.class)) { // yes it
                                                                       // happens
                field.set(this, ((Boolean) v).toString());
              } else {
                if ((v instanceof String) && (((String) v).equals(""))
                    && (field.getType() != String.class)) {
                  field.set(this, null);
                } else {
                  field.set(this, v);
                }
              }
            }
          }
        }
      } catch (IllegalArgumentException e) {
        try {
          System.err.println("Warning: value \n\"" + v + "\"\nis invalid for \""
              + k + "\", setting it to \"null\"\nwhile parsing \""
              + field.getName() + "\":");
          e.printStackTrace();
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
