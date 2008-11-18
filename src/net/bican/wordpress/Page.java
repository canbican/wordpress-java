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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import redstone.xmlrpc.XmlRpcArray;
import redstone.xmlrpc.XmlRpcStruct;

/**
 * 
 * Page object for a blog.
 * 
 * @author Can Bican &lt;can@bican.net&gt;
 * 
 */
public class Page extends XmlRpcMapped implements StringHeader {
  /**
   * 
   * Generates a new page class from the given file
   * 
   * @param file
   *          file that contains the page information
   * @return new Page object
   * @throws IOException
   * @throws InvalidPostFormatException
   */
  @SuppressWarnings("unchecked")
  public static Page fromFile(File file) throws IOException,
      InvalidPostFormatException {
    XmlRpcStruct page = null;
    page = new XmlRpcStruct();
    BufferedReader input = new BufferedReader(new FileReader(file));
    String line;
    String prevKey = null;
    String prevValue = null;
    Pattern keyPattern;
    keyPattern = Pattern.compile("^[\\d\\w_]+:", Pattern.CASE_INSENSITIVE
        | Pattern.UNICODE_CASE);
    while ((line = input.readLine()) != null) {
      if ((!"".equals(line)) && (!line.startsWith("#"))) {
        Matcher m = keyPattern.matcher(line);
        if (m.find()) {
          if (prevKey != null) {
            putVal(page, prevKey, prevValue);
          }
          String[] vals = line.split(":", 2);
          prevKey = vals[0];
          prevValue = vals[1];
        } else {
          if (prevValue != null) {
            prevValue += line;
          } else {
            throw new InvalidPostFormatException();
          }
        }
      }
    }
    if (prevKey != null)
      putVal(page, prevKey, prevValue);
    Page result = new Page();
    result.fromXmlRpcStruct(page);
    return result;
  }

  @SuppressWarnings("unchecked")
  private static void putVal(XmlRpcStruct s, String key, String v) {
    String value = v.trim();
    if (v.startsWith("[")) {
      value = value.replaceFirst("\\[", "");
      value = value.replaceFirst("\\]$", "");
      value = value.replaceAll(",  *", ",");
      value = value.trim();
      String[] valStr = value.split(",");
      XmlRpcArray vals = new XmlRpcArray();
      for (String s1 : valStr) {
        vals.add(s1);
      }
      s.put(key, vals);
    } else {
      if (!"null".equalsIgnoreCase(value)) {
        s.put(key, value);
      }
    }
  }

  XmlRpcArray categories;
  Date date_created_gmt;
  Date dateCreated;
  String description;
  String excerpt;
  String link;
  Integer mt_allow_comments;
  Integer mt_allow_pings;
  String page_id;
  String page_status;
  String permaLink;
  String post_type;
  String postid;
  String text_more;
  String title;
  String userid;
  String wp_author;
  String wp_author_display_name;
  String wp_author_id;
  String wp_page_order;
  String wp_page_parent_id;
  String wp_page_parent_title;
  String wp_password;

  String wp_slug;

  /**
   * @return the categories
   */
  public XmlRpcArray getCategories() {
    return categories;
  }

  /**
   * @return the date_created_gmt
   */
  public Date getDate_created_gmt() {
    return date_created_gmt;
  }

  /**
   * @return the dateCreated
   */
  public Date getDateCreated() {
    return dateCreated;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @return the excerpt
   */
  public String getExcerpt() {
    return excerpt;
  }

  /**
   * @return the link
   */
  public String getLink() {
    return link;
  }

  /**
   * @return the mt_allow_comments
   */
  public Integer getMt_allow_comments() {
    return mt_allow_comments;
  }

  /**
   * @return the mt_allow_pings
   */
  public Integer getMt_allow_pings() {
    return mt_allow_pings;
  }

  /**
   * @return the page_id
   */
  public String getPage_id() {
    return page_id;
  }

  /**
   * @return the page_status
   */
  public String getPage_status() {
    return page_status;
  }

  /**
   * @return the permaLink
   */
  public String getPermaLink() {
    return permaLink;
  }

  /**
   * @return the post_type
   */
  public String getPost_type() {
    return post_type;
  }

  /**
   * @return the postid
   */
  public String getPostid() {
    return postid;
  }

  /**
   * (non-Javadoc)
   * 
   * @see net.bican.wordpress.StringHeader#getStringHeader()
   */
  public String getStringHeader() {
    return ""; // this is not a tabulated list
  }

  /**
   * @return the text_more
   */
  public String getText_more() {
    return text_more;
  }

  /**
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @return the userid
   */
  public String getUserid() {
    return userid;
  }

  /**
   * @return the wp_author
   */
  public String getWp_author() {
    return wp_author;
  }

  /**
   * @return the wp_author_display_name
   */
  public String getWp_author_display_name() {
    return wp_author_display_name;
  }

  /**
   * @return the wp_author_id
   */
  public String getWp_author_id() {
    return wp_author_id;
  }

  /**
   * @return the wp_page_order
   */
  public String getWp_page_order() {
    return wp_page_order;
  }

  /**
   * @return the wp_page_parent_id
   */
  public String getWp_page_parent_id() {
    return wp_page_parent_id;
  }

  /**
   * @return the wp_page_parent_title
   */
  public String getWp_page_parent_title() {
    return wp_page_parent_title;
  }

  /**
   * @return the wp_password
   */
  public String getWp_password() {
    return wp_password;
  }

  /**
   * @return the wp_slug
   */
  public String getWp_slug() {
    return wp_slug;
  }

  /**
   * @param categories
   *          the categories to set
   */
  public void setCategories(XmlRpcArray categories) {
    this.categories = categories;
  }

  /**
   * @param date_created_gmt
   *          the date_created_gmt to set
   */
  public void setDate_created_gmt(Date date_created_gmt) {
    this.date_created_gmt = date_created_gmt;
  }

  /**
   * @param dateCreated
   *          the dateCreated to set
   */
  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  /**
   * @param description
   *          the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @param excerpt
   *          the excerpt to set
   */
  public void setExcerpt(String excerpt) {
    this.excerpt = excerpt;
  }

  /**
   * @param link
   *          the link to set
   */
  public void setLink(String link) {
    this.link = link;
  }

  /**
   * @param mt_allow_comments
   *          the mt_allow_comments to set
   */
  public void setMt_allow_comments(Integer mt_allow_comments) {
    this.mt_allow_comments = mt_allow_comments;
  }

  /**
   * @param mt_allow_pings
   *          the mt_allow_pings to set
   */
  public void setMt_allow_pings(Integer mt_allow_pings) {
    this.mt_allow_pings = mt_allow_pings;
  }

  /**
   * @param page_id
   *          the page_id to set
   */
  public void setPage_id(String page_id) {
    this.page_id = page_id;
  }

  /**
   * @param page_status
   *          the page_status to set
   */
  public void setPage_status(String page_status) {
    this.page_status = page_status;
  }

  /**
   * @param permaLink
   *          the permaLink to set
   */
  public void setPermaLink(String permaLink) {
    this.permaLink = permaLink;
  }

  /**
   * @param post_type
   *          the post_type to set
   */
  public void setPost_type(String post_type) {
    this.post_type = post_type;
  }

  /**
   * @param postid
   *          the postid to set
   */
  public void setPostid(String postid) {
    this.postid = postid;
  }

  /**
   * @param text_more
   *          the text_more to set
   */
  public void setText_more(String text_more) {
    this.text_more = text_more;
  }

  /**
   * @param title
   *          the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @param userid
   *          the userid to set
   */
  public void setUserid(String userid) {
    this.userid = userid;
  }

  /**
   * @param wp_author
   *          the wp_author to set
   */
  public void setWp_author(String wp_author) {
    this.wp_author = wp_author;
  }

  /**
   * @param wp_author_display_name
   *          the wp_author_display_name to set
   */
  public void setWp_author_display_name(String wp_author_display_name) {
    this.wp_author_display_name = wp_author_display_name;
  }

  /**
   * @param wp_author_id
   *          the wp_author_id to set
   */
  public void setWp_author_id(String wp_author_id) {
    this.wp_author_id = wp_author_id;
  }

  /**
   * @param wp_page_order
   *          the wp_page_order to set
   */
  public void setWp_page_order(String wp_page_order) {
    this.wp_page_order = wp_page_order;
  }

  /**
   * @param wp_page_parent_id
   *          the wp_page_parent_id to set
   */
  public void setWp_page_parent_id(String wp_page_parent_id) {
    this.wp_page_parent_id = wp_page_parent_id;
  }

  /**
   * @param wp_page_parent_title
   *          the wp_page_parent_title to set
   */
  public void setWp_page_parent_title(String wp_page_parent_title) {
    this.wp_page_parent_title = wp_page_parent_title;
  }

  /**
   * @param wp_password
   *          the wp_password to set
   */
  public void setWp_password(String wp_password) {
    this.wp_password = wp_password;
  }

  /**
   * @param wp_slug
   *          the wp_slug to set
   */
  public void setWp_slug(String wp_slug) {
    this.wp_slug = wp_slug;
  }
}
