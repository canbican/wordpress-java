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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import redstone.xmlrpc.XmlRpcArray;
import redstone.xmlrpc.XmlRpcFault;
import redstone.xmlrpc.XmlRpcProxy;
import redstone.xmlrpc.XmlRpcStruct;

interface BloggerBridge {
  /**
   * @param blogid
   *          Blog id, not used in wordpress
   * @param username
   *          User name
   * @param password
   *          Password
   * @param post_ID
   *          Page ID to delete
   * @param publish
   *          Publish status
   * @return result of deletion
   * @throws XmlRpcFault
   */
  Boolean deletePost(Integer blogid, Integer post_ID, String username,
      String password, String publish) throws XmlRpcFault;

  /**
   * @param placeHolder
   *          No reference about what it is in xmlrpc.php, but it's not used
   * @param blogid
   *          Blog id, not used in wordpress
   * @param username
   *          User name
   * @param password
   *          Password
   * @param template
   *          Template, not used in wordpress
   * @return Template page
   * @throws XmlRpcFault
   */
  String getTemplate(Integer placeHolder, Integer blogid, String username,
      String password, String template) throws XmlRpcFault;

  /**
   * @param placeHolder
   *          No reference about what it is in xmlrpc.php, but it's not used
   * @param username
   *          User name
   * @param password
   *          Password
   * @return User information
   * @throws XmlRpcFault
   */
  XmlRpcStruct getUserInfo(Integer placeHolder, String username, String password)
      throws XmlRpcFault;

  /**
   * @param placeHolder
   *          No reference about what it is in xmlrpc.php, but it's not used
   * @param username
   *          User name
   * @param password
   *          Password
   * @return Blog that user has
   * @throws XmlRpcFault
   */
  XmlRpcArray getUsersBlogs(Integer placeHolder, String username,
      String password) throws XmlRpcFault;

  /**
   * @param placeHolder
   *          No reference about what it is in xmlrpc.php, but it's not used
   * @param blogid
   *          Blog id, not used in wordpress
   * @param username
   *          User name
   * @param password
   *          Password
   * @param content
   *          Content of the template
   * @param template
   *          Template, not used in wordpress
   * @return Result of the operation
   * @throws XmlRpcFault
   */
  Boolean setTemplate(Integer placeHolder, Integer blogid, String username,
      String password, String content, String template) throws XmlRpcFault;
}

interface DemoBridge {
  /**
   * @param number1
   *          first number
   * @param number2
   *          second number
   * @return addition of the arguments
   * @throws XmlRpcFault
   */
  Double addTwoNumbers(Double number1, Double number2) throws XmlRpcFault;

  /**
   * @return A very important message
   * @throws XmlRpcFault
   */
  String sayHello() throws XmlRpcFault;
}

interface MetaWebLogBridge {
  /**
   * @param post_ID
   *          ID of the post to edit
   * @param username
   *          User name
   * @param password
   *          Password
   * @param post
   *          Page information
   * @param publish
   *          Post status
   * @return result of edit
   * @throws XmlRpcFault
   */
  Boolean editPost(Integer post_ID, String username, String password,
      XmlRpcStruct post, String publish) throws XmlRpcFault;

  /**
   * @param post_ID
   *          ID of the post to retrieve
   * @param username
   *          User name
   * @param password
   *          Password
   * @return Page information
   * @throws XmlRpcFault
   */
  XmlRpcStruct getPost(Integer post_ID, String username, String password)
      throws XmlRpcFault;

  /**
   * @param blogid
   *          Blog id (not used in wordpress)
   * @param username
   *          User name
   * @param password
   *          Password
   * @param num_posts
   *          Number of posts to retrieve
   * @return List of pages
   * @throws XmlRpcFault
   */
  XmlRpcArray getRecentPosts(Integer blogid, String username, String password,
      Integer num_posts) throws XmlRpcFault;

  /**
   * @param blogid
   *          Blog id (not used in wordpress)
   * @param username
   *          User name
   * @param password
   *          Password
   * @param data
   *          Data Structure (type,bits,overwrite)
   * @return result of the upload
   * @throws XmlRpcFault
   */
  XmlRpcStruct newMediaObject(Integer blogid, String username, String password,
      XmlRpcStruct data) throws XmlRpcFault;

  /**
   * @param blogid
   *          Blog id (not used in wordpress)
   * @param username
   *          User name
   * @param password
   *          Password
   * @param post
   *          Post structure
   * @param publish
   *          Publish status
   * @return post id
   * @throws XmlRpcFault
   */
  String newPost(Integer blogid, String username, String password,
      XmlRpcStruct post, String publish) throws XmlRpcFault;
}

interface MovableTypeBridge {
  /**
   * @return List of methods the server supports
   * @throws XmlRpcFault
   */
  XmlRpcArray supportedMethods() throws XmlRpcFault;

  /**
   * @return List of supported text filters
   * @throws XmlRpcFault
   */
  XmlRpcArray supportedTextFilters() throws XmlRpcFault;

  /**
   * @param PostId
   *          Post id
   * @return List of trackbacks for the post
   * @throws XmlRpcFault
   */
  XmlRpcArray getTrackbackPings(Integer PostId) throws XmlRpcFault;
}

interface PingbackBridge {
  /**
   * @param pagelinkedfrom
   *          Source
   * @param pagelinkedto
   *          Destination
   * @return response string
   * @throws XmlRpcFault
   */
  String ping(String pagelinkedfrom, String pagelinkedto) throws XmlRpcFault;
}

interface PingbackExtensionsBridge {
  /**
   * @param url
   *          url of the page queried
   * @return Array of URLs
   * @throws XmlRpcFault
   */
  XmlRpcArray getPingbacks(String url) throws XmlRpcFault;
}

/**
 * 
 * The utility class that links xmlrpc calls to Java functions.
 * 
 * @author Can Bican &lt;can@bican.net&gt;
 * 
 */
public class Wordpress {

  private static byte[] getBytesFromFile(File file) {
    byte[] result = null;
    InputStream is;
    try {
      is = new FileInputStream(file);

      // Get the size of the file
      long length = file.length();

      // You cannot create an array using a long type.
      // It needs to be an int type.
      // Before converting to an int type, check
      // to ensure that file is not larger than Integer.MAX_VALUE.
      if (length > Integer.MAX_VALUE) {
        // File is too large
      }

      // Create the byte array to hold the data
      byte[] bytes = new byte[(int) length];

      // Read in the bytes
      int offset = 0;
      int numRead = 0;
      while (offset < bytes.length
          && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
        offset += numRead;
      }

      // Ensure all the bytes have been read in
      if (offset < bytes.length) {
        throw new IOException(
            "Could not completely read file " + file.getName()); //$NON-NLS-1$
      }

      // Close the input stream and return bytes
      is.close();
      result = bytes;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result;
  }

  private BloggerBridge blogger;

  private DemoBridge demo = null;

  private MovableTypeBridge mt = null;

  private MetaWebLogBridge mw = null;

  private String password = null;

  private PingbackBridge pingback = null;

  private PingbackExtensionsBridge pingbackExt = null;
  private String username = null;
  private WordpressBridge wp = null;
  private String xmlRpcUrl = null;

  @SuppressWarnings("unused")
  private Wordpress() {
    // no default constructor - class needs username, password and url
  }

  /**
   * @param username
   *          User name
   * @param password
   *          Password
   * @param xmlRpcUrl
   *          xmlrpc communication point, usually blogurl/xmlrpc.php
   * @throws MalformedURLException
   */
  public Wordpress(String username, String password, String xmlRpcUrl)
      throws MalformedURLException {
    this.username = username;
    this.password = password;
    this.xmlRpcUrl = xmlRpcUrl;
    initMetaWebLog();
  }

  /**
   * @param number1
   *          First number
   * @param number2
   *          Second number
   * @return addition of two numbers
   * @throws XmlRpcFault
   */
  public double addTwoNumbers(double number1, double number2)
      throws XmlRpcFault {
    return demo.addTwoNumbers(number1, number2);
  }

  /**
   * @param post_ID
   *          ID of the page to delete
   * @param publish
   *          Publish status
   * @return Result of deletion
   * @throws XmlRpcFault
   */
  public Boolean deletePage(int post_ID, String publish) throws XmlRpcFault {
    return wp.deletePage(0, username, password, post_ID, publish);
  }

  /**
   * @param post_ID
   *          ID of the post to delete
   * @param publish
   *          Publish status
   * @return result of deletion
   * @throws XmlRpcFault
   */
  public Boolean deletePost(int post_ID, String publish) throws XmlRpcFault {
    return blogger.deletePost(0, post_ID, username, password, publish);
  }

  /**
   * @param post_ID
   *          ID of the post to edit
   * @param page
   *          Page information
   * @param publish
   *          Publish status
   * @return Result of edit
   * @throws XmlRpcFault
   */
  public Boolean editPage(int post_ID, Page page, String publish)
      throws XmlRpcFault {
    XmlRpcStruct post = page.toXmlRpcStruct();
    return wp.editPage(0, post_ID, username, password, post, publish);
  }

  /**
   * @param post_ID
   *          ID of the post to edit
   * @param page
   *          Page information
   * @param publish
   *          Publish status
   * @return result of edit
   * @throws XmlRpcFault
   */
  public Boolean editPost(int post_ID, Page page, String publish)
      throws XmlRpcFault {
    XmlRpcStruct post = page.toXmlRpcStruct();
    return mw.editPost(post_ID, username, password, post, publish);
  }

  @SuppressWarnings("unchecked")
  private List fillFromXmlRpcArray(XmlRpcArray r, Class cl) {
    List result = null;
    try {
      result = new ArrayList();
      for (Object o : r) {
        XmlRpcMapped n = (XmlRpcMapped) cl.newInstance();
        if (o instanceof String) {
          result.add(o);
        } else {
          n.fromXmlRpcStruct((XmlRpcStruct) o);
        }
        result.add(n);
      }
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return result;
  }

  private List<String> fromStringArray(XmlRpcArray r) {
    List<String> result;
    result = new ArrayList<String>();
    for (Object object : r) {
      result.add((String) object);
    }
    return result;
  }

  /**
   * @return List of authors
   * @throws XmlRpcFault
   */
  @SuppressWarnings("unchecked")
  public List<Author> getAuthors() throws XmlRpcFault {
    XmlRpcArray r = wp.getAuthors(0, username, password);
    return (List<Author>) fillFromXmlRpcArray(r, Author.class);
  }

  /**
   * @return List of categories
   * @throws XmlRpcFault
   */
  @SuppressWarnings("unchecked")
  public List<Category> getCategories() throws XmlRpcFault {
    XmlRpcArray r = wp.getCategories(0, username, password);
    return (List<Category>) fillFromXmlRpcArray(r, Category.class);
  }

  /**
   * @param pageid
   *          Page ID
   * @return The <code>Page</code> object.
   * @throws XmlRpcFault
   */
  public Page getPage(int pageid) throws XmlRpcFault {
    XmlRpcStruct r = wp.getPage(0, pageid, username, password);
    Page result = new Page();
    result.fromXmlRpcStruct(r);
    return result;
  }

  /**
   * @return List of Pages, short format
   * @throws XmlRpcFault
   */
  @SuppressWarnings("unchecked")
  public List<PageDefinition> getPageList() throws XmlRpcFault {
    XmlRpcArray r = wp.getPageList(0, username, password);
    return (List<PageDefinition>) fillFromXmlRpcArray(r, PageDefinition.class);
  }

  /**
   * @return List of Pages, in full format
   * @throws XmlRpcFault
   */
  @SuppressWarnings("unchecked")
  public List<Page> getPages() throws XmlRpcFault {
    XmlRpcArray r = wp.getPages(0, username, password);
    return (List<Page>) fillFromXmlRpcArray(r, Page.class);
  }

  /**
   * @param postId
   *          Post ID
   * @return Trackbacks for the post
   * @throws XmlRpcFault
   */
  @SuppressWarnings("unchecked")
  public List<Ping> getTrackbackPings(int postId) throws XmlRpcFault {
    XmlRpcArray r = mt.getTrackbackPings(postId);
    return (List<Ping>) fillFromXmlRpcArray(r, Ping.class);
  }

  /**
   * @param url
   *          Url of the page queried
   * @return List of URLs
   * @throws XmlRpcFault
   */
  @SuppressWarnings("unchecked")
  public List<URL> getPingbacks(String url) throws XmlRpcFault {
    List<URL> result = null;
    try {
      XmlRpcArray r = pingbackExt.getPingbacks(url);
      result = new ArrayList<URL>();
      for (Object rec : r) {
        result.add(new URL((String) rec));
      }
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    return result;
  }

  /**
   * @param post_ID
   *          ID of the post to retrieve
   * @return Page information
   * @throws XmlRpcFault
   */
  public Page getPost(int post_ID) throws XmlRpcFault {
    XmlRpcStruct r = mw.getPost(post_ID, username, password);
    Page result = new Page();
    result.fromXmlRpcStruct(r);
    return result;
  }

  /**
   * @param num_posts
   *          Number of posts to be retrieved.
   * @return List of pages.
   * @throws XmlRpcFault
   */
  @SuppressWarnings("unchecked")
  public List<Page> getRecentPosts(int num_posts) throws XmlRpcFault {
    XmlRpcArray r = mw.getRecentPosts(0, username, password, num_posts);
    return (List<Page>) fillFromXmlRpcArray(r, Page.class);
  }

  /**
   * @return Template page
   * @throws XmlRpcFault
   */
  public String getTemplate() throws XmlRpcFault {
    return blogger.getTemplate(0, 0, username, password, "");
  }

  /**
   * @return The user information
   * @throws XmlRpcFault
   */
  public User getUserInfo() throws XmlRpcFault {
    XmlRpcStruct r = blogger.getUserInfo(0, username, password);
    User result = new User();
    result.fromXmlRpcStruct(r);
    return result;
  }

  /**
   * @return List of blogs the user has (only one in wordpress case)
   * @throws XmlRpcFault
   */
  @SuppressWarnings("unchecked")
  public List<Blog> getUsersBlogs() throws XmlRpcFault {
    XmlRpcArray r = blogger.getUsersBlogs(0, username, password);
    return (List<Blog>) fillFromXmlRpcArray(r, Blog.class);
  }

  private void initMetaWebLog() throws MalformedURLException {
    final URL url = new URL(this.xmlRpcUrl);
    wp = (WordpressBridge) XmlRpcProxy.createProxy(url, "wp",
        new Class[] { WordpressBridge.class }, true);
    mw = (MetaWebLogBridge) XmlRpcProxy.createProxy(url, "metaWeblog",
        new Class[] { MetaWebLogBridge.class }, true);
    mt = (MovableTypeBridge) XmlRpcProxy.createProxy(url, "mt",
        new Class[] { MovableTypeBridge.class }, true);
    demo = (DemoBridge) XmlRpcProxy.createProxy(url, "demo",
        new Class[] { DemoBridge.class }, true);
    pingback = (PingbackBridge) XmlRpcProxy.createProxy(url, "pingback",
        new Class[] { PingbackBridge.class }, true);
    blogger = (BloggerBridge) XmlRpcProxy.createProxy(url, "blogger",
        new Class[] { BloggerBridge.class }, true);
    pingbackExt = (PingbackExtensionsBridge) XmlRpcProxy.createProxy(url,
        "pingback.extensions", new Class[] { PingbackExtensionsBridge.class },
        true);
  }

  /**
   * @param name
   *          Category name
   * @param slug
   *          Category short name
   * @param parentId
   *          Parent ID
   * @return New category id
   * @throws XmlRpcFault
   */
  @SuppressWarnings("unchecked")
  public int newCategory(String name, String slug, int parentId)
      throws XmlRpcFault {
    XmlRpcStruct h = new XmlRpcStruct();
    h.put("name", name);
    h.put("slug", slug);
    h.put("parent_id", parentId);
    return wp.newCategory(0, username, password, h);
  }

  /**
   * @param mimeType
   *          Mime type of the file
   * @param file
   *          File name
   * @param overwrite
   *          true/false
   * @return new object location
   * @throws XmlRpcFault
   */
  public MediaObject newMediaObject(String mimeType, File file,
      Boolean overwrite) throws XmlRpcFault {
    Attachment att = new Attachment();
    att.setType(mimeType);
    att.setOverwrite(overwrite);
    att.setName(file.getName());
    att.setBits(getBytesFromFile(file));
    XmlRpcStruct d = att.toXmlRpcStruct();
    XmlRpcStruct r = mw.newMediaObject(0, username, password, d);
    MediaObject result = new MediaObject();
    result.fromXmlRpcStruct(r);
    return result;
  }

  /**
   * @param post
   *          Page information
   * @param publish
   *          Publish status
   * @return Post ID
   * @throws XmlRpcFault
   */
  public String newPage(Page post, String publish) throws XmlRpcFault {
    return wp.newPage(0, username, password, post.toXmlRpcStruct(), publish);
  }

  /**
   * @param page
   *          Post information
   * @param publish
   *          Publish status
   * @return Post id
   * @throws XmlRpcFault
   */
  public String newPost(Page page, String publish) throws XmlRpcFault {
    return mw.newPost(0, username, password, page.toXmlRpcStruct(), publish);
  }

  /**
   * @param pagelinkedfrom
   *          Source
   * @param pagelinkedto
   *          Destination
   * @return response for ping
   * @throws XmlRpcFault
   */
  public String ping(String pagelinkedfrom, String pagelinkedto)
      throws XmlRpcFault {
    return pingback.ping(pagelinkedfrom, pagelinkedto);
  }

  /**
   * @return A very important message
   * @throws XmlRpcFault
   */
  public String sayHello() throws XmlRpcFault {
    return demo.sayHello();
  }

  /**
   * @param content
   *          Content of the template
   * @return Result of the operation
   * @throws XmlRpcFault
   */
  public boolean setTemplate(String content) throws XmlRpcFault {
    return blogger.setTemplate(0, 0, username, password, content, "");
  }

  /**
   * @param category
   *          Category to search
   * @param max_results
   *          Maximum results to return
   * @return List of suggested categories
   * @throws XmlRpcFault
   */
  @SuppressWarnings("unchecked")
  public List<Category> suggestCategories(String category, Integer max_results)
      throws XmlRpcFault {
    throw new UnsupportedOperationException(); // couldn't quite figure out the
    // response.
  }

  /**
   * @return List of supported methods
   * @throws XmlRpcFault
   */
  public List<String> supportedMethods() throws XmlRpcFault {
    return fromStringArray(mt.supportedMethods());
  }

  /**
   * @return List of supported text filters
   * @throws XmlRpcFault
   */
  public List<String> supportedTextFilters() throws XmlRpcFault {
    return fromStringArray(mt.supportedTextFilters());
  }
}

interface WordpressBridge {

  /**
   * @param blogid
   *          Blog id, not used in wordpress
   * @param username
   *          User name
   * @param password
   *          Password
   * @param post_ID
   *          ID of the post to delete
   * @param publish
   *          Publish status
   * @return Result of deletion--
   * @throws XmlRpcFault
   */
  Boolean deletePage(Integer blogid, String username, String password,
      Integer post_ID, String publish) throws XmlRpcFault;

  /**
   * @param blogid
   *          Blog id, not used in wordpress
   * @param post_ID
   *          ID of the post to edit
   * @param username
   *          User name
   * @param password
   *          Password
   * @param post
   *          Post information
   * @param publish
   *          Publish status
   * @return Result of edit
   * @throws XmlRpcFault
   */
  Boolean editPage(Integer blogid, Integer post_ID, String username,
      String password, XmlRpcStruct post, String publish) throws XmlRpcFault;

  /**
   * @param blogid
   *          Blog id, not used in wordpress
   * @param username
   *          User name
   * @param password
   *          Password
   * @return Array of Authors
   * @throws XmlRpcFault
   */
  XmlRpcArray getAuthors(int blogid, String username, String password)
      throws XmlRpcFault;

  /**
   * @param blogid
   *          Blog id, not used in wordpress
   * @param username
   *          User name
   * @param password
   *          Password
   * @return Array of categories
   * @throws XmlRpcFault
   */
  XmlRpcArray getCategories(int blogid, String username, String password)
      throws XmlRpcFault;

  /**
   * @param blogid
   *          Blog id, not used in wordpress
   * @param pageid
   *          Page id
   * @param username
   *          User name
   * @param password
   *          Password
   * @return Page information
   * @throws XmlRpcFault
   */
  XmlRpcStruct getPage(Integer blogid, Integer pageid, String username,
      String password) throws XmlRpcFault;

  /**
   * @param blogid
   *          Blog id, not used in wordpress
   * @param username
   *          User name
   * @param password
   *          Password
   * @return Array of Pages
   * @throws XmlRpcFault
   */
  XmlRpcArray getPageList(Integer blogid, String username, String password)
      throws XmlRpcFault;

  /**
   * @param blogid
   *          Blog id, not used in wordpress
   * @param username
   *          User name
   * @param password
   *          Password
   * @return Array of Pages
   * @throws XmlRpcFault
   */
  XmlRpcArray getPages(Integer blogid, String username, String password)
      throws XmlRpcFault;

  /**
   * @param blogid
   *          Blog id, not used in wordpress
   * @param username
   *          User name
   * @param password
   *          Password
   * @param category
   *          Category information
   * @return new category id
   * @throws XmlRpcFault
   */
  Integer newCategory(Integer blogid, String username, String password,
      XmlRpcStruct category) throws XmlRpcFault;

  /**
   * @param blogid
   *          Blog id, not used in wordpress
   * @param username
   *          User name
   * @param password
   *          Password
   * @param post
   *          Page information
   * @param publish
   *          Publish status
   * @return Post ID
   * @throws XmlRpcFault
   */
  String newPage(Integer blogid, String username, String password,
      XmlRpcStruct post, String publish) throws XmlRpcFault;

  /**
   * @param blogid
   *          Blog id, not used in wordpress
   * @param username
   *          User name
   * @param password
   *          Password
   * @param category
   *          Category to search
   * @param max_results
   *          Maximum results to return
   * @return List of suggested categories
   * @throws XmlRpcFault
   */
  XmlRpcArray suggestCategories(Integer blogid, String username,
      String password, String category, Integer max_results) throws XmlRpcFault;
}
