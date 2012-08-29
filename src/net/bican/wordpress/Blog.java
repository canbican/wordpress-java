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
 * Class that keeps the information on a blog for a user
 * 
 * @author Can Bican &lt;can@bican.net&gt;
 * 
 */
public class Blog extends XmlRpcMapped {
  Boolean isAdmin;

  String url;

  String blogid;

  String blogName;

  /**
   * @return the isAdmin
   */
  public Boolean getIsAdmin() {
    return this.isAdmin;
  }

  /**
   * @param isAdmin the isAdmin to set
   */
  public void setIsAdmin(Boolean isAdmin) {
    this.isAdmin = isAdmin;
  }

  /**
   * @return the url
   */
  public String getUrl() {
    return this.url;
  }

  /**
   * @param url the url to set
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * @return the blogid
   */
  public String getBlogid() {
    return this.blogid;
  }

  /**
   * @param blogid the blogid to set
   */
  public void setBlogid(String blogid) {
    this.blogid = blogid;
  }

  /**
   * @return the blogName
   */
  public String getBlogName() {
    return this.blogName;
  }

  /**
   * @param blogName the blogName to set
   */
  public void setBlogName(String blogName) {
    this.blogName = blogName;
  }
}
