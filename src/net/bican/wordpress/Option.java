/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress;

import net.bican.wordpress.util.StringHeader;

/**
 * Option object for a blog.
 *
 * @author Can Bican
 */
public class Option extends XmlRpcMapped implements StringHeader {
  String desc;
  String name;
  Boolean readonly;
  String value;
  
  /**
   * @return the description
   */
  public String getDesc() {
    return this.desc;
  }
  
  /**
   * @return the name
   */
  public String getName() {
    return this.name;
  }
  
  @Override
  public String getStringHeader() {
    return ""; //$NON-NLS-1$
  }
  
  /**
   * @return the value
   */
  public String getValue() {
    return this.value;
  }
  
  /**
   * @return read-only status
   */
  public Boolean isReadonly() {
    return this.readonly;
  }
  
  /**
   * @param desc
   *          description to set
   */
  public void setDesc(final String desc) {
    this.desc = desc;
  }
  
  /**
   * @param name
   *          name to set
   */
  public void setName(final String name) {
    this.name = name;
  }
  
  /**
   * @param readonly
   *          read-only status to set
   */
  public void setReadonly(final Boolean readonly) {
    this.readonly = readonly;
  }
  
  /**
   * @param value
   *          value to set
   */
  public void setValue(final String value) {
    this.value = value;
  }
  
}
