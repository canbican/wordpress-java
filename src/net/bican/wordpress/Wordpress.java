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
      XmlRpcStruct post, Boolean publish) throws XmlRpcFault;
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
    InputStream is = null;
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
      result = bytes;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (is != null)
        try {
          is.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
    }
    return result;
  }

  private BloggerBridge            blogger;

  private DemoBridge               demo        = null;

  private MovableTypeBridge        mt          = null;

  private MetaWebLogBridge         mw          = null;

  private String                   password    = null;

  private PingbackBridge           pingback    = null;

  private PingbackExtensionsBridge pingbackExt = null;

  private String                   username    = null;

  private WordpressBridge          wp          = null;

  private String                   xmlRpcUrl   = null;

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
   *           If the URL is faulty
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
   *           Generic exception for xml-rpc operations
   */
  @SuppressWarnings("boxing")
  public double addTwoNumbers(double number1, double number2)
      throws XmlRpcFault {
    return this.demo.addTwoNumbers(number1, number2);
  }

  /**
   * @param post_ID
   *          ID of the page to delete
   * @param publish
   *          Publish status
   * @return Result of deletion
   * @throws XmlRpcFault
   *           Generic exception for xml-rpc operations
   */
  @SuppressWarnings("boxing")
  public Boolean deletePage(int post_ID, String publish) throws XmlRpcFault {
    return this.wp.deletePage(0, this.username, this.password,
        Integer.valueOf(post_ID), publish);
  }

  /**
   * @param post_ID
   *          ID of the post to delete
   * @param publish
   *          Publish status
   * @return result of deletion
   * @throws XmlRpcFault
   *           Generic exception for xml-rpc operations
   */
  @SuppressWarnings("boxing")
  public Boolean deletePost(int post_ID, String publish) throws XmlRpcFault {
    return this.blogger.deletePost(0, post_ID, this.username, this.password,
        publish);
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
   *           Generic exception for xml-rpc operations
   */
  @SuppressWarnings("boxing")
  public Boolean editPage(int post_ID, Page page, String publish)
      throws XmlRpcFault {
    XmlRpcStruct post = page.toXmlRpcStruct();
    return this.wp.editPage(0, post_ID, this.username, this.password, post,
        publish);
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
   *           Generic exception for xml-rpc operations
   */
  @SuppressWarnings("boxing")
  public Boolean editPost(int post_ID, Page page, String publish)
      throws XmlRpcFault {
    XmlRpcStruct post = page.toXmlRpcStruct();
    return this.mw.editPost(post_ID, this.username, this.password, post,
        publish);
  }

  @SuppressWarnings({ "rawtypes", "unchecked", "static-method" })
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

  @SuppressWarnings("static-method")
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
   *           Generic exception for xml-rpc operations
   */
  @SuppressWarnings("unchecked")
  public List<Author> getAuthors() throws XmlRpcFault {
    XmlRpcArray r = this.wp.getAuthors(0, this.username, this.password);
    return fillFromXmlRpcArray(r, Author.class);
  }

  /**
   * @return List of categories
   * @throws XmlRpcFault
   *           Generic exception for xml-rpc operations
   */
  @SuppressWarnings("unchecked")
  public List<Category> getCategories() throws XmlRpcFault {
    XmlRpcArray r = this.wp.getCategories(0, this.username, this.password);
    return fillFromXmlRpcArray(r, Category.class);
  }

  /**
   * @param pageid
   *          Page ID
   * @return The <code>Page</code> object.
   * @throws XmlRpcFault
   *           Generic exception for xml-rpc operations
   */
  @SuppressWarnings("boxing")
  public Page getPage(int pageid) throws XmlRpcFault {
    XmlRpcStruct r = this.wp.getPage(0, pageid, this.username, this.password);
    Page result = new Page();
    result.fromXmlRpcStruct(r);
    return result;
  }

  /**
   * @return List of Pages, short format
   * @throws XmlRpcFault
   *           Generic exception for xml-rpc operations
   */
  @SuppressWarnings({ "unchecked", "boxing" })
  public List<PageDefinition> getPageList() throws XmlRpcFault {
    XmlRpcArray r = this.wp.getPageList(0, this.username, this.password);
    return fillFromXmlRpcArray(r, PageDefinition.class);
  }

  /**
   * @return List of Pages, in full format
   * @throws XmlRpcFault
   *           Generic exception for xml-rpc operations
   */
  @SuppressWarnings({ "unchecked", "boxing" })
  public List<Page> getPages() throws XmlRpcFault {
    XmlRpcArray r = this.wp.getPages(0, this.username, this.password);
    return fillFromXmlRpcArray(r, Page.class);
  }

  /**
   * @param postId
   *          Post ID
   * @return Trackbacks for the post
   * @throws XmlRpcFault
   *           Generic exception for xml-rpc operations
   */
  @SuppressWarnings({ "unchecked", "boxing" })
  public List<Ping> getTrackbackPings(int postId) throws XmlRpcFault {
    XmlRpcArray r = this.mt.getTrackbackPings(postId);
    return fillFromXmlRpcArray(r, Ping.class);
  }

  /**
   * @param url
   *          Url of the page queried
   * @return List of URLs
   * @throws XmlRpcFault
   *           Generic exception for xml-rpc operations
   */
  public List<URL> getPingbacks(String url) throws XmlRpcFault {
    List<URL> result = null;
    try {
      XmlRpcArray r = this.pingbackExt.getPingbacks(url);
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
   *           Generic exception for xml-rpc operations
   */
  @SuppressWarnings("boxing")
  public Page getPost(int post_ID) throws XmlRpcFault {
    XmlRpcStruct r = this.mw.getPost(post_ID, this.username, this.password);
    Page result = new Page();
    result.fromXmlRpcStruct(r);
    return result;
  }

  /**
   * @param num_posts
   *          Number of posts to be retrieved.
   * @return List of pages.
   * @throws XmlRpcFault
   *           Generic exception for xml-rpc operations
   */
  @SuppressWarnings({ "unchecked", "boxing" })
  public List<Page> getRecentPosts(int num_posts) throws XmlRpcFault {
    XmlRpcArray r = this.mw.getRecentPosts(0, this.username, this.password,
        num_posts);
    return fillFromXmlRpcArray(r, Page.class);
  }

  /**
   * @return Template page
   * @throws XmlRpcFault
   *           Generic exception for xml-rpc operations
   */
  @SuppressWarnings({ "boxing", "nls" })
  public String getTemplate() throws XmlRpcFault {
    return this.blogger.getTemplate(0, 0, this.username, this.password, "");
  }

  /**
   * @return The user information
   * @throws XmlRpcFault
   *           Generic exception for xml-rpc operations
   */
  @SuppressWarnings("boxing")
  public User getUserInfo() throws XmlRpcFault {
    XmlRpcStruct r = this.blogger.getUserInfo(0, this.username, this.password);
    User result = new User();
    result.fromXmlRpcStruct(r);
    return result;
  }

  /**
   * @return List of blogs the user has (only one in wordpress case)
   * @throws XmlRpcFault
   *           Generic exception for xml-rpc operations
   */
  @SuppressWarnings({ "unchecked", "boxing" })
  public List<Blog> getUsersBlogs() throws XmlRpcFault {
    XmlRpcArray r = this.blogger.getUsersBlogs(0, this.username, this.password);
    return fillFromXmlRpcArray(r, Blog.class);
  }

  @SuppressWarnings("nls")
  private void initMetaWebLog() throws MalformedURLException {
    final URL url = new URL(this.xmlRpcUrl);
    this.wp = (WordpressBridge) XmlRpcProxy.createProxy(url, "wp",
        new Class[] { WordpressBridge.class }, true);
    this.mw = (MetaWebLogBridge) XmlRpcProxy.createProxy(url, "metaWeblog",
        new Class[] { MetaWebLogBridge.class }, true);
    this.mt = (MovableTypeBridge) XmlRpcProxy.createProxy(url, "mt",
        new Class[] { MovableTypeBridge.class }, true);
    this.demo = (DemoBridge) XmlRpcProxy.createProxy(url, "demo",
        new Class[] { DemoBridge.class }, true);
    this.pingback = (PingbackBridge) XmlRpcProxy.createProxy(url, "pingback",
        new Class[] { PingbackBridge.class }, true);
    this.blogger = (BloggerBridge) XmlRpcProxy.createProxy(url, "blogger",
        new Class[] { BloggerBridge.class }, true);
    this.pingbackExt = (PingbackExtensionsBridge) XmlRpcProxy.createProxy(url,
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
   *           Generic exception for xml-rpc operations
   */
  @SuppressWarnings({ "unchecked", "nls", "boxing" })
  public int newCategory(String name, String slug, int parentId)
      throws XmlRpcFault {
    XmlRpcStruct h = new XmlRpcStruct();
    h.put("name", name);
    h.put("slug", slug);
    h.put("parent_id", parentId);
    return this.wp.newCategory(0, this.username, this.password, h);
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
   *           Generic exception for xml-rpc operations
   */
  @SuppressWarnings("boxing")
  public MediaObject newMediaObject(String mimeType, File file,
      Boolean overwrite) throws XmlRpcFault {
    Attachment att = new Attachment();
    att.setType(mimeType);
    att.setOverwrite(overwrite);
    att.setName(file.getName());
    att.setBits(getBytesFromFile(file));
    XmlRpcStruct d = att.toXmlRpcStruct();
    XmlRpcStruct r = this.mw.newMediaObject(0, this.username, this.password, d);
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
   *           Generic exception for xml-rpc operations
   */
  @SuppressWarnings("boxing")
  public String newPage(Page post, String publish) throws XmlRpcFault {
    return this.wp.newPage(0, this.username, this.password,
        post.toXmlRpcStruct(), publish);
  }

  /**
   * @param page
   *          Post information
   * @param publish
   *          Publish status
   * @return Post id
   * @throws XmlRpcFault
   *           Generic exception for xml-rpc operations
   */
  @SuppressWarnings("boxing")
  public String newPost(Page page, boolean publish) throws XmlRpcFault {
    return this.mw.newPost(0, this.username, this.password,
        page.toXmlRpcStruct(), publish);
  }

  /**
   * @param pagelinkedfrom
   *          Source
   * @param pagelinkedto
   *          Destination
   * @return response for ping
   * @throws XmlRpcFault
   *           Generic exception for xml-rpc operations
   */
  public String ping(String pagelinkedfrom, String pagelinkedto)
      throws XmlRpcFault {
    return this.pingback.ping(pagelinkedfrom, pagelinkedto);
  }

  /**
   * @return A very important message
   * @throws XmlRpcFault
   *           Generic exception for xml-rpc operations
   */
  public String sayHello() throws XmlRpcFault {
    return this.demo.sayHello();
  }

  /**
   * @param content
   *          Content of the template
   * @return Result of the operation
   * @throws XmlRpcFault
   *           Generic exception for xml-rpc operations
   */
  @SuppressWarnings({ "boxing", "nls" })
  public boolean setTemplate(String content) throws XmlRpcFault {
    return this.blogger.setTemplate(0, 0, this.username, this.password,
        content, "");
  }

  /**
   * @param category
   *          Category to search
   * @param max_results
   *          Maximum results to return
   * @return List of suggested categories
   * @throws XmlRpcFault
   *           Generic exception for xml-rpc operations
   */
  @SuppressWarnings("static-method")
  public List<Category> suggestCategories(String category, Integer max_results)
      throws XmlRpcFault {
    throw new UnsupportedOperationException(); // couldn't quite figure out the
    // response.
  }

  /**
   * @return List of supported methods
   * @throws XmlRpcFault
   *           Generic exception for xml-rpc operations
   */
  public List<String> supportedMethods() throws XmlRpcFault {
    return fromStringArray(this.mt.supportedMethods());
  }

  /**
   * @return List of supported text filters
   * @throws XmlRpcFault
   *           Generic exception for xml-rpc operations
   */
  public List<String> supportedTextFilters() throws XmlRpcFault {
    return fromStringArray(this.mt.supportedTextFilters());
  }

  /**
   * @return List of supported post status values
   * @throws XmlRpcFault
   *           Generic exception for xml-rpc operations
   */
  @SuppressWarnings("boxing")
  public List<PostAndPageStatus> getPostStatusList() throws XmlRpcFault {
    return processKeyValList(this.wp.getPostStatusList(0, this.username,
        this.password));
  }

  /**
   * @return List of supported page status values
   * @throws XmlRpcFault
   *           Generic exception for xml-rpc operations
   */
  @SuppressWarnings("boxing")
  public List<PostAndPageStatus> getPageStatusList() throws XmlRpcFault {
    return processKeyValList(this.wp.getPageStatusList(0, this.username,
        this.password));
  }

  @SuppressWarnings({ "static-method", "nls" })
  private List<PostAndPageStatus> processKeyValList(XmlRpcStruct r) {
    String response = r.toString();
    response = response.replaceAll("[{}]", "").replaceAll(",  *", ",");
    String[] responses = response.split(",");
    List<PostAndPageStatus> result = new ArrayList<PostAndPageStatus>();
    for (String rp : responses) {
      String[] keyval = rp.split("=");
      PostAndPageStatus pp = new PostAndPageStatus();
      pp.setStatus(keyval[0]);
      pp.setDescription(keyval[1]);
      result.add(pp);
    }
    return result;
  }

  /**
   * @param post_ID
   *          Blog Post ID
   * @return Number of comments (approved, spam, otherwise)
   * @throws XmlRpcFault
   *           Generic exception for xml-rpc operations
   */
  @SuppressWarnings("boxing")
  public CommentCount getCommentsCount(Integer post_ID) throws XmlRpcFault {
    XmlRpcStruct struct;
    if (post_ID != -1)
      struct = this.wp
          .getCommentCount(0, this.username, this.password, post_ID);
    else
      struct = this.wp.getCommentCount(0, this.username, this.password);
    CommentCount cc = new CommentCount();
    cc.fromXmlRpcStruct(struct);
    return cc;
  }

  /**
   * 
   * @param status
   *          One of "approve", "hold", or "spam". Or, null to show all.
   * @param post_id
   *          Filter comments by post_id, or null to not filter.
   * @param number
   *          The number of comments to return, or null for the default (of 10)
   * @param offset
   *          The offset into the set of comments to return, or null for 0
   * @return A list of Comment objects
   * @throws XmlRpcFault
   *           Generic exception for xml-rpc operations
   */
  @SuppressWarnings({ "unchecked", "nls", "boxing" })
  public List<Comment> getComments(String status, Integer post_id,
      Integer number, Integer offset) throws XmlRpcFault {

    XmlRpcStruct filter = new XmlRpcStruct();

    if (status != null) {
      filter.put("status", status);
    }

    if (post_id != null) {
      filter.put("post_id", post_id);
    }

    if (number != null) {
      filter.put("number", number);
    }

    if (offset != null) {
      filter.put("offset", offset);
    }

    XmlRpcArray r = this.wp
        .getComments(0, this.username, this.password, filter);
    return fillFromXmlRpcArray(r, Comment.class);
  }

  /**
   * @param comment_id
   *          comment_id to fetch
   * @return A Comment object
   * @throws XmlRpcFault
   *           Generic exception for xml-rpc operations
   */
  @SuppressWarnings("boxing")
  public Comment getComment(Integer comment_id) throws XmlRpcFault {
    XmlRpcStruct struct = this.wp.getComment(0, this.username, this.password,
        comment_id);

    Comment comment = new Comment();
    comment.fromXmlRpcStruct(struct);

    return comment;
  }

  /**
   * @param post_id
   *          Post to attach the comment to.
   * @param comment_parent
   *          Id of the parent comment (for threading)
   * @param content
   *          Content of comment
   * @param author
   *          Author's name
   * @param author_url
   *          Author's URL (can be empty)
   * @param author_email
   *          Author's Email Address
   * @return the id for the newly created comment.
   * @throws XmlRpcFault
   *           Generic exception for xml-rpc operations
   */
  @SuppressWarnings({ "unchecked", "nls", "boxing" })
  public Integer newComment(Integer post_id, Integer comment_parent,
      String content, String author, String author_url, String author_email)
      throws XmlRpcFault {

    XmlRpcStruct comment = new XmlRpcStruct();

    if (comment_parent != null) {
      comment.put("comment_parent", comment_parent);
    }

    comment.put("content", content);
    if (author != null)
      comment.put("author", author);
    if (author_url != null)
      comment.put("author_url", author_url);
    if (author_email != null)
      comment.put("author_email", author_email);

    Integer comment_id = this.wp.newComment(0, this.username, this.password,
        post_id, comment);

    return comment_id;
  }

  /**
   * @return the comment status list
   */
  public CommentStatusList getCommentStatusList() {
    XmlRpcStruct csl = this.wp.getCommentStatusList(0, this.username,
        this.password);
    CommentStatusList result = new CommentStatusList();
    result.fromXmlRpcStruct(csl);
    return result;
  }

  /**
   * @param commentID
   *          comment id to delete
   * @return result of the operation
   * @throws XmlRpcFault
   */
  @SuppressWarnings("boxing")
  public boolean deleteComment(int commentID) throws XmlRpcFault {
    return this.wp.deleteComment(0, this.username, this.password, commentID);
  }

  /**
   * @param comment
   * @return Result of the operation
   * @throws XmlRpcFault
   */
  @SuppressWarnings("boxing")
  public boolean editComment(Comment comment) throws XmlRpcFault {
    Boolean r = this.wp.editComment(0, this.username, this.password,
        comment.getComment_id(), comment);
    return r;
  }

  /**
   * @param categoryId
   * @return Result of the operation
   * @throws XmlRpcFault
   */
  @SuppressWarnings("boxing")
  public int deleteCategory(int categoryId) throws XmlRpcFault {
    Object r = this.wp.deleteCategory(0, this.username, this.password,
        categoryId);
    if (r instanceof Boolean)
      return 0;
    return (Integer) r;
  }
}

interface WordpressBridge {

  Boolean editComment(Integer blogid, String username, String password,
      Integer comment_id, Comment comment) throws XmlRpcFault;

  Object deleteCategory(Integer blogid, String username, String password,
      Integer category_id);

  Boolean deleteComment(Integer blogid, String username, String password,
      Integer comment_id) throws XmlRpcFault;

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

  XmlRpcStruct getCommentStatusList(int i, String username, String password);

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

  XmlRpcStruct getPostStatusList(Integer blogid, String username,
      String password) throws XmlRpcFault;

  XmlRpcStruct getPageStatusList(Integer blogid, String username,
      String password) throws XmlRpcFault;

  XmlRpcStruct getCommentCount(Integer blogid, String username,
      String password, Integer post_ID) throws XmlRpcFault;

  XmlRpcStruct getCommentCount(Integer blogid, String username, String password)
      throws XmlRpcFault;

  XmlRpcArray getComments(Integer blogid, String username, String password,
      XmlRpcStruct filter) throws XmlRpcFault;

  XmlRpcStruct getComment(Integer blogid, String username, String password,
      Integer comment_id) throws XmlRpcFault;

  Integer newComment(Integer blogid, String username, String password,
      Integer post_id, XmlRpcStruct comment) throws XmlRpcFault;

}
