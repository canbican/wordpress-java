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
 * Author object for a blog.
 * 
 * @author Can Bican &lt;can@bican.net&gt;
 * 
 */
public class Author extends XmlRpcMapped implements StringHeader {
  String display_name;

  String user_id;

  String user_login;

  /**
   * Display Name of the author.
   * 
   * @return the display_name
   */
  public String getDisplay_name() {
    return this.display_name;
  }

  /**
   * User id of the author.
   * 
   * @return the user_id
   */
  public String getUser_id() {
    return this.user_id;
  }

  /**
   * Login name of the author.
   * 
   * @return the user_login
   */
  public String getUser_login() {
    return this.user_login;
  }

  /**
   * Display name of the author.
   * 
   * @param display_name the display_name to set
   */
  public void setDisplay_name(String display_name) {
    this.display_name = display_name;
  }

  /**
   * User id of the author.
   * 
   * @param user_id the user_id to set
   */
  public void setUser_id(String user_id) {
    this.user_id = user_id;
  }

  /**
   * Login name of the author.
   * 
   * @param user_login the user_login to set
   */
  public void setUser_login(String user_login) {
    this.user_login = user_login;
  }

  /**
   * (non-Javadoc)
   * 
   * @see net.bican.wordpress.StringHeader#getStringHeader()
   */
  @Override
  @SuppressWarnings("nls")
  public String getStringHeader() {
    return "User_id:User_login:display_name";
  }
}
