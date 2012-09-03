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

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;

import redstone.xmlrpc.XmlRpcArray;
import redstone.xmlrpc.XmlRpcStruct;

/**
 * 
 * Static methods for parsing files
 * 
 * @author Can Bican <can@bican.net>
 * 
 */
public class FileParser {

  /**
   * 
   * Parses a file to create an xmlrpc compliant object
   * 
   * @param input
   *          reader for the file
   * @return New xmlrpc object
   * @throws IOException
   * @throws InvalidPostFormatException
   */
  @SuppressWarnings("nls")
  public static XmlRpcStruct parseFile(BufferedReader input)
      throws IOException, InvalidPostFormatException {
    XmlRpcStruct p = new XmlRpcStruct();
    String line;
    String prevKey = null;
    String prevValue = null;
    Pattern keyPattern;
    keyPattern = Pattern.compile("^[\\d\\w_]+:", Pattern.CASE_INSENSITIVE
        | Pattern.UNICODE_CASE);
    while ((line = input.readLine()) != null) {
      if ((!"".equals(line)) && (!line.startsWith("#"))) {
        Matcher m = keyPattern.matcher(line);
        if (m.find()) {
          if (prevKey != null) {
            FileParser.putVal(p, prevKey, prevValue);
          }
          String[] vals = line.split(":", 2);
          prevKey = vals[0];
          prevValue = vals[1];
        } else {
          if (prevValue != null) {
            prevValue += line;
          } else {
            throw new InvalidPostFormatException();
          }
        }
      }
    }
    if (prevKey != null)
      FileParser.putVal(p, prevKey, prevValue);
    return p;
  }

  /**
   * 
   * Fills values for an xmlrpc object
   * 
   * @param s
   *          xmlrpc object itself
   * @param key
   *          field name
   * @param v
   *          field value
   */
  @SuppressWarnings({ "unchecked", "rawtypes", "nls" })
  public static void putVal(XmlRpcStruct s, String key, String v) {
    String value = v.trim();
    if (value.startsWith("[")) {
      try {
        JSONArray jArr = new JSONArray(value);
        if (jArr.length() > 0) {
          Class cType = jArr.get(0).getClass();
          XmlRpcArray vals = new XmlRpcArray();
          if (cType == String.class) {
            for (int i = 0; i < jArr.length(); i++) {
              vals.add(jArr.getString(i));
            }
          } else {
            String className = FileParser.getClassName(key);
            Class<?> cl = Class.forName(className);
            for (int i = 0; i < jArr.length(); i++) {
              JSONConvertable o = (JSONConvertable) cl.newInstance();
              o.fromJSONObject(jArr.getJSONObject(i));
              vals.add(o);
            }
          }
          s.put(key, vals);
        }
      } catch (JSONException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (InstantiationException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } else {
      if (!"null".equalsIgnoreCase(value)) {
        s.put(key, value);
      }
    }
  }

  @SuppressWarnings("nls")
  static String getClassName(String key) {
    String className = key;
    while (className.contains("_")) {
      int pos = className.indexOf("_");
      Character ch = Character.valueOf(className.charAt(pos + 1));
      char chNew = Character.toUpperCase(ch.charValue());
      className = className.replaceFirst("_" + ch, chNew + "");
    }
    className = className.replaceFirst("s$", "");
    Character ch = Character.valueOf(className.charAt(0));
    char chNew = Character.toUpperCase(ch.charValue());
    className = className.replaceFirst("^" + ch, chNew + "");
    return "net.bican.wordpress." + className;
  }

}
