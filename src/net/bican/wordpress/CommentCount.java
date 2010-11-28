/*
 * This file is part of jwordpress.
 *
 * jwordpress is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * jwordpress is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jwordpress.  If not, see <http://www.gnu.org/licenses/>.
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
  public String getStringHeader() {
    final String TAB = ":";
    return "Approved" + TAB + "Awaiting Moderation" + TAB + "Spam" + TAB
        + "Total Comments";
  }
  
  
}
