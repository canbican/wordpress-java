/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress;

import net.bican.wordpress.util.StringHeader;

/**
 * Comment Count object for a blog post.
 *
 * @author Fred Potter
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
   * @return Number of comments awaiting moderation
   */
  public Integer getAwaiting_moderation() {
    return this.awaiting_moderation;
  }

  /**
   * @return Number of comments marked as spam.
   */
  public Integer getSpam() {
    return this.spam;
  }

  /**
   * (non-Javadoc)
   *
   * @see net.bican.wordpress.util.StringHeader#getStringHeader()
   */
  @Override
  @SuppressWarnings("nls")
  public String getStringHeader() {
    final String TAB = ":";
    return "Approved" + TAB + "Awaiting Moderation" + TAB + "Spam" + TAB
        + "Total Comments";
  }

  /**
   * @return Total number of comments
   */
  public Integer getTotal_comments() {
    return this.total_comments;
  }

  /**
   * @param approved
   *          Sets number of approved comments.
   */
  public void setApproved(final Integer approved) {
    this.approved = approved;
  }

  /**
   * @param awaitingModeration
   *          Number of comments awaiting moderation.
   */
  public void setAwaiting_moderation(final Integer awaitingModeration) {
    this.awaiting_moderation = awaitingModeration;
  }

  /**
   * @param spam
   *          Number of comments that are spam
   */
  public void setSpam(final Integer spam) {
    this.spam = spam;
  }

  /**
   * @param totalComments
   *          Total number of comments.
   */
  public void setTotal_comments(final Integer totalComments) {
    this.total_comments = totalComments;
  }

}
