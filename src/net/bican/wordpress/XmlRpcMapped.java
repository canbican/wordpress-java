/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redstone.xmlrpc.XmlRpcArray;
import redstone.xmlrpc.XmlRpcStruct;

/**
 * An abstract class for an object that has the capabilites of converting
 * to/from <code>XmlRpcStruct</code>.
 *
 * @author Can Bican
 */
public abstract class XmlRpcMapped {
  
  private static final Logger logger = LoggerFactory
      .getLogger(XmlRpcMapped.class);
  @SuppressWarnings("nls")
  private static final SimpleDateFormat sdf = new SimpleDateFormat(
      "yyyyMMdd'T'HH:mm:ss");
      
  /**
   * @param x
   *          XmlRpcStruct to create the object from
   */
  @SuppressWarnings({ "nls", "unchecked" })
  public void fromXmlRpcStruct(final XmlRpcStruct x) {
    final Field[] f = this.getClass().getDeclaredFields();
    String k = null;
    Object v = null;
    for (final Field field : f) {
      try {
        k = field.getName();
        v = x.get(k);
        final Class<?> kType = field.getType();
        if (v != null) {
          try {
            final XmlRpcMapped object = (XmlRpcMapped) kType.newInstance();
            object.fromXmlRpcStruct((XmlRpcStruct) v);
            field.set(this, object);
          } catch (InstantiationException | ClassCastException e) {
            if (kType == List.class) {
              final XmlRpcArray vList = (XmlRpcArray) v;
              @SuppressWarnings("rawtypes")
              final List result = new ArrayList();
              if (vList.size() > 0) {
                final Class<? extends Object> gType = vList.get(0).getClass();
                Class<? extends XmlRpcMapped> clList;
                final String[] typeStr = ((ParameterizedType) field
                    .getGenericType()).getActualTypeArguments()[0].toString()
                        .split(" ");
                final String[] typeStrSub = Arrays.copyOfRange(typeStr, 1,
                    typeStr.length);
                final StringBuilder typeB = new StringBuilder();
                for (int i = 0, il = typeStrSub.length; i < il; i++) {
                  if (i > 0) {
                    typeB.append(" ");
                  }
                  typeB.append(typeStrSub[i]);
                }
                final String type = typeB.toString();
                try {
                  clList = (Class<? extends XmlRpcMapped>) Class.forName(type,
                      false, this.getClass().getClassLoader());
                  @SuppressWarnings("rawtypes")
                  final Iterator it = vList.iterator();
                  while (it.hasNext()) {
                    if (gType == String.class) {
                      final String itemToInsert = (String) it.next();
                      result.add(itemToInsert);
                    } else {
                      final XmlRpcStruct item = (XmlRpcStruct) it.next();
                      try {
                        final XmlRpcMapped itemToInsert = clList.newInstance();
                        clList.cast(itemToInsert);
                        itemToInsert.fromXmlRpcStruct(item);
                        result.add(itemToInsert);
                      } catch (final InstantiationException e1) {
                        logger.warn(
                            "field {} contains invalid types in response, skipping",
                            k);
                      }
                    }
                  }
                  field.set(this, result);
                } catch (final ClassNotFoundException e2) {
                  logger.error("cannot find class {}", type);
                }
              }
            } else if (kType == Integer.class) {
              if (v.getClass() != Integer.class) {
                final Integer vInt = Integer.valueOf((String) v);
                field.set(this, vInt);
              } else {
                field.set(this, v);
              }
            } else if (kType == Double.class) {
              if (v.getClass() != Double.class) {
                final Double vDouble = Double.valueOf((String) v);
                field.set(this, vDouble);
              } else {
                field.set(this, v);
              }
            } else if (kType == Date.class) {
              try {
                if (v.getClass() != Date.class) {
                  final Date vDate = sdf.parse((String) v);
                  field.set(this, vDate);
                } else {
                  field.set(this, v);
                }
              } catch (final ParseException e1) {
                throw new IllegalArgumentException(e1);
              }
            } else {
              if (v instanceof Boolean && kType == String.class) { // yes it
                                                                   // happens
                field.set(this, ((Boolean) v).toString());
              } else {
                if (v instanceof String && ((String) v).equals("")
                    && field.getType() != String.class) {
                  field.set(this, null);
                } else {
                  field.set(this, v);
                }
              }
            }
          }
        }
      } catch (final IllegalArgumentException e) {
        try {
          if ((v != null) && (v instanceof XmlRpcArray)
              && (((XmlRpcArray) v).size() != 0)) { // ugly hack but it will
                                                    // eventually change into
                                                    // another ugly hack
            logger.warn(
                "value {} is invalid for {}, setting it to null (while parsing {})",
                v, k, field.getName());
          }
          field.set(this, null);
        } catch (final IllegalAccessException e1) {
          logger.error("cannot set the field to null: {}",
              e.getLocalizedMessage());
        }
      } catch (final IllegalAccessException e) {
        logger.error("illegal access to object constructor: {}",
            e.getLocalizedMessage());
      }
    }
  }
  
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
  private String toGenericString(final String recordDelimiter,
      final String fieldDelimiter, final boolean showFieldName) {
    String result = null;
    try {
      result = "";
      final Field[] f = this.getClass().getDeclaredFields();
      for (final Field field : f) {
        if (!Modifier.isStatic(field.getModifiers())) {
          final Class<?> fType = field.getType();
          if (showFieldName) {
            result += field.getName() + fieldDelimiter;
          }
          if (fType == Date.class) {
            result += sdf.format(field.get(this));
          } else {
            result += field.get(this);
          }
          result += recordDelimiter;
        }
      }
    } catch (final IllegalAccessException e) {
      // ignore and skip output
    }
    return result;
  }
  
  /**
   * @return Something similar to toString() but in one line
   */
  @SuppressWarnings("nls")
  public String toOneLinerString() {
    return this.toGenericString(":", "", false).replaceAll(":$", "");
  }
  
  /**
   * (non-Javadoc)
   *
   * @see java.lang.Object#toString()
   */
  @SuppressWarnings("nls")
  @Override
  public String toString() {
    return this.toGenericString("\n", ":", true);
  }
  
  /**
   * @return An XmlRpcStruct that represents the object.
   */
  @SuppressWarnings("unchecked")
  public XmlRpcStruct toXmlRpcStruct() {
    final XmlRpcStruct result = new XmlRpcStruct();
    final Field[] f = this.getClass().getDeclaredFields();
    for (final Field field : f) {
      try {
        if (!Modifier.isStatic(field.getModifiers())) {
          final Object o = field.get(this);
          if (o != null) {
            result.put(field.getName(), o);
          }
        }
      } catch (final IllegalAccessException e) {
        logger.error("cannot receive field {}, {}", //$NON-NLS-1$
            field.getName(), e.getLocalizedMessage());
      }
    }
    return result;
  }
}
