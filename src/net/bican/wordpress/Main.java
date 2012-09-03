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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;

import net.bican.wordpress.configuration.WpCliConfiguration;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import redstone.xmlrpc.XmlRpcFault;

/**
 * 
 * Main Application class for the command line interface.
 * 
 * @author Can Bican &lt;can@bican.net&gt;
 * 
 */
public class Main {
  /**
   * @param args
   *          execute with "-?" for an explanation of args
   * @throws ParseException
   *           When the command line options cannot be parsed
   */
  @SuppressWarnings({ "nls", "boxing" })
  public static void main(String[] args) throws ParseException {
    try {
      Options options = new Options();
      options.addOption("?", "help", false, "Print usage information");
      options.addOption("h", "url", true, "Specify the url to xmlrpc.php");
      options.addOption("u", "user", true, "User name");
      options.addOption("p", "pass", true, "Password");
      options.addOption("a", "authors", false, "Get author list");
      options.addOption("s", "slug", true, "Slug for categories");
      options.addOption("pi", "parentid", true, "Parent id for categories");
      options.addOption("oi", "postid", true, "Post id for pages and posts");
      options.addOption("c", "categories", false, "Get category list");
      options.addOption("cn", "newcategory", true,
          "New category (uses --slug and --parentid)");
      options.addOption("cr", "deletecategory", true,
          "Delete category <category_id>");
      options.addOption("pg", "pages", false, "Get page list (full)");
      options.addOption("pl", "pagelist", false, "Get page list");
      options.addOption("ps", "page", true, "Get page");
      options.addOption("pn", "newpage", true,
          "New page from file <arg> (needs --publish)");
      options.addOption("pe", "editpage", true,
          "Edit page (needs --postid and --publish");
      options.addOption("pd", "deletepage", true,
          "Delete page (needs --publish)");
      options.addOption("l", "publish", true,
          "Publish status for \"new\" options");
      options.addOption("us", "userinfo", false, "Get user information");
      options.addOption("or", "recentposts", true, "Get recent posts");
      options.addOption("os", "getpost", true, "Get post");
      options.addOption("on", "newpost", true,
          "New post from file <arg> (needs --publish)");
      options.addOption("oe", "editpost", true,
          "Edit post (needs --postid and --publish");
      options.addOption("od", "deletepost", true,
          "Delete post (needs --publish)");
      options.addOption("sm", "supportedmethods", false,
          "List supported methods");
      options.addOption("st", "supportedfilters", false,
          "List supported text filters");
      options.addOption("mn", "newmedia", true,
          "New media file (uses --overwrite)");
      options.addOption("ov", "overwrite", false,
          "Allow overwrite in uploading new media");
      options.addOption("so", "supportedstatus", false,
          "Print supported page and post status values");
      options.addOption("cs", "commentstatus", false,
          "Print comment status names for the blog");
      options.addOption("cc", "commentcount", true,
          "Get comment count for a post (-1 for all posts)");
      options.addOption("ca", "newcomment", true, "New comment from file");
      options.addOption("cd", "deletecomment", true, "Delete comment");
      options.addOption("ce", "editcomment", true, "Edit comment from file");
      options.addOption("cg", "getcomment", true, "Get comment");
      options.addOption("ct", "getcomments", true, "Get comments for the post");
      options.addOption("cs", "commentstatus", true,
          "Comment status (for --getcomments)");
      options.addOption("co", "commentoffset", true,
          "Comment offset # (for --getcomments)");
      options.addOption("cm", "commentnumber", true,
          "Comment # (for --getcomments)");
      try {
        WpCliConfiguration config = new WpCliConfiguration(args, options,
            Main.class);
        if (config.hasOption("help")) {
          showHelp(options);
        } else if ((!config.hasOption("url")) || (!config.hasOption("user"))
            || (!config.hasOption("pass"))) {
          System.err.println("Specify --user, --pass and --url");
        } else {
          try {
            Wordpress wp = new Wordpress(config.getOptionValue("user"),
                config.getOptionValue("pass"), config.getOptionValue("url"));
            if (config.hasOption("authors")) {
              printList(wp.getAuthors(), Author.class, true);
            } else if (config.hasOption("categories")) {
              printList(wp.getCategories(), Category.class, true);
            } else if (config.hasOption("deletecategory")) {
              Integer category_id = Integer.valueOf(config
                  .getOptionValue("deletecategory"));
              int r = wp.deleteCategory(category_id);
              System.out.println(r);
            } else if (config.hasOption("newcategory")) {
              String slug = config.getOptionValue("slug");
              Integer parentId = getInteger("parentid", config);
              if (slug == null)
                slug = "";
              if (parentId == null)
                parentId = 0;
              System.out.println(wp.newCategory(
                  config.getOptionValue("newcategory"), slug, parentId));
            } else if (config.hasOption("pages")) {
              printList(wp.getPages(), Page.class, false);
            } else if (config.hasOption("pagelist")) {
              printList(wp.getPageList(), PageDefinition.class, false);
            } else if (config.hasOption("page")) {
              printItem(wp.getPage(getInteger("page", config)), Page.class);
            } else if (config.hasOption("userinfo")) {
              printItem(wp.getUserInfo(), User.class);
            } else if (config.hasOption("recentposts")) {
              printList(wp.getRecentPosts(getInteger("recentposts", config)),
                  Page.class, false);
            } else if (config.hasOption("getpost")) {
              printItem(wp.getPost(getInteger("getpost", config)), Page.class);
            } else if (config.hasOption("supportedmethods")) {
              printList(wp.supportedMethods(), String.class, false);
            } else if (config.hasOption("supportedfilters")) {
              printList(wp.supportedTextFilters(), String.class, false);
            } else if (config.hasOption("newpage")) {
              if (!config.hasOption("publish")) {
                showHelp(options);
              } else {
                System.out.println(wp.newPage(
                    Page.fromFile(new File(config.getOptionValue("newpage"))),
                    config.getOptionValue("publish")));
              }
            } else if (config.hasOption("editpage")) {
              edit(options, config, wp, "editpage", true);
            } else if (config.hasOption("editpost")) {
              edit(options, config, wp, "editpost", false);
            } else if (config.hasOption("deletepage")) {
              delete(options, config, wp, "deletepage", true);
            } else if (config.hasOption("deletepost")) {
              delete(options, config, wp, "deletepost", false);
            } else if (config.hasOption("newpost")) {
              if (!config.hasOption("publish")) {
                showHelp(options);
              } else {
                System.out.println(wp.newPost(
                    Page.fromFile(new File(config.getOptionValue("newpost"))),
                    Boolean.valueOf(config.getOptionValue("publish"))));
              }
            } else if (config.hasOption("newmedia")) {
              String fileName = config.getOptionValue("newmedia");
              File file = new File(fileName);
              String mimeType = new MimetypesFileTypeMap().getContentType(file);
              Boolean overwrite = Boolean.FALSE;
              if (config.hasOption("overwrite"))
                overwrite = Boolean.TRUE;
              MediaObject result = wp.newMediaObject(mimeType, file, overwrite);
              if (result != null) {
                System.out.println(result);
              }
            } else if (config.hasOption("supportedstatus")) {
              System.out.println("Recognized status values for posts:");
              printList(wp.getPostStatusList(), PostAndPageStatus.class, true);
              System.out.println("\nRecognized status values for pages:");
              printList(wp.getPageStatusList(), PostAndPageStatus.class, true);
            } else if (config.hasOption("commentstatus")) {
              showCommentStatus(wp);
            } else if (config.hasOption("commentcount")) {
              showCommentCount(config, wp);
            } else if (config.hasOption("newcomment")) {
              editComment(wp, config.getOptionValue("newcomment"), "newcomment");
            } else if (config.hasOption("editcomment")) {
              editComment(wp, config.getOptionValue("editcomment"),
                  "editcomment");
            } else if (config.hasOption("deletecomment")) {
              System.err.println(Integer.valueOf(config
                  .getOptionValue("deletecomment")));
              deleteComment(wp,
                  Integer.valueOf(config.getOptionValue("deletecomment")));
            } else if (config.hasOption("getcomment")) {
              printComment(wp,
                  Integer.valueOf(config.getOptionValue("getcomment")));
            } else if (config.hasOption("getcomments")) {
              Integer postID = Integer.valueOf(config
                  .getOptionValue("getcomments"));
              String commentStatus = config.getOptionValue("commentstatus");
              Integer commentOffset;
              try {
                commentOffset = Integer.valueOf(config
                    .getOptionValue("commentoffset"));
              } catch (NumberFormatException e) {
                commentOffset = null;
              }
              Integer commentNumber;
              try {
                commentNumber = Integer.valueOf(config
                    .getOptionValue("commentnumber"));
              } catch (Exception e) {
                commentNumber = null;
              }
              printComments(wp, postID, commentStatus, commentOffset,
                  commentNumber);
            } else {
              showHelp(options);
            }
          } catch (MalformedURLException e) {
            System.err.println("URL \"" + config.getOptionValue("url")
                + "\" is invalid, reason is: " + e.getLocalizedMessage());
          } catch (IOException e) {
            System.err.println("Can't read from file, reason is: "
                + e.getLocalizedMessage());
          } catch (InvalidPostFormatException e) {
            System.err.println("Input format is invalid.");
          }
        }
      } catch (ParseException e) {
        System.err.println("Can't process command line arguments, reason is: "
            + e.getLocalizedMessage());
      }
    } catch (XmlRpcFault e) {
      String reason = e.getLocalizedMessage();
      System.err.println("Operation failed, reason is: " + reason);
    }
  }

  @SuppressWarnings("nls")
  private static void printComments(Wordpress wp, Integer postID,
      String commentStatus, Integer commentOffset, Integer commentNumber)
      throws XmlRpcFault {
    List<Comment> r = wp.getComments(commentStatus, postID, commentNumber,
        commentOffset);
    for (Comment comment : r) {
      System.out.println("--- BEGIN COMMENT");
      System.out.println(comment);
      System.out.println("--- END COMMENT");
    }
  }

  private static void printComment(Wordpress wp, Integer commentID)
      throws XmlRpcFault {
    Comment r = wp.getComment(commentID);
    System.out.println(r);
  }

  @SuppressWarnings("nls")
  private static void deleteComment(Wordpress wp, int commentID)
      throws XmlRpcFault {
    boolean result = wp.deleteComment(commentID);
    if (result)
      System.out.println("Comment deleted.");
    else
      System.out.println("Comment not deleted");
  }

  @SuppressWarnings({ "nls", "boxing" })
  private static void editComment(Wordpress wp, String fileName,
      String operation) throws XmlRpcFault, FileNotFoundException, IOException,
      InvalidPostFormatException {
    Comment comment = Comment.fromFile(new File(fileName));
    System.err.println(comment.getPost_id());
    System.err.println(comment.getContent());
    if (operation.equals("newcomment")) {
      Integer r = wp.newComment(comment.getPost_id(), comment.getParent(),
          comment.getContent(), comment.getAuthor(), comment.getAuthor_url(),
          comment.getAuthor_email());
      System.err.println("Comment ID: " + r);
    } else if (operation.equals("editcomment")) {
      Boolean r = wp.editComment(comment);
      if (r)
        System.err.println("Comment edited.");
      else
        System.err.println("Comment not edited.");
    }
  }

  @SuppressWarnings("nls")
  private static void showCommentCount(WpCliConfiguration config, Wordpress wp) {
    Integer post_ID = getInteger("commentcount", config);
    try {
      CommentCount result = wp.getCommentsCount(post_ID);
      System.out.println(result);
    } catch (XmlRpcFault e) {
      String reason = e.getLocalizedMessage();
      System.err.println("Operation failed, reason is: " + reason);
    }
  }

  private static void showCommentStatus(Wordpress wp) {
    printItem(wp.getCommentStatusList(), CommentStatusList.class);
  }

  @SuppressWarnings({ "nls", "boxing" })
  private static void delete(Options options, WpCliConfiguration config,
      Wordpress wp, String opt, boolean deletePage) throws XmlRpcFault {
    Integer post_ID = getInteger(opt, config);
    String publish = config.getOptionValue("publish");
    if ((publish != null) && (post_ID != null)) {
      if (deletePage) {
        System.out.println(wp.deletePage(post_ID, publish));
      } else {
        System.out.println(wp.deletePost(post_ID, publish));
      }
    } else {
      showHelp(options);
    }
  }

  @SuppressWarnings({ "nls", "boxing" })
  private static void edit(Options options, WpCliConfiguration config,
      Wordpress wp, String opt, boolean isPage) throws IOException,
      InvalidPostFormatException, XmlRpcFault {
    Integer post_ID = getInteger("postid", config);
    String publish = config.getOptionValue("publish");
    Page page = Page.fromFile(new File(config.getOptionValue(opt)));
    if ((publish != null) && (post_ID != null)) {
      Boolean result = false;
      if (isPage) {
        result = wp.editPage(post_ID, page, publish);
      } else {
        result = wp.editPost(post_ID, page, publish);
      }
      System.out.println(result);
    } else {
      showHelp(options);
    }
  }

  private static Integer getInteger(String c, WpCliConfiguration config) {
    Integer post_ID = null;
    try {
      post_ID = Integer.valueOf(config.getOptionValue(c));
    } catch (NumberFormatException e) {
      // leave it null
    }
    return post_ID;
  }

  @SuppressWarnings("nls")
  private static void showHelp(Options options) {
    HelpFormatter help = new HelpFormatter();
    help.printHelp(" ", options);
  }

  private static void printItem(Object o, Class<?> cl) {
    cl.cast(o);
    System.out.println(((StringHeader) o).getStringHeader());
    System.out.println(o);
  }

  private static void printList(List<?> r, Class<?> cl, boolean oneLiner) {
    boolean headerPrinted = false;
    for (Object o : r) {
      cl.cast(o);
      if (!headerPrinted) {
        if ((!(o instanceof String)) && (oneLiner)) {
          System.out.println(((StringHeader) o).getStringHeader());
        }
        headerPrinted = true;
      }
      if (oneLiner) {
        System.out.println(((XmlRpcMapped) o).toOneLinerString());
      } else {
        System.out.println(o);
      }
    }
  }
}
