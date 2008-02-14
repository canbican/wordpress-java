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
    return isAdmin;
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
    return url;
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
    return blogid;
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
    return blogName;
  }

  /**
   * @param blogName the blogName to set
   */
  public void setBlogName(String blogName) {
    this.blogName = blogName;
  }
}
