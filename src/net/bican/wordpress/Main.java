/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import net.bican.wordpress.configuration.WpCliConfiguration;
import net.bican.wordpress.exceptions.FileUploadException;
import net.bican.wordpress.exceptions.InsufficientRightsException;
import net.bican.wordpress.exceptions.InvalidArgumentsException;
import net.bican.wordpress.exceptions.InvalidPostFormatException;
import net.bican.wordpress.exceptions.ObjectNotFoundException;
import net.bican.wordpress.util.StringHeader;
import redstone.xmlrpc.XmlRpcFault;

/**
 * Main Application class for the command line interface.
 *
 * @author Can Bican
 */
public class Main {
  private static void delete(final Options options,
      final WpCliConfiguration config, final Wordpress wp, final String opt,
      final boolean deletePage) throws XmlRpcFault, InsufficientRightsException,
          ObjectNotFoundException {
    final Integer post_ID = getInteger(opt, config);
    if (post_ID != null) {
      System.out.println(wp.deletePost(post_ID));
    } else {
      showHelp(options);
    }
  }
  
  @SuppressWarnings("nls")
  private static void deleteComment(final Wordpress wp, final int commentID)
      throws XmlRpcFault, InsufficientRightsException, ObjectNotFoundException {
    final boolean result = wp.deleteComment(Integer.valueOf(commentID));
    if (result) {
      System.out.println("Comment deleted.");
    } else {
      System.out.println("Comment not deleted");
    }
  }
  
  @SuppressWarnings({ "nls" })
  private static void edit(final Options options,
      final WpCliConfiguration config, final Wordpress wp, final String opt,
      final boolean isPage) throws IOException, InvalidPostFormatException,
          XmlRpcFault, InsufficientRightsException, InvalidArgumentsException,
          ObjectNotFoundException {
    final Integer post_ID = getInteger("postid", config);
    final Post post = Post.fromFile(new File(config.getOptionValue(opt)));
    if (post_ID != null) {
      System.out.println(wp.editPost(post_ID, post));
    } else {
      showHelp(options);
    }
  }
  
  @SuppressWarnings({ "nls", "boxing" })
  private static void editComment(final Wordpress wp, final String fileName,
      final String operation) throws XmlRpcFault, FileNotFoundException,
          IOException, InvalidPostFormatException, InsufficientRightsException,
          ObjectNotFoundException, InvalidArgumentsException {
    final Comment comment = Comment.fromFile(new File(fileName));
    System.err.println(comment.getPost_id());
    System.err.println(comment.getContent());
    if (operation.equals("newcomment")) {
      final Integer r = wp.newComment(comment.getPost_id(), comment.getParent(),
          comment.getContent(), comment.getAuthor(), comment.getAuthor_url(),
          comment.getAuthor_email());
      System.err.println("Comment ID: " + r);
    } else if (operation.equals("editcomment")) {
      final Boolean r = wp.editComment(comment);
      if (r) {
        System.err.println("Comment edited.");
      } else {
        System.err.println("Comment not edited.");
      }
    }
  }
  
  private static Integer getInteger(final String c,
      final WpCliConfiguration config) {
    Integer post_ID = null;
    try {
      post_ID = Integer.valueOf(config.getOptionValue(c));
    } catch (final NumberFormatException e) {
      // leave it null
    }
    return post_ID;
  }
  
  /**
   * @param args
   *          execute with "-?" for an explanation of args
   * @throws ParseException
   *           When the command line options cannot be parsed
   * @throws InvalidArgumentsException
   *           thrown from a wordpress call
   * @throws InsufficientRightsException
   *           thrown from a wordpress call
   * @throws ObjectNotFoundException
   *           thrown from a wordpress call
   * @throws FileUploadException
   *           thrown from a wordpress call
   */
  @SuppressWarnings({ "nls", "boxing" })
  public static void main(final String[] args)
      throws ParseException, InsufficientRightsException,
      InvalidArgumentsException, ObjectNotFoundException, FileUploadException {
    try {
      final Options options = new Options();
      options.addOption("?", "help", false, "Print usage information");
      options.addOption("h", "url", true, "Specify the url to xmlrpc.php");
      options.addOption("u", "user", true, "User name");
      options.addOption("p", "pass", true, "Password");
      options.addOption("a", "authors", false, "Get author list");
      options.addOption("s", "slug", true, "Slug for categories");
      options.addOption("pi", "parentid", true, "Parent id for categories");
      options.addOption("oi", "postid", true, "Post id for posts");
      options.addOption("c", "categories", false, "Get category list");
      options.addOption("cn", "newcategory", true,
          "New category (uses --slug and --parentid)");
      options.addOption("cr", "deletecategory", true,
          "Delete category <category_id>");
      options.addOption("us", "userinfo", false, "Get user information");
      options.addOption("or", "recentposts", true, "Get recent posts");
      options.addOption("os", "getpost", true, "Get post");
      options.addOption("on", "newpost", true, "New post from file <arg>");
      options.addOption("oe", "editpost", true, "Edit post (needs --postid)");
      options.addOption("od", "deletepost", true, "Delete post");
      options.addOption("mn", "newmedia", true,
          "New media file (uses --overwrite)");
      options.addOption("ov", "overwrite", false,
          "Allow overwrite in uploading new media");
      options.addOption("so", "supportedstatus", false,
          "Print supported post status values");
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
        final WpCliConfiguration config = new WpCliConfiguration(args, options,
            Main.class);
        if (config.hasOption("help")) {
          showHelp(options);
        } else if (!config.hasOption("url") || !config.hasOption("user")
            || !config.hasOption("pass")) {
          System.err.println("Specify --user, --pass and --url");
        } else {
          try {
            final Wordpress wp = new Wordpress(config.getOptionValue("user"),
                config.getOptionValue("pass"), config.getOptionValue("url"));
            if (config.hasOption("authors")) {
              printList(wp.getAuthors(), Author.class, true);
            } else if (config.hasOption("categories")) {
              printList(wp.getTerms("category"), Term.class, true);
            } else if (config.hasOption("deletecategory")) {
              final Integer category_id = Integer
                  .valueOf(config.getOptionValue("deletecategory"));
              final boolean r = wp.deleteTerm("category", category_id);
              System.out.println(r);
            } else if (config.hasOption("newcategory")) {
              String slug = config.getOptionValue("slug");
              Integer parentId = getInteger("parentid", config);
              if (slug == null) {
                slug = "";
              }
              if (parentId == null) {
                parentId = 0;
              }
              final Term term = new Term();
              term.setSlug(slug);
              term.setParent(parentId);
              term.setName(config.getOptionValue("newcategory"));
              System.out.println(wp.newTerm(term));
            } else if (config.hasOption("userinfo")) {
              final FilterUser filter = new FilterUser();
              filter.setWho(config.getOptionValue("user"));
              printItem(wp.getUsers(filter).get(0), User.class);
            } else if (config.hasOption("recentposts")) {
              final FilterPost filter = new FilterPost();
              filter.setNumber(getInteger("recentposts", config));
              printList(wp.getPosts(filter), Post.class, false);
            } else if (config.hasOption("getpost")) {
              printItem(wp.getPost(getInteger("getpost", config)), Post.class);
            } else if (config.hasOption("editpost")) {
              edit(options, config, wp, "editpost", false);
            } else if (config.hasOption("deletepost")) {
              delete(options, config, wp, "deletepost", false);
            } else if (config.hasOption("newpost")) {
              System.out.println(wp.newPost(
                  Post.fromFile(new File(config.getOptionValue("newpost")))));
            } else if (config.hasOption("newmedia")) {
              final String fileName = config.getOptionValue("newmedia");
              final File file = new File(fileName);
              Boolean overwrite = Boolean.FALSE;
              if (config.hasOption("overwrite")) {
                overwrite = Boolean.TRUE;
              }
              try (FileInputStream fis = new FileInputStream(file)) {
                final MediaItemUploadResult result = wp.uploadFile(fis,
                    fileName, overwrite);
                if (result != null) {
                  System.out.println(result);
                }
              }
            } else if (config.hasOption("supportedstatus")) {
              System.out.println("Recognized status values for posts:");
              final Map<String, String> psl = wp.getPostStatusList();
              for (final String k : psl.keySet()) {
                System.out.println(k + " : " + psl.get(k));
              }
            } else if (config.hasOption("commentstatus")) {
              showCommentStatus(wp);
            } else if (config.hasOption("commentcount")) {
              showCommentCount(config, wp);
            } else if (config.hasOption("newcomment")) {
              editComment(wp, config.getOptionValue("newcomment"),
                  "newcomment");
            } else if (config.hasOption("editcomment")) {
              editComment(wp, config.getOptionValue("editcomment"),
                  "editcomment");
            } else if (config.hasOption("deletecomment")) {
              System.err.println(
                  Integer.valueOf(config.getOptionValue("deletecomment")));
              deleteComment(wp,
                  Integer.valueOf(config.getOptionValue("deletecomment")));
            } else if (config.hasOption("getcomment")) {
              printComment(wp,
                  Integer.valueOf(config.getOptionValue("getcomment")));
            } else if (config.hasOption("getcomments")) {
              final Integer postID = Integer
                  .valueOf(config.getOptionValue("getcomments"));
              final String commentStatus = config
                  .getOptionValue("commentstatus");
              Integer commentOffset;
              try {
                commentOffset = Integer
                    .valueOf(config.getOptionValue("commentoffset"));
              } catch (final NumberFormatException e) {
                commentOffset = null;
              }
              Integer commentNumber;
              try {
                commentNumber = Integer
                    .valueOf(config.getOptionValue("commentnumber"));
              } catch (final Exception e) {
                commentNumber = null;
              }
              printComments(wp, postID, commentStatus, commentOffset,
                  commentNumber);
            } else {
              showHelp(options);
            }
          } catch (final MalformedURLException e) {
            System.err.println("URL \"" + config.getOptionValue("url")
                + "\" is invalid, reason is: " + e.getLocalizedMessage());
          } catch (final IOException e) {
            System.err.println(
                "Can't read from file, reason is: " + e.getLocalizedMessage());
          } catch (final InvalidPostFormatException e) {
            System.err.println("Input format is invalid.");
          }
        }
      } catch (final ParseException e) {
        System.err.println("Can't process command line arguments, reason is: "
            + e.getLocalizedMessage());
      }
    } catch (final XmlRpcFault e) {
      final String reason = e.getLocalizedMessage();
      System.err.println("Operation failed, reason is: " + reason);
    }
  }
  
  private static void printComment(final Wordpress wp, final Integer commentID)
      throws XmlRpcFault, InsufficientRightsException, ObjectNotFoundException {
    final Comment r = wp.getComment(commentID);
    System.out.println(r);
  }
  
  @SuppressWarnings("nls")
  private static void printComments(final Wordpress wp, final Integer postID,
      final String commentStatus, final Integer commentOffset,
      final Integer commentNumber)
          throws XmlRpcFault, InsufficientRightsException {
    final List<Comment> r = wp.getComments(commentStatus, postID, commentNumber,
        commentOffset);
    for (final Comment comment : r) {
      System.out.println("--- BEGIN COMMENT");
      System.out.println(comment);
      System.out.println("--- END COMMENT");
    }
  }
  
  private static void printItem(final Object o, final Class<?> cl) {
    cl.cast(o);
    System.out.println(((StringHeader) o).getStringHeader());
    System.out.println(o);
  }
  
  private static void printList(final List<?> r, final Class<?> cl,
      final boolean oneLiner) {
    boolean headerPrinted = false;
    for (final Object o : r) {
      cl.cast(o);
      if (!headerPrinted) {
        if (!(o instanceof String) && oneLiner) {
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
  
  @SuppressWarnings("nls")
  private static void showCommentCount(final WpCliConfiguration config,
      final Wordpress wp) throws InsufficientRightsException {
    final Integer post_ID = getInteger("commentcount", config);
    try {
      final CommentCount result = wp.getCommentsCount(post_ID);
      System.out.println(result);
    } catch (final XmlRpcFault e) {
      final String reason = e.getLocalizedMessage();
      System.err.println("Operation failed, reason is: " + reason);
    }
  }
  
  private static void showCommentStatus(final Wordpress wp) throws XmlRpcFault {
    printItem(wp.getCommentStatusList(), CommentStatusList.class);
  }
  
  @SuppressWarnings("nls")
  private static void showHelp(final Options options) {
    final HelpFormatter help = new HelpFormatter();
    help.printHelp(" ", options);
  }
}
