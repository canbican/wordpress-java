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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

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
   * @param file file that contains the page information
   * @return new Page object
   * @throws IOException when the file cannot be read
   * @throws InvalidPostFormatException when the file format is not recognized
   */
  public static Page fromFile(File file) throws IOException,
      InvalidPostFormatException {
    BufferedReader input = new BufferedReader(new FileReader(file));
    XmlRpcStruct page = FileParser.parseFile(input);
    Page result = new Page();
    result.fromXmlRpcStruct(page);
    return result;
  }

  XmlRpcArray categories;

  XmlRpcArray custom_fields;

  Date date_created_gmt;

  Date dateCreated;

  String description;

  String link;

  Integer mt_allow_comments;

  Integer mt_allow_pings;

  String mt_excerpt;

  String mt_keywords;

  String mt_text_more;

  Integer page_id;

  String page_status;

  String permaLink;

  String post_status;

  String post_type;

  Integer postid;

  String title;

  String userid;

  String wp_author;

  String wp_author_display_name;

  String wp_author_id;

  Integer wp_page_order;

  Integer wp_page_parent_id;

  String wp_page_parent_title;

  String wp_password;

  String wp_slug;

  /**
   * @return the categories
   */
  public XmlRpcArray getCategories() {
    return this.categories;
  }

  /**
   * @return the custom_fields
   */
  public XmlRpcArray getCustom_fields() {
    return this.custom_fields;
  }

  /**
   * @return the date_created_gmt
   */
  public Date getDate_created_gmt() {
    return this.date_created_gmt;
  }

  /**
   * @return the dateCreated
   */
  public Date getDateCreated() {
    return this.dateCreated;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * @return the excerpt
   */
  public String getExcerpt() {
    return this.mt_excerpt;
  }

  /**
   * @return the link
   */
  public String getLink() {
    return this.link;
  }

  /**
   * @return the mt_allow_comments
   */
  public Integer getMt_allow_comments() {
    return this.mt_allow_comments;
  }

  /**
   * @return the mt_allow_pings
   */
  public Integer getMt_allow_pings() {
    return this.mt_allow_pings;
  }

  /**
   * @return the mt_keywords
   */
  public String getMt_keywords() {
    return this.mt_keywords;
  }

  /**
   * @return text_more
   */
  public String getMt_text_more() {
    return this.mt_text_more;
  }

  /**
   * @return the page_id
   */
  public Integer getPage_id() {
    return this.page_id;
  }

  /**
   * @return the page_status
   */
  public String getPage_status() {
    return this.page_status;
  }

  /**
   * @return the permaLink
   */
  public String getPermaLink() {
    return this.permaLink;
  }

  /**
   * @return the post_status
   */
  public String getPost_status() {
    return this.post_status;
  }

  /**
   * @return the post_type
   */
  public String getPost_type() {
    return this.post_type;
  }

  /**
   * @return the postid
   */
  public Integer getPostid() {
    return this.postid;
  }

  /**
   * (non-Javadoc)
   * 
   * @see net.bican.wordpress.StringHeader#getStringHeader()
   */
  @Override
  @SuppressWarnings("nls")
  public String getStringHeader() {
    return ""; // this is not a tabulated list
  }

  /**
   * @return the title
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * @return the userid
   */
  public String getUserid() {
    return this.userid;
  }

  /**
   * @return the wp_author
   */
  public String getWp_author() {
    return this.wp_author;
  }

  /**
   * @return the wp_author_display_name
   */
  public String getWp_author_display_name() {
    return this.wp_author_display_name;
  }

  /**
   * @return the wp_author_id
   */
  public String getWp_author_id() {
    return this.wp_author_id;
  }

  /**
   * @return the wp_page_order
   */
  public Integer getWp_page_order() {
    return this.wp_page_order;
  }

  /**
   * @return the wp_page_parent_id
   */
  public Integer getWp_page_parent_id() {
    return this.wp_page_parent_id;
  }

  /**
   * @return the wp_page_parent_title
   */
  public String getWp_page_parent_title() {
    return this.wp_page_parent_title;
  }

  /**
   * @return the wp_password
   */
  public String getWp_password() {
    return this.wp_password;
  }

  /**
   * @return the wp_slug
   */
  public String getWp_slug() {
    return this.wp_slug;
  }

  /**
   * @param categories the categories to set
   */
  public void setCategories(XmlRpcArray categories) {
    this.categories = categories;
  }

  /**
   * @param custom_fields the custom_fields to set
   */
  public void setCustom_fields(XmlRpcArray custom_fields) {
    this.custom_fields = custom_fields;
  }

  /**
   * @param date_created_gmt the date_created_gmt to set
   */
  public void setDate_created_gmt(Date date_created_gmt) {
    this.date_created_gmt = date_created_gmt;
    // one of dateCreated() and date_created_gmt() has to be null
    // or the behavior when two of them are different is undefined
    if (this.date_created_gmt != null)
      this.dateCreated = null;
  }

  /**
   * @param dateCreated the dateCreated to set
   */
  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
    // one of dateCreated() and date_created_gmt() has to be null
    // or the behavior when two of them are different is undefined
    if (this.dateCreated != null)
      this.date_created_gmt = null;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @param excerpt the excerpt to set
   */
  public void setExcerpt(String excerpt) {
    this.mt_excerpt = excerpt;
  }

  /**
   * @param link the link to set
   */
  public void setLink(String link) {
    this.link = link;
  }

  /**
   * @param mt_allow_comments the mt_allow_comments to set
   */
  public void setMt_allow_comments(Integer mt_allow_comments) {
    this.mt_allow_comments = mt_allow_comments;
  }

  /**
   * @param mt_allow_pings the mt_allow_pings to set
   */
  public void setMt_allow_pings(Integer mt_allow_pings) {
    this.mt_allow_pings = mt_allow_pings;
  }

  /**
   * @param mt_keywords the mt_keywords to set
   */
  public void setMt_keywords(String mt_keywords) {
    this.mt_keywords = mt_keywords;
  }

  /**
   * @param mt_text_more to set
   */
  public void setMt_text_more(String mt_text_more) {
    this.mt_text_more = mt_text_more;
  }

  /**
   * @param page_id the page_id to set
   */
  public void setPage_id(Integer page_id) {
    this.page_id = page_id;
  }

  /**
   * @param page_status the page_status to set
   */
  public void setPage_status(String page_status) {
    this.page_status = page_status;
  }

  /**
   * @param permaLink the permaLink to set
   */
  public void setPermaLink(String permaLink) {
    this.permaLink = permaLink;
  }

  /**
   * @param postStatus the post_status to set
   */
  public void setPost_status(String postStatus) {
    this.post_status = postStatus;
  }

  /**
   * @param post_type the post_type to set
   */
  public void setPost_type(String post_type) {
    this.post_type = post_type;
  }

  /**
   * @param postid the postid to set
   */
  public void setPostid(Integer postid) {
    this.postid = postid;
  }

  /**
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @param userid the userid to set
   */
  public void setUserid(String userid) {
    this.userid = userid;
  }

  /**
   * @param wp_author the wp_author to set
   */
  public void setWp_author(String wp_author) {
    this.wp_author = wp_author;
  }

  /**
   * @param wp_author_display_name the wp_author_display_name to set
   */
  public void setWp_author_display_name(String wp_author_display_name) {
    this.wp_author_display_name = wp_author_display_name;
  }

  /**
   * @param wp_author_id the wp_author_id to set
   */
  public void setWp_author_id(String wp_author_id) {
    this.wp_author_id = wp_author_id;
  }

  /**
   * @param wp_page_order the wp_page_order to set
   */
  public void setWp_page_order(Integer wp_page_order) {
    this.wp_page_order = wp_page_order;
  }

  /**
   * @param wp_page_parent_id the wp_page_parent_id to set
   */
  public void setWp_page_parent_id(Integer wp_page_parent_id) {
    this.wp_page_parent_id = wp_page_parent_id;
  }

  /**
   * @param wp_page_parent_title the wp_page_parent_title to set
   */
  public void setWp_page_parent_title(String wp_page_parent_title) {
    this.wp_page_parent_title = wp_page_parent_title;
  }

  /**
   * @param wp_password the wp_password to set
   */
  public void setWp_password(String wp_password) {
    this.wp_password = wp_password;
  }

  /**
   * @param wp_slug the wp_slug to set
   */
  public void setWp_slug(String wp_slug) {
    this.wp_slug = wp_slug;
  }
}
