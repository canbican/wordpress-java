/*
 * 
 * Wordpress-java
 * https://github.com/canbican/wordpress-java/
 * 
 * Copyright 2012-2015 Can Bican <can@bican.net>
 * See the file 'COPYING' in the distribution for licensing terms.
 * 
 */
package net.bican.wordpress;

import redstone.xmlrpc.XmlRpcStruct;

/**
 * support list for a post type
 * 
 * @author Can Bican
 */
public class Support extends XmlRpcMapped {
  private static final String CUSTOM_FIELDS_FIELD = "custom-fields"; //$NON-NLS-1$
  private static final String POST_FORMATS_FIELD = "post-formats"; //$NON-NLS-1$
  Boolean editor;
  Boolean thumbnail;
  Boolean comments;
  Boolean trackbacks;
  Boolean author;
  Boolean revisions;
  Boolean postFormats;
  Boolean title;
  Boolean excerpt;
  Boolean customFields;
  
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
   * @return the editor
   */
  public final Boolean isEditor() {
    return this.editor;
  }
  
  /**
   * @param editor
   *          the editor to set
   */
  public final void setEditor(final Boolean editor) {
    this.editor = editor;
  }
  
  /**
   * @return the thumbnail
   */
  public final Boolean isThumbnail() {
    return this.thumbnail;
  }
  
  /**
   * @param thumbnail
   *          the thumbnail to set
   */
  public final void setThumbnail(final Boolean thumbnail) {
    this.thumbnail = thumbnail;
  }
  
  /**
   * @return the comments
   */
  public final Boolean isComments() {
    return this.comments;
  }
  
  /**
   * @param comments
   *          the comments to set
   */
  public final void setComments(final Boolean comments) {
    this.comments = comments;
  }
  
  /**
   * @return the trackbacks
   */
  public final Boolean isTrackbacks() {
    return this.trackbacks;
  }
  
  /**
   * @param trackbacks
   *          the trackbacks to set
   */
  public final void setTrackbacks(final Boolean trackbacks) {
    this.trackbacks = trackbacks;
  }
  
  /**
   * @return the author
   */
  public final Boolean isAuthor() {
    return this.author;
  }
  
  /**
   * @param author
   *          the author to set
   */
  public final void setAuthor(final Boolean author) {
    this.author = author;
  }
  
  /**
   * @return the revisions
   */
  public final Boolean isRevisions() {
    return this.revisions;
  }
  
  /**
   * @param revisions
   *          the revisions to set
   */
  public final void setRevisions(final Boolean revisions) {
    this.revisions = revisions;
  }
  
  /**
   * @return the postFormats
   */
  public final Boolean isPostFormats() {
    return this.postFormats;
  }
  
  /**
   * @param postFormats
   *          the postFormats to set
   */
  public final void setPostFormats(final Boolean postFormats) {
    this.postFormats = postFormats;
  }
  
  /**
   * @return the title
   */
  public final Boolean isTitle() {
    return this.title;
  }
  
  /**
   * @param title
   *          the title to set
   */
  public final void setTitle(final Boolean title) {
    this.title = title;
  }
  
  /**
   * @return the excerpt
   */
  public final Boolean isExcerpt() {
    return this.excerpt;
  }
  
  /**
   * @param excerpt
   *          the excerpt to set
   */
  public final void setExcerpt(final Boolean excerpt) {
    this.excerpt = excerpt;
  }
  
  /**
   * @return the customFields
   */
  public final Boolean isCustomFields() {
    return this.customFields;
  }
  
  /**
   * @param customFields
   *          the customFields to set
   */
  public final void setCustomFields(final Boolean customFields) {
    this.customFields = customFields;
  }
}
