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

/**
 * 
 * Class that represents a wordpress attachment.
 * 
 * @author Can Bican &lt;can@bican.net&gt;
 * 
 */
class Attachment extends XmlRpcMapped {
  byte[] bits;

  String name;

  Boolean overwrite;

  String type;

  /**
   * @return the bits
   */
  public byte[] getBits() {
    return this.bits;
  }

  /**
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * @return the overwrite
   */
  public Boolean getOverwrite() {
    return this.overwrite;
  }

  /**
   * @return the type
   */
  public String getType() {
    return this.type;
  }

  /**
   * @param bits the bits to set
   */
  public void setBits(byte[] bits) {
    this.bits = bits;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @param overwrite the overwrite to set
   */
  public void setOverwrite(Boolean overwrite) {
    this.overwrite = overwrite;
  }

  /**
   * @param type the type to set
   */
  public void setType(String type) {
    this.type = type;
  }
}
