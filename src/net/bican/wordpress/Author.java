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
    return display_name;
  }

  /**
   * User id of the author.
   * 
   * @return the user_id
   */
  public String getUser_id() {
    return user_id;
  }

  /**
   * Login name of the author.
   * 
   * @return the user_login
   */
  public String getUser_login() {
    return user_login;
  }

  /**
   * Display name of the author.
   * 
   * @param display_name  
   *          the display_name to set
   */
  public void setDisplay_name(String display_name) {
    this.display_name = display_name;
  }

  /**
   * User id of the author.
   * 
   * @param user_id
   *          the user_id to set
   */
  public void setUser_id(String user_id) {
    this.user_id = user_id;
  }

  /**
   * Login name of the author.
   * 
   * @param user_login
   *          the user_login to set
   */
  public void setUser_login(String user_login) {
    this.user_login = user_login;
  }

  /**
   * (non-Javadoc)
   * @see net.bican.wordpress.StringHeader#getStringHeader()
   */
  public String getStringHeader() {
    return "User_id:User_login:display_name";
  }
}
