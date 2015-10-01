/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress;

/**
 * Class that keeps info for different sizes of a media item
 *
 * @author Can Bican
 */
public class MediaItemSizes extends XmlRpcMapped {
  MediaItemSize large;
  MediaItemSize medium;
  MediaItemSize post_thumbnail;
  MediaItemSize thumbnail;
  
  /**
   * @return the large
   */
  public final MediaItemSize getLarge() {
    return this.large;
  }
  
  /**
   * @return the medium
   */
  public final MediaItemSize getMedium() {
    return this.medium;
  }
  
  /**
   * @return the post_thumbnail
   */
  public final MediaItemSize getPost_thumbnail() {
    return this.post_thumbnail;
  }
  
  /**
   * @return the thumbnail
   */
  public final MediaItemSize getThumbnail() {
    return this.thumbnail;
  }
  
  /**
   * @param large
   *          the large to set
   */
  public final void setLarge(final MediaItemSize large) {
    this.large = large;
  }
  
  /**
   * @param medium
   *          the medium to set
   */
  public final void setMedium(final MediaItemSize medium) {
    this.medium = medium;
  }
  
  /**
   * @param post_thumbnail
   *          the post_thumbnail to set
   */
  public final void setPost_thumbnail(final MediaItemSize post_thumbnail) {
    this.post_thumbnail = post_thumbnail;
  }
  
  /**
   * @param thumbnail
   *          the thumbnail to set
   */
  public final void setThumbnail(final MediaItemSize thumbnail) {
    this.thumbnail = thumbnail;
  }
}
