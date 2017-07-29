/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright 2012-2015 Can Bican
 * <can@bican.net> See the file 'COPYING' in the distribution for licensing terms.
 */
package net.bican.wordpress;

import lombok.Getter;
import lombok.Setter;
import redstone.xmlrpc.XmlRpcStruct;

/**
 * support list for a post type
 *
 * @author Can Bican
 */
@Getter
@Setter
public class Support extends XmlRpcMapped {
  private static final String CUSTOM_FIELDS_FIELD = "custom-fields"; //$NON-NLS-1$
  private static final String POST_FORMATS_FIELD = "post-formats"; //$NON-NLS-1$
  Boolean author;
  Boolean comments;
  Boolean customFields;
  Boolean editor;
  Boolean excerpt;
  Boolean postFormats;
  Boolean revisions;
  Boolean thumbnail;
  Boolean title;
  Boolean trackbacks;

  @Override
  public void fromXmlRpcStruct(final XmlRpcStruct x) {
    super.fromXmlRpcStruct(x);
    final Boolean pf = (Boolean) x.get(POST_FORMATS_FIELD);
    if (pf != null) {
      this.setPostFormats(pf);
    }
    final Boolean pf2 = (Boolean) x.get(CUSTOM_FIELDS_FIELD);
    if (pf2 != null) {
      this.setCustomFields(pf2);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public XmlRpcStruct toXmlRpcStruct() {
    final XmlRpcStruct result = super.toXmlRpcStruct();
    if (this.isPostFormats() != null) {
      result.put(POST_FORMATS_FIELD, this.isPostFormats());
    }
    if (this.isCustomFields() != null) {
      result.put(CUSTOM_FIELDS_FIELD, this.isCustomFields());
    }
    return result;
  }

  /**
   * @return if it's custom fields
   */
  public Boolean isCustomFields() {
    return this.getCustomFields();
  }

  /**
   * @return if it's post formats
   */
  public Boolean isPostFormats() {
    return this.getPostFormats();
  }
}
