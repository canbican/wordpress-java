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
 * The class for keeping user blog entries
 * 
 * @author Can Bican
 */
public class UserBlog extends XmlRpcMapped {
  Integer blogid;
  String blogName;
  String url;
  String xmlrpc;
  boolean isAdmin;
  
  /**
   * @return the blog id
   */
  public Integer getBlogid() {
    return this.blogid;
  }
  
  /**
   * @param blogid
   *          blog id to set
   */
  public void setBlogid(final Integer blogid) {
    this.blogid = blogid;
  }
  
  /**
   * @return the blog name
   */
  public String getBlogName() {
    return this.blogName;
  }
  
  /**
   * @param blogName
   *          blog name to set
   */
  public void setBlogName(final String blogName) {
    this.blogName = blogName;
  }
  
  /**
   * @return the url
   */
  public String getUrl() {
    return this.url;
  }
  
  /**
   * @param url
   *          url to set
   */
  public void setUrl(final String url) {
    this.url = url;
  }
  
  /**
   * @return the xml rpc url
   */
  public String getXmlrpc() {
    return this.xmlrpc;
  }
  
  /**
   * @param xmlrpc
   *          xml rpc url to set
   */
  public void setXmlrpc(final String xmlrpc) {
    this.xmlrpc = xmlrpc;
  }
  
  /**
   * @return the admin status
   */
  public boolean isAdmin() {
    return this.isAdmin;
  }
  
  /**
   * @param isAdmin
   *          admin status to set
   */
  public void setAdmin(final boolean isAdmin) {
    this.isAdmin = isAdmin;
  }
  
}
