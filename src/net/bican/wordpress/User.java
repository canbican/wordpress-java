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
 * Class that keeps user information from blogger style calls
 * 
 * @author Can Bican &lt;can@bican.net&gt;
 * 
 */
public class User extends XmlRpcMapped implements StringHeader {
  String nickname;

  Integer userid;

  String url;

  String lastname;

  String firstname;

  /**
   * (non-Javadoc)
   * 
   * @see net.bican.wordpress.StringHeader#getStringHeader()
   */
  @Override
  @SuppressWarnings("nls")
  public String getStringHeader() {
    final String TAB = ":";
    return "First name" + TAB + "Last name" + TAB + "Nick name" + TAB + "Url"
        + TAB + "User ID";
  }

  /**
   * @return the nickname
   */
  public String getNickname() {
    return this.nickname;
  }

  /**
   * @param nickname the nickname to set
   */
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  /**
   * @return the userid
   */
  public Integer getUserid() {
    return this.userid;
  }

  /**
   * @param userid the userid to set
   */
  public void setUserid(Integer userid) {
    this.userid = userid;
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
   * @return the lastname
   */
  public String getLastname() {
    return this.lastname;
  }

  /**
   * @param lastname the lastname to set
   */
  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  /**
   * @return the firstname
   */
  public String getFirstname() {
    return this.firstname;
  }

  /**
   * @param firstname the firstname to set
   */
  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

}
