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

import net.bican.wordpress.util.StringHeader;

/**
 * 
 * Comment status names for the blog
 * 
 * @author Can Bican
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
   * @see net.bican.wordpress.util.StringHeader#getStringHeader()
   */
  @Override
  @SuppressWarnings("nls")
  public String getStringHeader() {
    final String TAB = ":";
    return "Name" + TAB + "Description";
  }

}
