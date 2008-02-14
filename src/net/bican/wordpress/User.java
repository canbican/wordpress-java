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
 * 
 * Class that keeps user information from blogger style calls
 * 
 * @author Can Bican &lt;can@bican.net&gt;
 * 
 */
public class User extends XmlRpcMapped implements StringHeader {
  String nickname;
  String userid;
  String url;
  String lastname;
  String firstname;

  /**
   * (non-Javadoc)
   * 
   * @see net.bican.wordpress.StringHeader#getStringHeader()
   */
  public String getStringHeader() {
    final String TAB = ":";
    return "First name" + TAB + "Last name" + TAB + "Nick name" + TAB + "Url"
        + TAB + "User ID";
  }

  /**
   * @return the nickname
   */
  public String getNickname() {
    return nickname;
  }

  /**
   * @param nickname
   *          the nickname to set
   */
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  /**
   * @return the userid
   */
  public String getUserid() {
    return userid;
  }

  /**
   * @param userid
   *          the userid to set
   */
  public void setUserid(String userid) {
    this.userid = userid;
  }

  /**
   * @return the url
   */
  public String getUrl() {
    return url;
  }

  /**
   * @param url
   *          the url to set
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * @return the lastname
   */
  public String getLastname() {
    return lastname;
  }

  /**
   * @param lastname
   *          the lastname to set
   */
  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  /**
   * @return the firstname
   */
  public String getFirstname() {
    return firstname;
  }

  /**
   * @param firstname
   *          the firstname to set
   */
  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

}
