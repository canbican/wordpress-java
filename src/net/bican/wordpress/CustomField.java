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
