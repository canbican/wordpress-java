/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright 2012-2015 Can Bican
 * <can@bican.net> See the file 'COPYING' in the distribution for licensing terms.
 */
package net.bican.wordpress;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import net.bican.wordpress.exceptions.InvalidPostFormatException;
import net.bican.wordpress.util.FileParser;
import redstone.xmlrpc.XmlRpcStruct;

/**
 * A wordpress post
 *
 * @author Can Bican
 */
@Getter
@Setter
public class Post extends XmlRpcMapped {
  /**
   * creates a post object from file
   *
   * @param file input file
   * @return new Post object
   * @throws FileNotFoundException when the file is absent
   * @throws IOException when the file cannot be read
   * @throws InvalidPostFormatException when the file format is invalid
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

  @SuppressWarnings("unchecked")
  @Override
  public XmlRpcStruct toXmlRpcStruct() {
    XmlRpcStruct result = super.toXmlRpcStruct();
    if (this.post_thumbnail != null) {
      result.put("post_thumbnail", this.post_thumbnail.attachment_id);
    }
    return result;
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
   * @return the sticky status
   */
  public Boolean isSticky() {
    return this.getSticky();
  }
}
