/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress;

import java.util.Date;
import java.util.List;

import net.bican.wordpress.util.StringHeader;

/**
 * Class that keeps user information from blogger style calls
 *
 * @author Can Bican
 */
public class User extends XmlRpcMapped implements StringHeader {
  String bio;
  String display_name;
  String email;
  String first_name;
  String last_name;
  String nicename;
  String nickname;
  Date registered;
  List<String> roles;
  String url;
  Integer user_id;
  String username;
  
  /**
   * @return the bio
   */
  public final String getBio() {
    return this.bio;
  }
  
  /**
   * @return the display_name
   */
  public final String getDisplay_name() {
    return this.display_name;
  }
  
  /**
   * @return the email
   */
  public final String getEmail() {
    return this.email;
  }
  
  /**
   * @return the first_name
   */
  public final String getFirst_name() {
    return this.first_name;
  }
  
  /**
   * @return the last_name
   */
  public final String getLast_name() {
    return this.last_name;
  }
  
  /**
   * @return the nicename
   */
  public final String getNicename() {
    return this.nicename;
  }
  
  /**
   * @return the nickname
   */
  public final String getNickname() {
    return this.nickname;
  }
  
  /**
   * @return the registered
   */
  public final Date getRegistered() {
    return this.registered;
  }
  
  /**
   * @return the roles
   */
  public final List<String> getRoles() {
    return this.roles;
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
    return "First name" + TAB + "Last name" + TAB + "Nick name" + TAB + "Url"
        + TAB + "User ID";
  }
  
  /**
   * @return the url
   */
  public final String getUrl() {
    return this.url;
  }
  
  /**
   * @return the user_id
   */
  public final Integer getUser_id() {
    return this.user_id;
  }
  
  /**
   * @return the username
   */
  public final String getUsername() {
    return this.username;
  }
  
  /**
   * @param bio
   *          the bio to set
   */
  public final void setBio(final String bio) {
    this.bio = bio;
  }
  
  /**
   * @param display_name
   *          the display_name to set
   */
  public final void setDisplay_name(final String display_name) {
    this.display_name = display_name;
  }
  
  /**
   * @param email
   *          the email to set
   */
  public final void setEmail(final String email) {
    this.email = email;
  }
  
  /**
   * @param first_name
   *          the first_name to set
   */
  public final void setFirst_name(final String first_name) {
    this.first_name = first_name;
  }
  
  /**
   * @param last_name
   *          the last_name to set
   */
  public final void setLast_name(final String last_name) {
    this.last_name = last_name;
  }
  
  /**
   * @param nicename
   *          the nicename to set
   */
  public final void setNicename(final String nicename) {
    this.nicename = nicename;
  }
  
  /**
   * @param nickname
   *          the nickname to set
   */
  public final void setNickname(final String nickname) {
    this.nickname = nickname;
  }
  
  /**
   * @param registered
   *          the registered to set
   */
  public final void setRegistered(final Date registered) {
    this.registered = registered;
  }
  
  /**
   * @param roles
   *          the roles to set
   */
  public final void setRoles(final List<String> roles) {
    this.roles = roles;
  }
  
  /**
   * @param url
   *          the url to set
   */
  public final void setUrl(final String url) {
    this.url = url;
  }
  
  /**
   * @param user_id
   *          the user_id to set
   */
  public final void setUser_id(final Integer user_id) {
    this.user_id = user_id;
  }
  
  /**
   * @param username
   *          the username to set
   */
  public final void setUsername(final String username) {
    this.username = username;
  }
}
