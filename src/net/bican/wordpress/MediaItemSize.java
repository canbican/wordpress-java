/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress;

import redstone.xmlrpc.XmlRpcStruct;

/**
 * Class that holds media item info for different media items
 *
 * @author Can Bican
 */
public class MediaItemSize extends XmlRpcMapped {
  private static final String MIME_TYPE_FIELD = "mime-type"; //$NON-NLS-1$
  String file;
  Integer height;
  String mimeType;
  Integer width;
  
  @Override
  public void fromXmlRpcStruct(final XmlRpcStruct x) {
    super.fromXmlRpcStruct(x);
    this.setMimeType((String) x.get(MIME_TYPE_FIELD));
  }
  
  /**
   * @return the file
   */
  public final String getFile() {
    return this.file;
  }
  
  /**
   * @return the height
   */
  public final Integer getHeight() {
    return this.height;
  }
  
  /**
   * @return the mimeType
   */
  public final String getMimeType() {
    return this.mimeType;
  }
  
  /**
   * @return the width
   */
  public final Integer getWidth() {
    return this.width;
  }
  
  /**
   * @param file
   *          the file to set
   */
  public final void setFile(final String file) {
    this.file = file;
  }
  
  /**
   * @param height
   *          the height to set
   */
  public final void setHeight(final Integer height) {
    this.height = height;
  }
  
  /**
   * @param mimeType
   *          the mimeType to set
   */
  public final void setMimeType(final String mimeType) {
    this.mimeType = mimeType;
  }
  
  /**
   * @param width
   *          the width to set
   */
  public final void setWidth(final Integer width) {
    this.width = width;
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public XmlRpcStruct toXmlRpcStruct() {
    final XmlRpcStruct result = super.toXmlRpcStruct();
    result.put(MIME_TYPE_FIELD, this.getMimeType());
    return result;
  }
}
