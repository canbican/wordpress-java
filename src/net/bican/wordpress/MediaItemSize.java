/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright 2012-2015 Can Bican
 * <can@bican.net> See the file 'COPYING' in the distribution for licensing terms.
 */
package net.bican.wordpress;

import lombok.Getter;
import lombok.Setter;
import redstone.xmlrpc.XmlRpcStruct;

/**
 * Class that holds media item info for different media items
 *
 * @author Can Bican
 */
@Getter
@Setter
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

  @SuppressWarnings("unchecked")
  @Override
  public XmlRpcStruct toXmlRpcStruct() {
    final XmlRpcStruct result = super.toXmlRpcStruct();
    result.put(MIME_TYPE_FIELD, this.getMimeType());
    return result;
  }
}
