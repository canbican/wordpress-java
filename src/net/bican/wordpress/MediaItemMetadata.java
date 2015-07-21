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

/**
 * Meta data of a media object in a MediaItem class
 * 
 * @author Can Bican
 */
public class MediaItemMetadata extends XmlRpcMapped {
  Integer width;
  Integer height;
  String file;
  MediaItemSizes sizes;
  PostThumbnailImageMeta image_meta;
  
  /**
   * @return the width
   */
  public final Integer getWidth() {
    return this.width;
  }
  
  /**
   * @param width
   *          the width to set
   */
  public final void setWidth(final Integer width) {
    this.width = width;
  }
  
  /**
   * @return the height
   */
  public final Integer getHeight() {
    return this.height;
  }
  
  /**
   * @param height
   *          the height to set
   */
  public final void setHeight(final Integer height) {
    this.height = height;
  }
  
  /**
   * @return the file
   */
  public final String getFile() {
    return this.file;
  }
  
  /**
   * @param file
   *          the file to set
   */
  public final void setFile(final String file) {
    this.file = file;
  }
  
  /**
   * @return the sizes
   */
  public final MediaItemSizes getSizes() {
    return this.sizes;
  }
  
  /**
   * @param sizes
   *          the sizes to set
   */
  public final void setSizes(final MediaItemSizes sizes) {
    this.sizes = sizes;
  }
  
  /**
   * @return the image_meta
   */
  public final PostThumbnailImageMeta getImage_meta() {
    return this.image_meta;
  }
  
  /**
   * @param image_meta
   *          the image_meta to set
   */
  public final void setImage_meta(final PostThumbnailImageMeta image_meta) {
    this.image_meta = image_meta;
  }
}
