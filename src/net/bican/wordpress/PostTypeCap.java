/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress;

/**
 * Capabilities for a post type
 *
 * @author Can Bican
 */
public class PostTypeCap extends XmlRpcMapped {
  String create_posts;
  String delete_others_posts;
  String delete_post;
  String delete_posts;
  String delete_private_posts;
  String delete_published_posts;
  String edit_others_posts;
  String edit_post;
  String edit_posts;
  String edit_private_posts;
  String edit_published_posts;
  String publish_posts;
  String read;
  String read_post;
  String read_private_posts;
  
  /**
   * @return the create_posts
   */
  public final String getCreate_posts() {
    return this.create_posts;
  }
  
  /**
   * @return the delete_others_posts
   */
  public final String getDelete_others_posts() {
    return this.delete_others_posts;
  }
  
  /**
   * @return the delete_post
   */
  public final String getDelete_post() {
    return this.delete_post;
  }
  
  /**
   * @return the delete_posts
   */
  public final String getDelete_posts() {
    return this.delete_posts;
  }
  
  /**
   * @return the delete_private_posts
   */
  public final String getDelete_private_posts() {
    return this.delete_private_posts;
  }
  
  /**
   * @return the delete_published_posts
   */
  public final String getDelete_published_posts() {
    return this.delete_published_posts;
  }
  
  /**
   * @return the edit_others_posts
   */
  public final String getEdit_others_posts() {
    return this.edit_others_posts;
  }
  
  /**
   * @return the edit_post
   */
  public final String getEdit_post() {
    return this.edit_post;
  }
  
  /**
   * @return the edit_posts
   */
  public final String getEdit_posts() {
    return this.edit_posts;
  }
  
  /**
   * @return the edit_private_posts
   */
  public final String getEdit_private_posts() {
    return this.edit_private_posts;
  }
  
  /**
   * @return the edit_published_posts
   */
  public final String getEdit_published_posts() {
    return this.edit_published_posts;
  }
  
  /**
   * @return the publish_posts
   */
  public final String getPublish_posts() {
    return this.publish_posts;
  }
  
  /**
   * @return the read
   */
  public final String getRead() {
    return this.read;
  }
  
  /**
   * @return the read_post
   */
  public final String getRead_post() {
    return this.read_post;
  }
  
  /**
   * @return the read_private_posts
   */
  public final String getRead_private_posts() {
    return this.read_private_posts;
  }
  
  /**
   * @param create_posts
   *          the create_posts to set
   */
  public final void setCreate_posts(final String create_posts) {
    this.create_posts = create_posts;
  }
  
  /**
   * @param delete_others_posts
   *          the delete_others_posts to set
   */
  public final void setDelete_others_posts(final String delete_others_posts) {
    this.delete_others_posts = delete_others_posts;
  }
  
  /**
   * @param delete_post
   *          the delete_post to set
   */
  public final void setDelete_post(final String delete_post) {
    this.delete_post = delete_post;
  }
  
  /**
   * @param delete_posts
   *          the delete_posts to set
   */
  public final void setDelete_posts(final String delete_posts) {
    this.delete_posts = delete_posts;
  }
  
  /**
   * @param delete_private_posts
   *          the delete_private_posts to set
   */
  public final void setDelete_private_posts(final String delete_private_posts) {
    this.delete_private_posts = delete_private_posts;
  }
  
  /**
   * @param delete_published_posts
   *          the delete_published_posts to set
   */
  public final void setDelete_published_posts(
      final String delete_published_posts) {
    this.delete_published_posts = delete_published_posts;
  }
  
  /**
   * @param edit_others_posts
   *          the edit_others_posts to set
   */
  public final void setEdit_others_posts(final String edit_others_posts) {
    this.edit_others_posts = edit_others_posts;
  }
  
  /**
   * @param edit_post
   *          the edit_post to set
   */
  public final void setEdit_post(final String edit_post) {
    this.edit_post = edit_post;
  }
  
  /**
   * @param edit_posts
   *          the edit_posts to set
   */
  public final void setEdit_posts(final String edit_posts) {
    this.edit_posts = edit_posts;
  }
  
  /**
   * @param edit_private_posts
   *          the edit_private_posts to set
   */
  public final void setEdit_private_posts(final String edit_private_posts) {
    this.edit_private_posts = edit_private_posts;
  }
  
  /**
   * @param edit_published_posts
   *          the edit_published_posts to set
   */
  public final void setEdit_published_posts(final String edit_published_posts) {
    this.edit_published_posts = edit_published_posts;
  }
  
  /**
   * @param publish_posts
   *          the publish_posts to set
   */
  public final void setPublish_posts(final String publish_posts) {
    this.publish_posts = publish_posts;
  }
  
  /**
   * @param read
   *          the read to set
   */
  public final void setRead(final String read) {
    this.read = read;
  }
  
  /**
   * @param read_post
   *          the read_post to set
   */
  public final void setRead_post(final String read_post) {
    this.read_post = read_post;
  }
  
  /**
   * @param read_private_posts
   *          the read_private_posts to set
   */
  public final void setRead_private_posts(final String read_private_posts) {
    this.read_private_posts = read_private_posts;
  }
}
