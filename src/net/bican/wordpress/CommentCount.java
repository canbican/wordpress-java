/*
 * 
 * Wordpress-java
 * http://code.google.com/p/wordpress-java/
 * 
 * Copyright 2012 Can Bican <can@bican.net>
 * See the file 'COPYING' in the distribution for licensing terms.
 * 
 */
package net.bican.wordpress;

/**
 * Comment Count object for a blog post.
 * 
 * @author Fred Potter &lt;fpoptter@gmail.com&gt;
 *
 */
public class CommentCount extends XmlRpcMapped implements StringHeader {
  
  Integer approved;
  Integer awaiting_moderation;
  Integer spam;
  Integer total_comments;
  
  /**
   * @return Number of approved comments.
   */
  public Integer getApproved() {
    return this.approved;
  }
  
  /**
   * @param approved Sets number of approved comments.
   */
  public void setApproved(Integer approved) {
    this.approved = approved;
  }

  /**
   * @return Number of comments awaiting moderation
   */
  public Integer getAwaiting_moderation() {
    return this.awaiting_moderation;
  }

  /**
   * @param awaitingModeration Number of comments awaiting moderation.
   */
  public void setAwaiting_moderation(Integer awaitingModeration) {
    this.awaiting_moderation = awaitingModeration;
  }

  /**
   * @return Number of comments marked as spam.
   */
  public Integer getSpam() {
    return this.spam;
  }

  /**
   * @param spam Number of comments that are spam
   */
  public void setSpam(Integer spam) {
    this.spam = spam;
  }

  /**
   * @return Total number of comments
   */
  public Integer getTotal_comments() {
    return this.total_comments;
  }

  /**
   * @param totalComments Total number of comments.
   */
  public void setTotal_comments(Integer totalComments) {
    this.total_comments = totalComments;
  }

  /**
   * (non-Javadoc)
   * 
   * @see net.bican.wordpress.StringHeader#getStringHeader()
   */
  @Override
  @SuppressWarnings("nls")
  public String getStringHeader() {
    final String TAB = ":";
    return "Approved" + TAB + "Awaiting Moderation" + TAB + "Spam" + TAB
        + "Total Comments";
  }
  
  
}
