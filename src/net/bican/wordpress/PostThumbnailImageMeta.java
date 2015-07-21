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
 * Class for keeping image meta data
 * 
 * @author Can Bican
 */
public class PostThumbnailImageMeta extends XmlRpcMapped {
  Double aperture; // TODO wordpress codex tells it's integer but should be
                   // double
  String credit;
  String camera;
  String caption;
  Integer created_timestamp;
  String copyright;
  Double focal_length; // TODO wordpress codex tells it's integer but exif
                       // accepts doubles
  String iso;
  Double shutter_speed;// TODO wordpress codex tells it's integer but exif
                       // accepts doubles
  String title;
  Integer orientation; // wordpress codex does not include this TODO
  
  /**
   * @return the aperture
   */
  public final Double getAperture() {
    return this.aperture;
  }
  
  /**
   * @param aperture
   *          the aperture to set
   */
  public final void setAperture(final Double aperture) {
    this.aperture = aperture;
  }
  
  /**
   * @return the credit
   */
  public final String getCredit() {
    return this.credit;
  }
  
  /**
   * @param credit
   *          the credit to set
   */
  public final void setCredit(final String credit) {
    this.credit = credit;
  }
  
  /**
   * @return the camera
   */
  public final String getCamera() {
    return this.camera;
  }
  
  /**
   * @param camera
   *          the camera to set
   */
  public final void setCamera(final String camera) {
    this.camera = camera;
  }
  
  /**
   * @return the caption
   */
  public final String getCaption() {
    return this.caption;
  }
  
  /**
   * @param caption
   *          the caption to set
   */
  public final void setCaption(final String caption) {
    this.caption = caption;
  }
  
  /**
   * @return the created_timestamp
   */
  public final Integer getCreated_timestamp() {
    return this.created_timestamp;
  }
  
  /**
   * @param created_timestamp
   *          the created_timestamp to set
   */
  public final void setCreated_timestamp(final Integer created_timestamp) {
    this.created_timestamp = created_timestamp;
  }
  
  /**
   * @return the copyright
   */
  public final String getCopyright() {
    return this.copyright;
  }
  
  /**
   * @param copyright
   *          the copyright to set
   */
  public final void setCopyright(final String copyright) {
    this.copyright = copyright;
  }
  
  /**
   * @return the focal_length
   */
  public final Double getFocal_length() {
    return this.focal_length;
  }
  
  /**
   * @param focal_length
   *          the focal_length to set
   */
  public final void setFocal_length(final Double focal_length) {
    this.focal_length = focal_length;
  }
  
  /**
   * @return the iso
   */
  public final String getIso() {
    return this.iso;
  }
  
  /**
   * @param iso
   *          the iso to set
   */
  public final void setIso(final String iso) {
    this.iso = iso;
  }
  
  /**
   * @return the shutter_speed
   */
  public final Double getShutter_speed() {
    return this.shutter_speed;
  }
  
  /**
   * @param shutter_speed
   *          the shutter_speed to set
   */
  public final void setShutter_speed(final Double shutter_speed) {
    this.shutter_speed = shutter_speed;
  }
  
  /**
   * @return the title
   */
  public final String getTitle() {
    return this.title;
  }
  
  /**
   * @param title
   *          the title to set
   */
  public final void setTitle(final String title) {
    this.title = title;
  }
  
  /**
   * @return the orientation
   */
  public final Integer getOrientation() {
    return this.orientation;
  }
  
  /**
   * @param orientation
   *          the orientation to set
   */
  public final void setOrientation(final Integer orientation) {
    this.orientation = orientation;
  }
}
