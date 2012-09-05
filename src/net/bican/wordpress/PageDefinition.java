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

import java.util.Date;

/**
 * 
 * A trimmed version of <code>Page</code> object, that is returned in lists
 * 
 * @author Can Bican &lt;can@bican.net&gt;
 * 
 */
public class PageDefinition extends XmlRpcMapped implements StringHeader {
  String page_title;

  String page_parent_id;

  Date dateCreated;

  Date date_created_gmt;

  /**
   * @return the page_title
   */
  public String getPage_title() {
    return this.page_title;
  }

  /**
   * @param page_title the page_title to set
   */
  public void setPage_title(String page_title) {
    this.page_title = page_title;
  }

  /**
   * @return the page_parent_id
   */
  public String getPage_parent_id() {
    return this.page_parent_id;
  }

  /**
   * @param page_parent_id the page_parent_id to set
   */
  public void setPage_parent_id(String page_parent_id) {
    this.page_parent_id = page_parent_id;
  }

  /**
   * @return the dateCreated
   */
  public Date getDateCreated() {
    return this.dateCreated;
  }

  /**
   * @param dateCreated the dateCreated to set
   */
  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  /**
   * @return the date_created_gmt
   */
  public Date getDate_created_gmt() {
    return this.date_created_gmt;
  }

  /**
   * @param date_created_gmt the date_created_gmt to set
   */
  public void setDate_created_gmt(Date date_created_gmt) {
    this.date_created_gmt = date_created_gmt;
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
    return "Date Created" + TAB + "Date Created(GMT)" + TAB + "Parent ID" + TAB
        + "Page Title";
  }

}
