/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress;

/**
 * Class that represents a wordpress attachment.
 *
 * @author Can Bican
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
   * @param bits
   *          the bits to set
   */
  public void setBits(final byte[] bits) {
    this.bits = bits;
  }
  
  /**
   * @param name
   *          the name to set
   */
  public void setName(final String name) {
    this.name = name;
  }
  
  /**
   * @param overwrite
   *          the overwrite to set
   */
  public void setOverwrite(final Boolean overwrite) {
    this.overwrite = overwrite;
  }
  
  /**
   * @param type
   *          the type to set
   */
  public void setType(final String type) {
    this.type = type;
  }
}
