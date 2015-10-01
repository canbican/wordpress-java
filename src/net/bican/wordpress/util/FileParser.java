/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.bican.wordpress.exceptions.InvalidPostFormatException;
import redstone.xmlrpc.XmlRpcArray;
import redstone.xmlrpc.XmlRpcStruct;

/**
 * Static methods for parsing files
 *
 * @author Can Bican
 */
public class FileParser {
  private static final Logger logger = LoggerFactory
      .getLogger(FileParser.class);
      
  @SuppressWarnings("nls")
  static String getClassName(final String key) {
    String className = key;
    while (className.contains("_")) {
      final int pos = className.indexOf("_");
      final Character ch = Character.valueOf(className.charAt(pos + 1));
      final char chNew = Character.toUpperCase(ch.charValue());
      className = className.replaceFirst("_" + ch, chNew + "");
    }
    className = className.replaceFirst("s$", "");
    final Character ch = Character.valueOf(className.charAt(0));
    final char chNew = Character.toUpperCase(ch.charValue());
    className = className.replaceFirst("^" + ch, chNew + "");
    return "net.bican.wordpress." + className;
  }
  
  /**
   * Parses a file to create an xmlrpc compliant object
   *
   * @param input
   *          reader for the file
   * @return New xmlrpc object
   * @throws IOException
   *           when the file cannot be read
   * @throws InvalidPostFormatException
   *           when the file format is invalid
   */
  @SuppressWarnings("nls")
  public static XmlRpcStruct parseFile(final BufferedReader input)
      throws IOException, InvalidPostFormatException {
    final XmlRpcStruct p = new XmlRpcStruct();
    String line;
    String prevKey = null;
    String prevValue = null;
    Pattern keyPattern;
    keyPattern = Pattern.compile("^[\\d\\w_]+:",
        Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
    while ((line = input.readLine()) != null) {
      if (!"".equals(line) && !line.startsWith("#")) {
        final Matcher m = keyPattern.matcher(line);
        if (m.find()) {
          if (prevKey != null) {
            FileParser.putVal(p, prevKey, prevValue);
          }
          final String[] vals = line.split(":", 2);
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
    if (prevKey != null) {
      FileParser.putVal(p, prevKey, prevValue);
    }
    return p;
  }
  
  /**
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
  public static void putVal(final XmlRpcStruct s, final String key,
      final String v) {
    final String value = v.trim();
    if (value.startsWith("[")) {
      try {
        final JSONArray jArr = new JSONArray(value);
        if (jArr.length() > 0) {
          final Class cType = jArr.get(0).getClass();
          final XmlRpcArray vals = new XmlRpcArray();
          if (cType == String.class) {
            for (int i = 0; i < jArr.length(); i++) {
              vals.add(jArr.getString(i));
            }
          } else {
            final String className = FileParser.getClassName(key);
            final Class<?> cl = Class.forName(className);
            for (int i = 0; i < jArr.length(); i++) {
              final JSONConvertable o = (JSONConvertable) cl.newInstance();
              o.fromJSONObject(jArr.getJSONObject(i));
              vals.add(o);
            }
          }
          s.put(key, vals);
        }
      } catch (JSONException | ClassNotFoundException | InstantiationException
          | IllegalAccessException e) {
        logger.error("cannot put key {} value {}: {}", key, v,
            e.getLocalizedMessage());
      }
    } else {
      if (!"null".equalsIgnoreCase(value)) {
        s.put(key, value);
      }
    }
  }
  
}
