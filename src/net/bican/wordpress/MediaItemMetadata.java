/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress;

/**
 * Meta data of a media object in a MediaItem class
 *
 * @author Can Bican
 */
public class MediaItemMetadata extends XmlRpcMapped {
  String file;
  Integer height;
  PostThumbnailImageMeta image_meta;
  MediaItemSizes sizes;
  Integer width;
  
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
   * @return the image_meta
   */
  public final PostThumbnailImageMeta getImage_meta() {
    return this.image_meta;
  }
  
  /**
   * @return the sizes
   */
  public final MediaItemSizes getSizes() {
    return this.sizes;
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
   * @param image_meta
   *          the image_meta to set
   */
  public final void setImage_meta(final PostThumbnailImageMeta image_meta) {
    this.image_meta = image_meta;
  }
  
  /**
   * @param sizes
   *          the sizes to set
   */
  public final void setSizes(final MediaItemSizes sizes) {
    this.sizes = sizes;
  }
  
  /**
   * @param width
   *          the width to set
   */
  public final void setWidth(final Integer width) {
    this.width = width;
  }
}
