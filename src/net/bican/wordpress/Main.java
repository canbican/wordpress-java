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

import java.io.File;
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
   * @param args execute with "-?" for an explanation of args
   * @throws ParseException When the command line options cannot be parsed
   */
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
            Wordpress wp = new Wordpress(config.getOptionValue("user"), config
                .getOptionValue("pass"), config.getOptionValue("url"));
            if (config.hasOption("authors")) {
              printList(wp.getAuthors(), Author.class, true);
            } else if (config.hasOption("categories")) {
              printList(wp.getCategories(), Category.class, true);
            } else if (config.hasOption("newcategory")) {
              String slug = config.getOptionValue("slug");
              Integer parentId = getInteger("parentid", config);
              if (slug == null)
                slug = "";
              if (parentId == null)
                parentId = 0;
              System.out.println(wp.newCategory(config
                  .getOptionValue("newcategory"), slug, parentId));
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
                System.out.println(wp.newPage(Page.fromFile(new File(config
                    .getOptionValue("newpage"))), config
                    .getOptionValue("publish")));
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
                System.out.println(wp.newPost(Page.fromFile(new File(config
                    .getOptionValue("newpost"))), config
                    .getOptionValue("publish")));
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
