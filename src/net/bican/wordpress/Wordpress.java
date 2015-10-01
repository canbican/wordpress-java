/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.bican.wordpress.exceptions.FileUploadException;
import net.bican.wordpress.exceptions.InsufficientRightsException;
import net.bican.wordpress.exceptions.InvalidArgumentsException;
import net.bican.wordpress.exceptions.ObjectNotFoundException;
import redstone.xmlrpc.XmlRpcArray;
import redstone.xmlrpc.XmlRpcFault;
import redstone.xmlrpc.XmlRpcProxy;
import redstone.xmlrpc.XmlRpcStruct;

/**
 * The utility class that links xmlrpc calls to Java functions.
 *
 * @author Can Bican
 */
public class Wordpress {
  private static final Integer BLOGID = Integer.valueOf(0);
  private static final Logger logger = LoggerFactory.getLogger(Wordpress.class);

  @SuppressWarnings("unchecked")
  private static <T extends XmlRpcMapped> List<T> fillFromXmlRpcArray(
      final XmlRpcArray r, final Class<T> cl, final T item) {
    List<T> result = null;
    try {
      result = new ArrayList<>();
      for (final Object o : r) {
        final XmlRpcMapped n = cl.newInstance();
        if (o instanceof String) {
          result.add((T) o);
        } else {
          n.fromXmlRpcStruct((XmlRpcStruct) o);
        }
        result.add((T) n);
      }
    } catch (final InstantiationException e) {
      logger.error("cannot instantiate {}: {}", //$NON-NLS-1$
          cl.getCanonicalName(), e.getLocalizedMessage());
    } catch (final IllegalAccessException e) {
      logger.error("cannot access constructor of {}: {}", //$NON-NLS-1$
          cl.getCanonicalName(), e.getLocalizedMessage());
    }
    return result;
  }

  private static List<Option> structToOptions(final XmlRpcStruct r) {
    final List<Option> result = new ArrayList<>();
    for (final Object o : r.keySet()) {
      final String name = (String) o;
      final Option option = new Option();
      option.setName(name);
      option.fromXmlRpcStruct(r.getStruct(o));
      result.add(option);
    }
    return result;
  }

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
   *           If the URL is faulty
   */
  public Wordpress(final String username, final String password,
      final String xmlRpcUrl) throws MalformedURLException {
    this.username = username;
    this.password = password;
    this.xmlRpcUrl = xmlRpcUrl;
    this.initMetaWebLog();
  }
  
  /**
   * @param commentID
   *          comment id to delete
   * @return result of the operation
   * @throws InsufficientRightsException
   *           if the user does not have the moderate_comments cap or if the
   *           user does not have permission to edit this comment
   * @throws ObjectNotFoundException
   *           if no comment with that comment_id exists
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public boolean deleteComment(final Integer commentID)
      throws InsufficientRightsException, ObjectNotFoundException, XmlRpcFault {
    try {
      return this.wp
          .deleteComment(BLOGID, this.username, this.password, commentID)
          .booleanValue();
    } catch (final XmlRpcFault e) {
      final int err = e.getErrorCode();
      switch (err) {
        case 403:
          throw new InsufficientRightsException();
        case 404:
          throw new ObjectNotFoundException();
        default:
          throw e;
      }
    }
  }
  
  /**
   * @param postId
   *          post id to delete
   * @return true
   * @throws InsufficientRightsException
   *           if the user does not have permission to delete the post
   * @throws ObjectNotFoundException
   *           if no post with that post_id exists
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public boolean deletePost(final Integer postId)
      throws InsufficientRightsException, ObjectNotFoundException, XmlRpcFault {
    try {
      return this.wp.deletePost(BLOGID, this.username, this.password, postId)
          .booleanValue();
    } catch (final XmlRpcFault e) {
      final int err = e.getErrorCode();
      switch (err) {
        case 401:
          throw new InsufficientRightsException();
        case 404:
          throw new ObjectNotFoundException();
        default:
          throw e;
      }
    }
  }
  
  /**
   * @param taxonomy
   *          taxonomy name
   * @param termId
   *          term id
   * @return the result of the operation
   * @throws InsufficientRightsException
   *           if the user does not have the assign_terms cap for this taxonomy
   * @throws XmlRpcFault
   *           if there is a generic error during request
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public boolean deleteTerm(final String taxonomy, final Integer termId)
      throws InsufficientRightsException, XmlRpcFault {
    try {
      final Boolean r = this.wp.deleteTerm(BLOGID, this.username, this.password,
          taxonomy, termId);
      return r.booleanValue();
    } catch (final XmlRpcFault e) {
      final int err = e.getErrorCode();
      switch (err) {
        case 401:
          throw new InsufficientRightsException();
        case 404:
          return false;
        default:
          throw e;
      }
    }
  }
  
  /**
   * @param comment
   *          edited form of the comment object
   * @return Result of the operation
   * @throws InvalidArgumentsException
   *           if status is not a valid comment status
   * @throws InsufficientRightsException
   *           if the user does not have the moderate_comments cap or if the
   *           user does not have permission to edit this comment.
   * @throws ObjectNotFoundException
   *           if no comment with that comment_id exists
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public boolean editComment(final Comment comment)
      throws InvalidArgumentsException, InsufficientRightsException,
      ObjectNotFoundException, XmlRpcFault {
    Boolean r;
    try {
      r = this.wp.editComment(BLOGID, this.username, this.password,
          comment.getComment_id(), comment);
      return r.booleanValue();
    } catch (final XmlRpcFault e) {
      final int err = e.getErrorCode();
      switch (err) {
        case 401:
          throw new InvalidArgumentsException();
        case 403:
          throw new InsufficientRightsException();
        case 404:
          throw new ObjectNotFoundException();
        default:
          throw e;
      }
    }
  }
  
  /**
   * @param postId
   *          post id to edit
   * @param post
   *          edited contents
   * @return true
   * @throws InsufficientRightsException
   *           one of the following reasons:
   *           <ul>
   *           <li>if the user does not have the edit_posts cap for this post
   *           type</li>
   *           <li>if user does not have permission to create post of the
   *           specified post_status</li>
   *           <li>if post_author is different than the user's ID and the user
   *           does not have the edit_others_posts cap for this post type</li>
   *           <li>if sticky is passed and user does not have permission to make
   *           the post sticky, regardless if sticky is set to 0, 1, false or
   *           true</li>
   *           <li>if a taxonomy in terms or terms_names is not supported by
   *           this post type</li>
   *           <li>if terms or terms_names is set but user does not have
   *           assign_terms cap</li>
   *           <li>if an ambiguous term name is used in terms_names</li>
   *           </ul>
   * @throws InvalidArgumentsException
   *           if invalid post_type is specified or if an invalid term ID is
   *           specified in terms
   * @throws ObjectNotFoundException
   *           if no author with that post_author ID exists or if no attachment
   *           with that post_thumbnail ID exists
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public boolean editPost(final Integer postId, final Post post)
      throws InsufficientRightsException, InvalidArgumentsException,
      ObjectNotFoundException, XmlRpcFault {
    try {
      final Boolean r = this.wp.editPost(BLOGID, this.username, this.password,
          postId, post.toXmlRpcStruct());
      return r.booleanValue();
    } catch (final XmlRpcFault e) {
      final int err = e.getErrorCode();
      switch (err) {
        case 401:
          throw new InsufficientRightsException();
        case 403:
          throw new InvalidArgumentsException();
        case 404:
          throw new ObjectNotFoundException();
        default:
          throw e;
      }
    }
  }
  
  /**
   * @param user
   *          contents of the edited fields
   * @return the result of the operation
   * @throws InsufficientRightsException
   *           if the user does not permission to edit his/her profile.
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  @SuppressWarnings({ "unchecked" })
  public boolean editProfile(final User user)
      throws InsufficientRightsException, XmlRpcFault {
    try {
      final XmlRpcStruct content = new XmlRpcStruct();
      if (user.getFirst_name() != null) {
        content.put("first_name", user.getFirst_name()); //$NON-NLS-1$
      }
      if (user.getLast_name() != null) {
        content.put("last_name", user.getLast_name()); //$NON-NLS-1$
      }
      if (user.getUrl() != null) {
        content.put("url", user.getUrl()); //$NON-NLS-1$
      }
      if (user.getDisplay_name() != null) {
        content.put("display_name", user.getDisplay_name()); //$NON-NLS-1$
      }
      if (user.getNickname() != null) {
        content.put("nickname", user.getNickname()); //$NON-NLS-1$
      }
      if (user.getNicename() != null) {
        content.put("nicename", user.getNicename()); //$NON-NLS-1$
      }
      final Boolean r = this.wp.editProfile(BLOGID, this.username,
          this.password, content);
      return r.booleanValue();
    } catch (final XmlRpcFault e) {
      final int err = e.getErrorCode();
      switch (err) {
        case 401:
          throw new InsufficientRightsException();
        default:
          throw e;
      }
    }
  }
  
  /**
   * @param termId
   *          term id
   * @param content
   *          new contents
   * @return the result of the operation
   * @throws InsufficientRightsException
   *           if the user does not have the assign_terms cap for this taxonomy
   * @throws InvalidArgumentsException
   *           if invalid taxonomy name is specified
   * @throws ObjectNotFoundException
   *           if the term id is not found
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  @SuppressWarnings({ "unchecked" })
  public boolean editTerm(final Integer termId, final Term content)
      throws InsufficientRightsException, InvalidArgumentsException,
      ObjectNotFoundException, XmlRpcFault {
    try {
      final XmlRpcStruct datar = new XmlRpcStruct();
      datar.put("taxonomy", content.getTaxonomy()); //$NON-NLS-1$
      if (content.getName() != null) {
        datar.put("name", content.getName()); //$NON-NLS-1$
      }
      if (content.getSlug() != null) {
        datar.put("slug", content.getSlug()); //$NON-NLS-1$
      }
      if (content.getDescription() != null) {
        datar.put("description", content.getDescription()); //$NON-NLS-1$
      }
      if (content.getParent() != null) {
        datar.put("parent", content.getParent()); //$NON-NLS-1$
      }
      final Boolean r = this.wp.editTerm(BLOGID, this.username, this.password,
          termId, datar);
      return r.booleanValue();
    } catch (final XmlRpcFault e) {
      final int err = e.getErrorCode();
      switch (err) {
        case 401:
          throw new InsufficientRightsException();
        case 403:
          throw new InvalidArgumentsException(content.getTaxonomy());
        case 404:
          throw new ObjectNotFoundException(termId.toString());
        default:
          throw e;
      }
    }
  }
  
  /**
   * @return the list of authors
   * @throws InsufficientRightsException
   *           if the user lacks the edit_posts cap
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public List<Author> getAuthors()
      throws InsufficientRightsException, XmlRpcFault {
    try {
      final XmlRpcArray r = this.wp.getAuthors(BLOGID, this.username,
          this.password);
      return fillFromXmlRpcArray(r, Author.class, new Author());
    } catch (final XmlRpcFault e) {
      final int err = e.getErrorCode();
      switch (err) {
        case 401:
          throw new InsufficientRightsException();
        default:
          throw e;
      }
    }
  }
  
  /**
   * @param comment_id
   *          comment_id to fetch
   * @return A Comment object
   * @throws InsufficientRightsException
   *           if the user does not have the moderate_comments cap
   * @throws ObjectNotFoundException
   *           if no comment with that comment_id exists
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public Comment getComment(final Integer comment_id)
      throws XmlRpcFault, InsufficientRightsException, ObjectNotFoundException {
    try {
      final XmlRpcStruct struct = this.wp.getComment(BLOGID, this.username,
          this.password, comment_id);
      final Comment comment = new Comment();
      comment.fromXmlRpcStruct(struct);
      return comment;
    } catch (final XmlRpcFault e) {
      final int err = e.getErrorCode();
      switch (err) {
        case 403:
          throw new InsufficientRightsException();
        case 404:
          throw new ObjectNotFoundException();
        default:
          throw e;
      }
    }
  }
  
  /**
   * @param status
   *          One of "approve", "hold", or "spam". Or, null to show all.
   * @param post_id
   *          Filter comments by post_id, or null to not filter.
   * @param number
   *          The number of comments to return, or null for the default (of 10)
   * @param offset
   *          The offset into the set of comments to return, or null for 0
   * @return A list of Comment objects
   * @throws InsufficientRightsException
   *           if the user does not have the moderate_comments cap
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  @SuppressWarnings({ "unchecked" })
  public List<Comment> getComments(final String status, final Integer post_id,
      final Integer number, final Integer offset)
          throws InsufficientRightsException, XmlRpcFault {
    try {
      final XmlRpcStruct filter = new XmlRpcStruct();
      if (status != null) {
        filter.put("status", status); //$NON-NLS-1$
      }
      if (post_id != null) {
        filter.put("post_id", post_id); //$NON-NLS-1$
      }
      if (number != null) {
        filter.put("number", number); //$NON-NLS-1$
      }
      if (offset != null) {
        filter.put("offset", offset); //$NON-NLS-1$
      }
      final XmlRpcArray r = this.wp.getComments(BLOGID, this.username,
          this.password, filter);
      return fillFromXmlRpcArray(r, Comment.class, new Comment());
    } catch (final XmlRpcFault e) {
      final int err = e.getErrorCode();
      switch (err) {
        case 401:
          throw new InsufficientRightsException();
        default:
          throw e;
      }
    }
  }
  
  /**
   * @param postId
   *          Blog Post ID
   * @return Number of comments (approved, spam, otherwise)
   * @throws XmlRpcFault
   *           if there is a generic error during request
   * @throws InsufficientRightsException
   *           if the user does not have the edit_posts cap
   */
  public CommentCount getCommentsCount(final Integer postId)
      throws XmlRpcFault, InsufficientRightsException {
    try {
      XmlRpcStruct struct;
      if (postId.intValue() != -1) {
        struct = this.wp.getCommentCount(BLOGID, this.username, this.password,
            postId);
      } else {
        struct = this.wp.getCommentCount(BLOGID, this.username, this.password);
      }
      final CommentCount cc = new CommentCount();
      cc.fromXmlRpcStruct(struct);
      return cc;
    } catch (final XmlRpcFault e) {
      final int err = e.getErrorCode();
      switch (err) {
        case 403:
          throw new InsufficientRightsException();
        default:
          throw e;
      }
    }
  }
  
  /**
   * @return the comment status list
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public CommentStatusList getCommentStatusList() throws XmlRpcFault {
    final XmlRpcStruct csl = this.wp.getCommentStatusList(BLOGID, this.username,
        this.password);
    final CommentStatusList result = new CommentStatusList();
    result.fromXmlRpcStruct(csl);
    return result;
  }
  
  /**
   * @param attachmentId
   *          the attachment id
   * @return the media item
   * @throws InsufficientRightsException
   *           if the user lacks the upload_files cap
   * @throws ObjectNotFoundException
   *           if no attachment with that attachment_id exists
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public MediaItem getMediaItem(final Integer attachmentId)
      throws InsufficientRightsException, ObjectNotFoundException, XmlRpcFault {
    try {
      final XmlRpcStruct r = this.wp.getMediaItem(BLOGID, this.username,
          this.password, attachmentId);
      final MediaItem result = new MediaItem();
      result.fromXmlRpcStruct(r);
      return result;
    } catch (final XmlRpcFault e) {
      final int err = e.getErrorCode();
      switch (err) {
        case 403: // TODO wrong return code again?
          throw new InsufficientRightsException();
        case 404:
          throw new ObjectNotFoundException(attachmentId.toString());
        default:
          throw e;
      }
    }
  }
  
  /**
   * @return the list of media items
   * @throws InsufficientRightsException
   *           if the user lacks the upload_files cap
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public List<MediaItem> getMediaLibrary()
      throws InsufficientRightsException, XmlRpcFault {
    return this.getMediaLibrary(null);
  }
  
  /**
   * @param filter
   *          the filter
   * @return the list of media items
   * @throws InsufficientRightsException
   *           if the user lacks the upload_files cap
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  @SuppressWarnings("unchecked")
  public List<MediaItem> getMediaLibrary(final FilterMediaItem filter)
      throws InsufficientRightsException, XmlRpcFault {
    try {
      XmlRpcArray r;
      if (filter != null) {
        final XmlRpcStruct filterXml = new XmlRpcStruct();
        if (filter.getMime_type() != null) {
          filterXml.put("mime_type", filter.getMime_type()); //$NON-NLS-1$
        }
        if (filter.getNumber() != null) {
          filterXml.put("number", filter.getNumber()); //$NON-NLS-1$
        }
        if (filter.getOffset() != null) {
          filterXml.put("offset", filter.getOffset()); //$NON-NLS-1$
        }
        if (filter.getParent_id() != null) {
          filterXml.put("parent_id", filter.getParent_id()); //$NON-NLS-1$
        }
        r = this.wp.getMediaLibrary(BLOGID, this.username, this.password,
            filterXml);
      } else {
        r = this.wp.getMediaLibrary(BLOGID, this.username, this.password);
      }
      return fillFromXmlRpcArray(r, MediaItem.class, new MediaItem());
    } catch (final XmlRpcFault e) {
      final int err = e.getErrorCode();
      switch (err) {
        case 401:
          throw new InsufficientRightsException();
        default:
          throw e;
      }
    }
  }
  
  /**
   * @param optionName
   *          option name to get
   * @return the option
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public Option getOption(final String optionName) throws XmlRpcFault {
    final List<Option> result = this.getOptions(optionName, null);
    return result == null || result.size() == 0 ? null : result.get(0);
  }
  
  /**
   * @return the list of options
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public List<Option> getOptions() throws XmlRpcFault {
    return this.getOptions(null, null);
  }
  
  /**
   * @param optionNames
   *          options to retrieve
   * @return the options
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  @SuppressWarnings({ "unchecked" })
  public List<Option> getOptions(final String... optionNames)
      throws XmlRpcFault {
    XmlRpcArray options = null;
    if (optionNames[0] != null) {
      options = new XmlRpcArray();
      for (final String s : optionNames) {
        if (s != null) {
          options.add(s);
        }
      }
    }
    final XmlRpcStruct r = options != null
        ? this.wp.getOptions(BLOGID, this.username, this.password, options)
        : this.wp.getOptions(BLOGID, this.username, this.password);
    return structToOptions(r);
  }
  
  /**
   * @param url
   *          Url of the page queried
   * @return List of URLs
   * @throws XmlRpcFault
   *           Generic exception for xml-rpc operations
   */
  public List<URL> getPingbacks(final String url) throws XmlRpcFault {
    List<URL> result = null;
    final XmlRpcArray r = this.pingbackExt.getPingbacks(url);
    result = new ArrayList<>();
    for (final Object rec : r) {
      try {
        result.add(new URL((String) rec));
      } catch (final MalformedURLException e) {
        logger.error("malformed url for pingback: {}", //$NON-NLS-1$
            rec);
      }
    }
    return result;
  }
  
  /**
   * @param postId
   *          post id to retrieve
   * @return the post
   * @throws InsufficientRightsException
   *           if user does not have permission to edit the post
   * @throws ObjectNotFoundException
   *           if no post with that postId exists
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public Post getPost(final Integer postId)
      throws InsufficientRightsException, ObjectNotFoundException, XmlRpcFault {
    try {
      final XmlRpcStruct r = this.wp.getPost(BLOGID, this.username,
          this.password, postId);
      final Post result = new Post();
      result.fromXmlRpcStruct(r);
      return result;
    } catch (final XmlRpcFault e) {
      final int err = e.getErrorCode();
      switch (err) {
        case 401:
          throw new InsufficientRightsException();
        case 404:
          throw new ObjectNotFoundException(postId.toString());
        default:
          throw e;
      }
    }
  }
  
  /**
   * @return the list of post formats
   * @throws InsufficientRightsException
   *           if the user does not have the edit_posts cap
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public Map<String, String> getPostFormats()
      throws InsufficientRightsException, XmlRpcFault {
    return this.getPostFormats(false);
  }
  
  /**
   * @param showSupported
   *          true if only supported types are to be listed
   * @return the list of post formats
   * @throws InsufficientRightsException
   *           if the user does not have the edit_posts cap
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  @SuppressWarnings("unchecked")
  public Map<String, String> getPostFormats(final boolean showSupported)
      throws InsufficientRightsException, XmlRpcFault {
    XmlRpcStruct r;
    List<String> filtered = null;
    try {
      if (!showSupported) {
        r = this.wp.getPostFormats(BLOGID, this.username, this.password);
      } else {
        final XmlRpcArray filter = new XmlRpcArray();
        final XmlRpcStruct ss = new XmlRpcStruct();
        ss.put("show-supported", "true"); //$NON-NLS-1$ //$NON-NLS-2$
        filter.add(ss);
        r = this.wp.getPostFormats(BLOGID, this.username, this.password, ss);
        final XmlRpcArray f = r.getArray("supported"); //$NON-NLS-1$
        filtered = new ArrayList<>();
        for (final Object fItem : f) {
          filtered.add((String) fItem);
        }
        r = r.getStruct("all"); //$NON-NLS-1$
      }
      if (r != null) {
        final Map<String, String> result = new HashMap<>();
        for (final Object s : r.keySet()) {
          if (filtered == null) {
            result.put((String) s, (String) r.get(s));
          } else {
            if (filtered.contains(s)) {
              result.put((String) s, (String) r.get(s));
            }
          }
        }
        return result;
      }
      return null;
    } catch (final XmlRpcFault e) {
      final int err = e.getErrorCode();
      switch (err) {
        case 403:
          throw new InsufficientRightsException();
        default:
          throw e;
      }
    }
  }
  
  /**
   * @return the list of posts
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public List<Post> getPosts() throws XmlRpcFault {
    return this.getPosts(null);
  }
  
  /**
   * @param filter
   *          filter for resulting posts
   * @return the list of posts
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public List<Post> getPosts(final FilterPost filter) throws XmlRpcFault {
    XmlRpcArray r = null;
    if (filter == null) {
      r = this.wp.getPosts(BLOGID, this.username, this.password);
    } else {
      r = this.wp.getPosts(BLOGID, this.username, this.password,
          filter.toXmlRpcStruct());
    }
    return fillFromXmlRpcArray(r, Post.class, new Post());
  }
  
  /**
   * @return the post statuses
   * @throws InsufficientRightsException
   *           if the user does not have the edit_posts cap
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public Map<String, String> getPostStatusList()
      throws InsufficientRightsException, XmlRpcFault {
    try {
      final XmlRpcStruct r = this.wp.getPostStatusList(BLOGID, this.username,
          this.password);
      if (r != null) {
        final Map<String, String> result = new HashMap<>();
        for (final Object s : r.keySet()) {
          result.put((String) s, (String) r.get(s));
        }
        return result;
      }
      return null;
    } catch (final XmlRpcFault e) {
      final int err = e.getErrorCode();
      switch (err) {
        case 403:
          throw new InsufficientRightsException();
        default:
          throw e;
      }
    }
  }
  
  /**
   * @param postTypeName
   *          name of the post type
   * @return the post type
   * @throws InsufficientRightsException
   *           if the user does not have the edit_posts cap for this post type
   * @throws InvalidArgumentsException
   *           if invalid post type name is specified
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public PostType getPostType(final String postTypeName)
      throws InsufficientRightsException, InvalidArgumentsException,
      XmlRpcFault {
    try {
      final XmlRpcStruct r = this.wp.getPostType(BLOGID, this.username,
          this.password, postTypeName);
      final PostType result = new PostType();
      result.fromXmlRpcStruct(r);
      return result;
    } catch (final XmlRpcFault e) {
      final int err = e.getErrorCode();
      switch (err) {
        case 401:
          throw new InsufficientRightsException();
        case 403:
          throw new InvalidArgumentsException();
        default:
          throw e;
      }
      
    }
  }
  
  /**
   * @return list of post types
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public List<PostType> getPostTypes() throws XmlRpcFault {
    return this.getPostTypes(null);
  }
  
  /**
   * @param filter
   *          filter for results, @see <a href=
   *          "https://codex.wordpress.org/Function_Reference/get_post_types">
   *          Function Reference/get post types</a>
   * @return list of post types
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  @SuppressWarnings("unchecked")
  public List<PostType> getPostTypes(final Map<String, Object> filter)
      throws XmlRpcFault {
    XmlRpcStruct r;
    if (filter == null) {
      r = this.wp.getPostTypes(BLOGID, this.username, this.password);
    } else {
      final XmlRpcStruct postTypeFilter = new XmlRpcStruct();
      for (final String k : filter.keySet()) {
        final Object v = filter.get(k);
        if (v != null) {
          postTypeFilter.put(k, v);
        }
      }
      r = this.wp.getPostTypes(BLOGID, this.username, this.password,
          postTypeFilter);
    }
    final List<PostType> result = new ArrayList<>();
    for (final Object k : r.keySet()) {
      final Object value = r.get(k);
      final PostType pt = new PostType();
      pt.fromXmlRpcStruct((XmlRpcStruct) value);
      result.add(pt);
    }
    return result;
  }
  
  /**
   * @return the user profile of the current user
   * @throws InsufficientRightsException
   *           if the user does not permission to edit his/her profile.
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public User getProfile() throws InsufficientRightsException, XmlRpcFault {
    try {
      final XmlRpcStruct r = this.wp.getProfile(BLOGID, this.username,
          this.password);
      final User user = new User();
      user.fromXmlRpcStruct(r);
      return user;
    } catch (final XmlRpcFault e) {
      final int err = e.getErrorCode();
      switch (err) {
        case 401:
          throw new InsufficientRightsException();
        default:
          throw e;
      }
    }
  }
  
  /**
   * @return the taxonomies
   * @throws XmlRpcFault
   *           when there is an error with the request
   */
  public List<Taxonomy> getTaxonomies() throws XmlRpcFault {
    final XmlRpcArray r = this.wp.getTaxonomies(BLOGID, this.username,
        this.password);
    return fillFromXmlRpcArray(r, Taxonomy.class, new Taxonomy());
  }
  
  /**
   * @param taxonomy
   *          taxonomy to get
   * @return the taxonomy
   * @throws InsufficientRightsException
   *           if the user does not have the assign_terms cap for this taxonomy
   * @throws InvalidArgumentsException
   *           if invalid taxonomy name is specified
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public Taxonomy getTaxonomy(final String taxonomy)
      throws InsufficientRightsException, InvalidArgumentsException,
      XmlRpcFault {
    try {
      final XmlRpcStruct r = this.wp.getTaxonomy(BLOGID, this.username,
          this.password, taxonomy);
      final Taxonomy t = new Taxonomy();
      t.fromXmlRpcStruct(r);
      return t;
    } catch (final XmlRpcFault e) {
      final int err = e.getErrorCode();
      switch (err) {
        case 401:
          throw new InsufficientRightsException();
        case 403:
          throw new InvalidArgumentsException(taxonomy);
        default:
          throw e;
      }
    }
  }
  
  /**
   * @param taxonomy
   *          taxonomy name
   * @param termId
   *          term id
   * @return the term
   * @throws InsufficientRightsException
   *           if the user does not have the assign_terms cap for this taxonomy
   * @throws InvalidArgumentsException
   *           if invalid taxonomy name is specified
   * @throws ObjectNotFoundException
   *           if the term id is not found
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public Term getTerm(final String taxonomy, final Integer termId)
      throws InsufficientRightsException, InvalidArgumentsException,
      ObjectNotFoundException, XmlRpcFault {
    try {
      final XmlRpcStruct r = this.wp.getTerm(BLOGID, this.username,
          this.password, taxonomy, termId);
      final Term t = new Term();
      t.fromXmlRpcStruct(r);
      return t;
    } catch (final XmlRpcFault e) {
      final int err = e.getErrorCode();
      switch (err) {
        case 401:
          throw new InsufficientRightsException();
        case 403:
          throw new InvalidArgumentsException(taxonomy);
        case 404:
          throw new ObjectNotFoundException(termId.toString());
        default:
          throw e;
      }
    }
  }
  
  /**
   * @param taxonomy
   *          taxonomy name
   * @return the list of terms
   * @throws InsufficientRightsException
   *           if the user does not have the assign_terms cap for this taxonomy
   * @throws InvalidArgumentsException
   *           if invalid taxonomy name is specified
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public List<Term> getTerms(final String taxonomy)
      throws InsufficientRightsException, InvalidArgumentsException,
      XmlRpcFault {
    return this.getTerms(taxonomy, null);
  }
  
  /**
   * @param taxonomy
   *          taxonomy name
   * @param filter
   *          term filter
   * @return the list of terms
   * @throws InsufficientRightsException
   *           if the user does not have the assign_terms cap for this taxonomy
   * @throws InvalidArgumentsException
   *           if invalid taxonomy name is specified
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public List<Term> getTerms(final String taxonomy, final TermFilter filter)
      throws InsufficientRightsException, InvalidArgumentsException,
      XmlRpcFault {
    try {
      final XmlRpcArray r = filter != null
          ? this.wp.getTerms(BLOGID, this.username, this.password, taxonomy,
              filter.toXmlRpcStruct())
          : this.wp.getTerms(BLOGID, this.username, this.password, taxonomy);
      return fillFromXmlRpcArray(r, Term.class, new Term());
    } catch (final XmlRpcFault e) {
      final int err = e.getErrorCode();
      switch (err) {
        case 401:
          throw new InsufficientRightsException();
        case 403:
          throw new InvalidArgumentsException(taxonomy);
        default:
          throw e;
      }
    }
  }
  
  /**
   * @param userId
   *          user id to get info
   * @return the user object
   * @throws InsufficientRightsException
   *           if user does not have permission to edit the user
   * @throws ObjectNotFoundException
   *           if no user with that userId exists
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public User getUser(final Integer userId)
      throws InsufficientRightsException, ObjectNotFoundException, XmlRpcFault {
    try {
      final XmlRpcStruct r = this.wp.getUser(BLOGID, this.username,
          this.password, userId);
      final User user = new User();
      user.fromXmlRpcStruct(r);
      return user;
    } catch (final XmlRpcFault e) {
      final int err = e.getErrorCode();
      switch (err) {
        case 401:
          throw new InsufficientRightsException();
        case 404:
          throw new ObjectNotFoundException(userId.toString());
        default:
          throw e;
      }
    }
  }
  
  /**
   * @return the list users
   * @throws InsufficientRightsException
   *           if the user does not have the list_users cap
   * @throws InvalidArgumentsException
   *           if invalid role is specified
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public List<User> getUsers() throws InsufficientRightsException,
      InvalidArgumentsException, XmlRpcFault {
    return this.getUsers(null);
  }
  
  /**
   * @param filter
   *          filter for limiting the result
   * @return the list of users
   * @throws InsufficientRightsException
   *           if the user does not have the list_users cap
   * @throws InvalidArgumentsException
   *           if invalid role is specified
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public List<User> getUsers(final FilterUser filter)
      throws InsufficientRightsException, InvalidArgumentsException,
      XmlRpcFault {
    try {
      XmlRpcArray r;
      if (filter == null) {
        r = this.wp.getUsers(BLOGID, this.username, this.password);
      } else {
        final XmlRpcStruct f = filter.buildWithNonNullValues();
        r = this.wp.getUsers(BLOGID, this.username, this.password, f);
      }
      return fillFromXmlRpcArray(r, User.class, new User());
    } catch (final XmlRpcFault e) {
      final int err = e.getErrorCode();
      switch (err) {
        case 401:
          throw new InsufficientRightsException();
        case 403:
          if (filter != null) {
            throw new InvalidArgumentsException(filter.getRole());
          }
          throw new InvalidArgumentsException();
        default:
          throw e;
      }
    }
  }
  
  /**
   * @return the list of blogs user is registered to
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public List<UserBlog> getUsersBlogs() throws XmlRpcFault {
    final XmlRpcArray r = this.wp.getUsersBlogs(this.username, this.password);
    return fillFromXmlRpcArray(r, UserBlog.class, new UserBlog());
  }
  
  @SuppressWarnings("nls")
  private void initMetaWebLog() throws MalformedURLException {
    final URL url = new URL(this.xmlRpcUrl);
    this.wp = (WordpressBridge) XmlRpcProxy.createProxy(url, "wp",
        new Class[] { WordpressBridge.class }, true);
    this.pingback = (PingbackBridge) XmlRpcProxy.createProxy(url, "pingback",
        new Class[] { PingbackBridge.class }, true);
    this.pingbackExt = (PingbackExtensionsBridge) XmlRpcProxy.createProxy(url,
        "pingback.extensions", new Class[] { PingbackExtensionsBridge.class },
        true);
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
   * @throws InsufficientRightsException
   *           if anonymous comments are disallowed and invalid credentials are
   *           supplied, or if comment does not follow required comment fields
   *           configuration.
   * @throws ObjectNotFoundException
   *           if no post with that post_id exists
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  @SuppressWarnings({ "unchecked", "nls" })
  public Integer newComment(final Integer post_id, final Integer comment_parent,
      final String content, final String author, final String author_url,
      final String author_email) throws InsufficientRightsException,
          ObjectNotFoundException, XmlRpcFault {
    try {
      final XmlRpcStruct comment = new XmlRpcStruct();
      if (comment_parent != null) {
        comment.put("comment_parent", comment_parent);
      }
      comment.put("content", content);
      if (author != null) {
        comment.put("author", author);
      }
      if (author_url != null) {
        comment.put("author_url", author_url);
      }
      if (author_email != null) {
        comment.put("author_email", author_email);
      }
      final Integer comment_id = this.wp.newComment(BLOGID, this.username,
          this.password, post_id, comment);
      return comment_id;
    } catch (final XmlRpcFault e) {
      final int err = e.getErrorCode();
      switch (err) {
        case 403:
          throw new InsufficientRightsException();
        case 404:
          throw new ObjectNotFoundException();
        default:
          throw e;
      }
    }
  }
  
  /**
   * @param post
   *          new post contents
   * @return the post id
   * @throws InsufficientRightsException
   *           one of the following reasons:
   *           <ul>
   *           <li>if the user does not have the edit_posts cap for this post
   *           type</li>
   *           <li>if user does not have permission to create post of the
   *           specified post_status</li>
   *           <li>if post_author is different than the user's ID and the user
   *           does not have the edit_others_posts cap for this post type</li>
   *           <li>if sticky is passed and user does not have permission to make
   *           the post sticky, regardless if sticky is set to 0, 1, false or
   *           true</li>
   *           <li>if a taxonomy in terms or terms_names is not supported by
   *           this post type</li>
   *           <li>if terms or terms_names is set but user does not have
   *           assign_terms cap</li>
   *           <li>if an ambiguous term name is used in terms_names</li>
   *           </ul>
   * @throws InvalidArgumentsException
   *           if invalid post_type is specified or if an invalid term ID is
   *           specified in terms
   * @throws ObjectNotFoundException
   *           if no author with that post_author ID exists or if no attachment
   *           with that post_thumbnail ID exists
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  @SuppressWarnings("unchecked")
  public Integer newPost(final Post post) throws InsufficientRightsException,
      InvalidArgumentsException, ObjectNotFoundException, XmlRpcFault {
    try {
      final List<Term> oldTerms = post.getTerms();
      final XmlRpcStruct postX = post.toXmlRpcStruct();
      if (oldTerms != null) {
        final XmlRpcStruct newTerms = new XmlRpcStruct();
        for (final Term term : post.getTerms()) {
          final XmlRpcArray ts = (XmlRpcArray) newTerms.get(term.getTaxonomy());
          if (ts == null) {
            final XmlRpcArray tXs = new XmlRpcArray();
            tXs.add(term.getTerm_id());
            newTerms.put(term.getTaxonomy(), tXs);
          } else {
            ts.add(term.getTerm_id());
          }
        }
        postX.put("terms", newTerms); //$NON-NLS-1$
      }
      return Integer.valueOf(
          this.wp.newPost(BLOGID, this.username, this.password, postX));
    } catch (final XmlRpcFault e) {
      final int err = e.getErrorCode();
      switch (err) {
        case 401:
          throw new InsufficientRightsException();
        case 403:
          throw new InvalidArgumentsException();
        case 404:
          throw new ObjectNotFoundException();
        default:
          throw e;
      }
    }
  }
  
  /**
   * @param term
   *          new term
   * @return the term id
   * @throws InsufficientRightsException
   *           if the user does not have the assign_terms cap for this taxonomy
   * @throws InvalidArgumentsException
   *           if invalid taxonomy name is specified
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public Integer newTerm(final Term term) throws InsufficientRightsException,
      InvalidArgumentsException, XmlRpcFault {
    try {
      final String r = this.wp.newTerm(BLOGID, this.username, this.password,
          term.toXmlRpcStruct());
      return Integer.valueOf(r);
    } catch (final XmlRpcFault e) {
      final int err = e.getErrorCode();
      switch (err) {
        case 401:
          throw new InsufficientRightsException();
        case 403:
          throw new InvalidArgumentsException(term.getTaxonomy());
        default:
          throw e;
      }
    }
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
  public String ping(final String pagelinkedfrom, final String pagelinkedto)
      throws XmlRpcFault {
    return this.pingback.ping(pagelinkedfrom, pagelinkedto);
  }
  
  /**
   * @param option
   *          option to set
   * @return modified option
   * @throws InsufficientRightsException
   *           if the user does not have the manage_options cap
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  public Option setOption(final Option option)
      throws InsufficientRightsException, XmlRpcFault {
    final List<Option> r = this.setOptions(option);
    return r == null || r.size() == 0 ? null : r.get(0);
  }
  
  /**
   * @param options
   *          options to set
   * @return modified options
   * @throws InsufficientRightsException
   *           if the user does not have the manage_options cap
   * @throws XmlRpcFault
   *           if there is a generic error during request
   */
  @SuppressWarnings({ "unchecked" })
  public List<Option> setOptions(final Option... options)
      throws InsufficientRightsException, XmlRpcFault {
    try {
      final XmlRpcStruct opts = new XmlRpcStruct();
      for (final Option o : options) {
        if (o != null) {
          opts.put(o.getName(), o.getValue());
        }
      }
      final XmlRpcArray optsArr = new XmlRpcArray();
      optsArr.add(opts);
      final XmlRpcStruct r = this.wp.setOptions(BLOGID, this.username,
          this.password, opts);
      return structToOptions(r);
    } catch (final XmlRpcFault e) {
      final int err = e.getErrorCode();
      switch (err) {
        case 403: // TODO codex documentation is inconsistent, so check to
                  // ensure if it's really 401
          throw new InsufficientRightsException();
        default:
          throw e;
      }
    }
  }
  
  /**
   * @param media
   *          file data
   * @param fileName
   *          file name
   * @return the result of the operation
   * @throws InsufficientRightsException
   *           if the user does not have the upload_files cap
   * @throws FileUploadException
   *           if the file cannot be uploaded
   * @throws XmlRpcFault
   *           if there is a generic error during request
   * @throws IOException
   *           if there is an I/O problem processing the file data on a
   *           temporary file
   */
  public MediaItemUploadResult uploadFile(final InputStream media,
      final String fileName) throws InsufficientRightsException,
          FileUploadException, XmlRpcFault, IOException {
    return this.uploadFile(media, fileName, null);
  }
  
  /**
   * @param media
   *          file data
   * @param fileName
   *          file name
   * @param overwrite
   *          to overwrite an existing file
   * @return the result of the operation
   * @throws InsufficientRightsException
   *           if the user does not have the upload_files cap
   * @throws FileUploadException
   *           if the file cannot be uploaded
   * @throws XmlRpcFault
   *           if there is a generic error during request
   * @throws IOException
   *           if there is an I/O problem processing the file data on a
   *           temporary file
   */
  public MediaItemUploadResult uploadFile(final InputStream media,
      final String fileName, final Boolean overwrite)
          throws InsufficientRightsException, FileUploadException, XmlRpcFault,
          IOException {
    return this.uploadFile(media, fileName, overwrite, null);
  }
  
  /**
   * @param media
   *          file data
   * @param fileName
   *          file name
   * @param overwrite
   *          to overwrite an existing file
   * @param postId
   *          post id
   * @return the result of the operation
   * @throws InsufficientRightsException
   *           if the user does not have the upload_files cap
   * @throws FileUploadException
   *           if the file cannot be uploaded
   * @throws XmlRpcFault
   *           if there is a generic error during request
   * @throws IOException
   *           if there is an I/O problem processing the file data on a
   *           temporary file
   */
  @SuppressWarnings("unchecked")
  public MediaItemUploadResult uploadFile(final InputStream media,
      final String fileName, final Boolean overwrite, final Integer postId)
          throws InsufficientRightsException, FileUploadException, XmlRpcFault,
          IOException {
    try {
      final XmlRpcStruct data = new XmlRpcStruct();
      final File tempFile = File.createTempFile("mediauploadwpj", null); //$NON-NLS-1$
      tempFile.deleteOnExit();
      Files.copy(media, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
      final String mimeType = new MimetypesFileTypeMap()
          .getContentType(tempFile);
      final ByteArrayOutputStream out = new ByteArrayOutputStream();
      final byte[] buffer = new byte[1024];
      try (InputStream mediaTemp = new FileInputStream(tempFile)) {
        int len = mediaTemp.read(buffer);
        while (len != -1) {
          out.write(buffer, 0, len);
          len = mediaTemp.read(buffer);
        }
      }
      tempFile.delete();
      data.put("name", fileName); //$NON-NLS-1$
      data.put("type", mimeType); //$NON-NLS-1$
      data.put("bits", out.toByteArray()); //$NON-NLS-1$
      if (postId != null) {
        data.put("post_id", postId); //$NON-NLS-1$
      }
      if (overwrite != null) {
        data.put("overwrite", overwrite); //$NON-NLS-1$
      }
      final XmlRpcStruct r = this.wp.uploadFile(BLOGID, this.username,
          this.password, data);
      final MediaItemUploadResult result = new MediaItemUploadResult();
      result.fromXmlRpcStruct(r);
      return result;
    } catch (final XmlRpcFault e) {
      final int err = e.getErrorCode();
      switch (err) {
        case 401:
          throw new InsufficientRightsException();
        case 500:
          throw new FileUploadException();
        default:
          throw e;
      }
    }
  }
}
