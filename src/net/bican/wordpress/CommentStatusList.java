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
 * 
 * Comment status names for the blog
 * 
 * @author Can Bican <can@bican.net>
 *
 */
public class CommentStatusList extends XmlRpcMapped implements StringHeader {

  String hold;

  String approve;

  String spam;

  /**
   * @return hold description
   */
  public String getHold() {
    return this.hold;
  }

  /**
   * @return approved description
   */
  public String getApprove() {
    return this.approve;
  }

  /**
   * @return spam description
   */
  public String getSpam() {
    return this.spam;
  }

  /**
   * (non-Javadoc)
   * @see net.bican.wordpress.StringHeader#getStringHeader()
   */
  @Override
  @SuppressWarnings("nls")
  public String getStringHeader() {
    final String TAB = ":";
    return "Name" + TAB + "Description";
  }

}
