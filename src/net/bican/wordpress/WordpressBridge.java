/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress;

import redstone.xmlrpc.XmlRpcArray;
import redstone.xmlrpc.XmlRpcFault;
import redstone.xmlrpc.XmlRpcStruct;

interface WordpressBridge {
  Boolean deleteComment(Integer blogid, String username, String password,
      Integer comment_id) throws XmlRpcFault;
      
  Boolean deletePost(Integer blogid, String username, String password,
      Integer post_id) throws XmlRpcFault;
      
  Boolean deleteTerm(Integer blogid, String username, String password,
      String taxonomy, Integer term_id) throws XmlRpcFault;
      
  Boolean editComment(Integer blogid, String username, String password,
      Integer comment_id, Comment comment) throws XmlRpcFault;
      
  Boolean editPost(Integer blogid, String username, String password,
      Integer post_id, XmlRpcStruct content) throws XmlRpcFault;
      
  Boolean editProfile(Integer blogid, String username, String password,
      XmlRpcStruct content) throws XmlRpcFault;
      
  Boolean editTerm(Integer blogid, String username, String password,
      Integer term_id, XmlRpcStruct content) throws XmlRpcFault;
      
  XmlRpcArray getAuthors(Integer blogid, String username, String password)
      throws XmlRpcFault;
      
  XmlRpcStruct getComment(Integer blogid, String username, String password,
      Integer comment_id) throws XmlRpcFault;
      
  XmlRpcStruct getCommentCount(Integer blogid, String username, String password)
      throws XmlRpcFault;
      
  XmlRpcStruct getCommentCount(Integer blogid, String username, String password,
      Integer post_ID) throws XmlRpcFault;
      
  XmlRpcArray getComments(Integer blogid, String username, String password,
      XmlRpcStruct filter) throws XmlRpcFault;
      
  XmlRpcStruct getCommentStatusList(Integer blogid, String username,
      String password) throws XmlRpcFault;
      
  XmlRpcStruct getMediaItem(Integer blogid, String username, String password,
      Integer attachment_id) throws XmlRpcFault;
      
  XmlRpcArray getMediaLibrary(Integer blogid, String username, String password)
      throws XmlRpcFault;
      
  XmlRpcArray getMediaLibrary(Integer blogid, String username, String password,
      XmlRpcStruct filter) throws XmlRpcFault;
      
  XmlRpcStruct getOptions(Integer blogid, String username, String password)
      throws XmlRpcFault;
      
  XmlRpcStruct getOptions(Integer blogid, String username, String password,
      XmlRpcArray options) throws XmlRpcFault;
      
  // "fields" field omitted for simplicity
  XmlRpcStruct getPost(Integer blogid, String username, String password,
      Integer post_id) throws XmlRpcFault;
      
  XmlRpcStruct getPostFormats(Integer blogid, String username, String password)
      throws XmlRpcFault;
      
  XmlRpcStruct getPostFormats(Integer blogid, String username, String password,
      XmlRpcStruct filter) throws XmlRpcFault;
      
  XmlRpcArray getPosts(Integer blogid, String username, String password)
      throws XmlRpcFault;
      
  XmlRpcArray getPosts(Integer blogid, String username, String password,
      XmlRpcStruct filter) throws XmlRpcFault;
      
  XmlRpcStruct getPostStatusList(Integer blogid, String username,
      String password) throws XmlRpcFault;
      
  // "fields" field omitted for simplicity
  XmlRpcStruct getPostType(Integer blogid, String username, String password,
      String post_type_name) throws XmlRpcFault;
      
  XmlRpcStruct getPostTypes(Integer blogid, String username, String password)
      throws XmlRpcFault;
      
  XmlRpcStruct getPostTypes(Integer blogid, String username, String password,
      XmlRpcStruct filter) throws XmlRpcFault;
      
  // "fields" field omitted for simplicity
  XmlRpcStruct getProfile(Integer blogid, String username, String password)
      throws XmlRpcFault;
      
  XmlRpcArray getTaxonomies(Integer blogid, String username, String password)
      throws XmlRpcFault;
      
  XmlRpcStruct getTaxonomy(Integer blogid, String username, String password,
      String taxonomy) throws XmlRpcFault;
      
  XmlRpcStruct getTerm(Integer blogid, String username, String password,
      String taxonomy, Integer term_id) throws XmlRpcFault;
      
  XmlRpcArray getTerms(Integer blogid, String username, String password,
      String taxonomy) throws XmlRpcFault;
      
  XmlRpcArray getTerms(Integer blogid, String username, String password,
      String taxonomy, XmlRpcStruct filter) throws XmlRpcFault;
      
  // "fields" field omitted for simplicity
  XmlRpcStruct getUser(Integer blogid, String username, String password,
      Integer user_id) throws XmlRpcFault;
      
  XmlRpcArray getUsers(Integer blogid, String username, String password)
      throws XmlRpcFault;
      
  XmlRpcArray getUsers(Integer blogid, String username, String password,
      XmlRpcStruct filter) throws XmlRpcFault;
      
  XmlRpcArray getUsersBlogs(String username, String password)
      throws XmlRpcFault;
      
  Integer newComment(Integer blogid, String username, String password,
      Integer post_id, XmlRpcStruct comment) throws XmlRpcFault;
      
  String newPost(Integer blogid, String username, String password,
      XmlRpcStruct content) throws XmlRpcFault;
      
  String newTerm(Integer blogid, String username, String password,
      XmlRpcStruct content) throws XmlRpcFault;
      
  XmlRpcStruct setOptions(Integer blogid, String username, String password,
      XmlRpcStruct options) throws XmlRpcFault;
      
  XmlRpcStruct uploadFile(Integer blogid, String username, String password,
      XmlRpcStruct data) throws XmlRpcFault;
}
