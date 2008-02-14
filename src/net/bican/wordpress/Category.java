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
    return categoryId;
  }

  /**
   * @return the categoryName
   */
  public String getCategoryName() {
    return categoryName;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @return the htmlUrl
   */
  public String getHtmlUrl() {
    return htmlUrl;
  }

  /**
   * @return the parentId
   */
  public String getParentId() {
    return parentId;
  }

  /**
   * @return the rssUrl
   */
  public String getRssUrl() {
    return rssUrl;
  }

  /**
   * @param categoryId
   *          the categoryId to set
   */
  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  /**
   * @param categoryName
   *          the categoryName to set
   */
  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  /**
   * @param description
   *          the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @param htmlUrl
   *          the htmlUrl to set
   */
  public void setHtmlUrl(String htmlUrl) {
    this.htmlUrl = htmlUrl;
  }

  /**
   * @param parentId
   *          the parentId to set
   */
  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  /**
   * @param rssUrl
   *          the rssUrl to set
   */
  public void setRssUrl(String rssUrl) {
    this.rssUrl = rssUrl;
  }

  /**
   * (non-Javadoc)
   * @see net.bican.wordpress.StringHeader#getStringHeader()
   */
  public String getStringHeader() {
    final String TAB = ":";
    return "Category Id" + TAB + "Category Name" + TAB + "Description" + TAB
        + "Html Url" + TAB + "Parent Id" + TAB + "Rss Url";
  }
}
