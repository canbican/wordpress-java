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

import lombok.Getter;
import lombok.Setter;
import net.bican.wordpress.exceptions.InvalidPostFormatException;
import net.bican.wordpress.util.FileParser;
import net.bican.wordpress.util.StringHeader;
import redstone.xmlrpc.XmlRpcStruct;

/**
 * A WordPress Comment object
 *
 * @author Fred Potter
 */
@Getter
@Setter
public class Comment extends XmlRpcMapped implements StringHeader {

  /**
   * Creates a comment object from file
   *
   * @param file input file
   * @return new Comment object
   * @throws FileNotFoundException when the file is absent
   * @throws IOException when the file cannot be read
   * @throws InvalidPostFormatException when the file format is invalid
   */
  public static Comment fromFile(final File file)
      throws FileNotFoundException, IOException, InvalidPostFormatException {
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      final XmlRpcStruct c = FileParser.parseFile(br);
      final Comment result = new Comment();
      result.fromXmlRpcStruct(c);
      return result;
    }
  }

  String author;
  String author_email;
  String author_ip;
  String author_url;
  Integer comment_id;
  String content;
  Date date_created_gmt;
  String link;
  Integer parent;
  Integer post_id;
  String post_title;
  String status;
  Integer user_id;

  @Override
  @SuppressWarnings("nls")
  public String getStringHeader() {
    final String TAB = ":";
    return "Date Created GMT" + TAB + "User Id" + TAB + "Comment Id" + TAB + "Parent" + TAB
        + "Status" + TAB + "Content" + TAB + "Link" + TAB + "Post Id" + TAB + "Post Title" + TAB
        + "Author" + TAB + "Author URL " + TAB + "Author Email" + TAB + "Author IP";
  }
}
