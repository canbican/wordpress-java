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
 * Category object for a blog.
 * 
 * @author Can Bican &lt;can@bican.net&gt;
 * 
 */
public class Category extends XmlRpcMapped implements StringHeader {
  String categoryId = null;

  String categoryName = null;

  String description = null;

  String htmlUrl = null;

  String parentId = null;

  String rssUrl = null;

  /**
   * @return the categoryId
   */
  public String getCategoryId() {
    return this.categoryId;
  }

  /**
   * @return the categoryName
   */
  public String getCategoryName() {
    return this.categoryName;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * @return the htmlUrl
   */
  public String getHtmlUrl() {
    return this.htmlUrl;
  }

  /**
   * @return the parentId
   */
  public String getParentId() {
    return this.parentId;
  }

  /**
   * @return the rssUrl
   */
  public String getRssUrl() {
    return this.rssUrl;
  }

  /**
   * @param categoryId the categoryId to set
   */
  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  /**
   * @param categoryName the categoryName to set
   */
  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @param htmlUrl the htmlUrl to set
   */
  public void setHtmlUrl(String htmlUrl) {
    this.htmlUrl = htmlUrl;
  }

  /**
   * @param parentId the parentId to set
   */
  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  /**
   * @param rssUrl the rssUrl to set
   */
  public void setRssUrl(String rssUrl) {
    this.rssUrl = rssUrl;
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
    return "Category Id" + TAB + "Category Name" + TAB + "Description" + TAB
        + "Html Url" + TAB + "Parent Id" + TAB + "Rss Url";
  }
}
