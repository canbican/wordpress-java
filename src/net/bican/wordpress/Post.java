/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import net.bican.wordpress.exceptions.InvalidPostFormatException;
import net.bican.wordpress.util.FileParser;
import redstone.xmlrpc.XmlRpcStruct;

/**
 * A wordpress post
 *
 * @author Can Bican
 */
public class Post extends XmlRpcMapped {
  /**
   * creates a post object from file
   *
   * @param file
   *          input file
   * @return new Post object
   * @throws FileNotFoundException
   *           when the file is absent
   * @throws IOException
   *           when the file cannot be read
   * @throws InvalidPostFormatException
   *           when the file format is invalid
   */
  public static Post fromFile(final File file)
      throws FileNotFoundException, IOException, InvalidPostFormatException {
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      final XmlRpcStruct c = FileParser.parseFile(br);
      final Post result = new Post();
      result.fromXmlRpcStruct(c);
      return result;
    }
  }

  String comment_status;
  List<CustomField> custom_fields;
  Enclosure enclosure;
  String guid;
  String link;
  Integer menu_order;
  String ping_status;
  Integer post_author;
  String post_content;
  Date post_date;
  Date post_date_gmt;
  String post_excerpt;
  String post_format;
  Integer post_id;
  String post_mime_type;
  Date post_modified;
  Date post_modified_gmt;
  String post_name;
  Integer post_parent;
  String post_password;
  String post_status;
  MediaItem post_thumbnail;
  String post_title;
  String post_type;
  Boolean sticky;
  
  List<Term> terms;
  
  /**
   * @return the comment_status
   */
  public final String getComment_status() {
    return this.comment_status;
  }
  
  /**
   * @return the customFields
   */
  public final List<CustomField> getCustom_fields() {
    return this.custom_fields;
  }
  
  /**
   * @return the enclosure
   */
  public final Enclosure getEnclosure() {
    return this.enclosure;
  }
  
  /**
   * @return the guid
   */
  public final String getGuid() {
    return this.guid;
  }
  
  /**
   * @return the link
   */
  public final String getLink() {
    return this.link;
  }
  
  /**
   * @return the menu_order
   */
  public final Integer getMenu_order() {
    return this.menu_order;
  }
  
  /**
   * @return the ping_status
   */
  public final String getPing_status() {
    return this.ping_status;
  }
  
  /**
   * @return the post_author
   */
  public final Integer getPost_author() {
    return this.post_author;
  }
  
  /**
   * @return the post_content
   */
  public final String getPost_content() {
    return this.post_content;
  }
  
  /**
   * @return the post_date
   */
  public final Date getPost_date() {
    return this.post_date;
  }
  
  /**
   * @return the post_date_gmt
   */
  public final Date getPost_date_gmt() {
    return this.post_date_gmt;
  }
  
  /**
   * @return the post_excerpt
   */
  public final String getPost_excerpt() {
    return this.post_excerpt;
  }
  
  /**
   * @return the post_format
   */
  public final String getPost_format() {
    return this.post_format;
  }
  
  /**
   * @return the post_id
   */
  public final Integer getPost_id() {
    return this.post_id;
  }
  
  /**
   * @return the post_mime_type
   */
  public final String getPost_mime_type() {
    return this.post_mime_type;
  }
  
  /**
   * @return the post_modified
   */
  public final Date getPost_modified() {
    return this.post_modified;
  }
  
  /**
   * @return the post_modified_gmt
   */
  public final Date getPost_modified_gmt() {
    return this.post_modified_gmt;
  }
  
  /**
   * @return the post_name
   */
  public final String getPost_name() {
    return this.post_name;
  }
  
  /**
   * @return the post_parent
   */
  public final Integer getPost_parent() {
    return this.post_parent;
  }
  
  /**
   * @return the post_password
   */
  public final String getPost_password() {
    return this.post_password;
  }
  
  /**
   * @return the post_status
   */
  public final String getPost_status() {
    return this.post_status;
  }
  
  /**
   * @return the post_thumbnail
   */
  public final MediaItem getPost_thumbnail() {
    return this.post_thumbnail;
  }
  
  /**
   * @return the post_title
   */
  public final String getPost_title() {
    return this.post_title;
  }
  
  /**
   * @return the post_type
   */
  public final String getPost_type() {
    return this.post_type;
  }
  
  /**
   * @return the terms
   */
  public final List<Term> getTerms() {
    return this.terms;
  }
  
  /**
   * @return the sticky
   */
  public final Boolean isSticky() {
    return this.sticky;
  }
  
  /**
   * @param comment_status
   *          the comment_status to set
   */
  public final void setComment_status(final String comment_status) {
    this.comment_status = comment_status;
  }
  
  /**
   * @param customFields
   *          the customFields to set
   */
  public final void setCustom_fields(final List<CustomField> customFields) {
    this.custom_fields = customFields;
  }
  
  /**
   * @param enclosure
   *          the enclosure to set
   */
  public final void setEnclosure(final Enclosure enclosure) {
    this.enclosure = enclosure;
  }
  
  /**
   * @param guid
   *          the guid to set
   */
  public final void setGuid(final String guid) {
    this.guid = guid;
  }
  
  /**
   * @param link
   *          the link to set
   */
  public final void setLink(final String link) {
    this.link = link;
  }
  
  /**
   * @param menu_order
   *          the menu_order to set
   */
  public final void setMenu_order(final Integer menu_order) {
    this.menu_order = menu_order;
  }
  
  /**
   * @param ping_status
   *          the ping_status to set
   */
  public final void setPing_status(final String ping_status) {
    this.ping_status = ping_status;
  }
  
  /**
   * @param post_author
   *          the post_author to set
   */
  public final void setPost_author(final Integer post_author) {
    this.post_author = post_author;
  }
  
  /**
   * @param post_content
   *          the post_content to set
   */
  public final void setPost_content(final String post_content) {
    this.post_content = post_content;
  }
  
  /**
   * @param post_date
   *          the post_date to set
   */
  public final void setPost_date(final Date post_date) {
    this.post_date = post_date;
  }
  
  /**
   * @param post_date_gmt
   *          the post_date_gmt to set
   */
  public final void setPost_date_gmt(final Date post_date_gmt) {
    this.post_date_gmt = post_date_gmt;
  }
  
  /**
   * @param post_excerpt
   *          the post_excerpt to set
   */
  public final void setPost_excerpt(final String post_excerpt) {
    this.post_excerpt = post_excerpt;
  }
  
  /**
   * @param post_format
   *          the post_format to set
   */
  public final void setPost_format(final String post_format) {
    this.post_format = post_format;
  }
  
  /**
   * @param post_id
   *          the post_id to set
   */
  public final void setPost_id(final Integer post_id) {
    this.post_id = post_id;
  }
  
  /**
   * @param post_mime_type
   *          the post_mime_type to set
   */
  public final void setPost_mime_type(final String post_mime_type) {
    this.post_mime_type = post_mime_type;
  }
  
  /**
   * @param post_modified
   *          the post_modified to set
   */
  public final void setPost_modified(final Date post_modified) {
    this.post_modified = post_modified;
  }
  
  /**
   * @param post_modified_gmt
   *          the post_modified_gmt to set
   */
  public final void setPost_modified_gmt(final Date post_modified_gmt) {
    this.post_modified_gmt = post_modified_gmt;
  }
  
  /**
   * @param post_name
   *          the post_name to set
   */
  public final void setPost_name(final String post_name) {
    this.post_name = post_name;
  }
  
  /**
   * @param post_parent
   *          the post_parent to set
   */
  public final void setPost_parent(final Integer post_parent) {
    this.post_parent = post_parent;
  }
  
  /**
   * @param post_password
   *          the post_password to set
   */
  public final void setPost_password(final String post_password) {
    this.post_password = post_password;
  }
  
  /**
   * @param post_status
   *          the post_status to set
   */
  public final void setPost_status(final String post_status) {
    this.post_status = post_status;
  }
  
  /**
   * @param post_thumbnail
   *          the post_thumbnail to set
   */
  public final void setPost_thumbnail(final MediaItem post_thumbnail) {
    this.post_thumbnail = post_thumbnail;
  }
  
  /**
   * @param post_title
   *          the post_title to set
   */
  public final void setPost_title(final String post_title) {
    this.post_title = post_title;
  }
  
  /**
   * @param post_type
   *          the post_type to set
   */
  public final void setPost_type(final String post_type) {
    this.post_type = post_type;
  }
  
  /**
   * @param sticky
   *          the sticky to set
   */
  public final void setSticky(final Boolean sticky) {
    this.sticky = sticky;
  }
  
  /**
   * @param terms
   *          the terms to set
   */
  public final void setTerms(final List<Term> terms) {
    this.terms = terms;
  }
}
