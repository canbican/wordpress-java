/*
 * 
 * Wordpress-java
 * https://github.com/canbican/wordpress-java/
 * 
 * Copyright 2012-2015 Can Bican <can@bican.net>
 * See the file 'COPYING' in the distribution for licensing terms.
 * 
 */
package net.bican.wordpress.example;

import java.net.MalformedURLException;
import java.util.List;

import redstone.xmlrpc.XmlRpcFault;
import net.bican.wordpress.FilterPost;
import net.bican.wordpress.Post;
import net.bican.wordpress.Wordpress;
import net.bican.wordpress.exceptions.InsufficientRightsException;
import net.bican.wordpress.exceptions.InvalidArgumentsException;
import net.bican.wordpress.exceptions.ObjectNotFoundException;

/**
 * Example implementation Run this as java net.bican.wordpress.example.Main
 * &lt;username&gt; &lt;password&gt; &lt;xmlrpc-url&gt; for your blog.
 * 
 * @author Can Bican
 */
public class Main {
  
  @SuppressWarnings({ "nls", "javadoc", "boxing" })
  public static void main(String[] args)
      throws MalformedURLException, XmlRpcFault, InsufficientRightsException,
      InvalidArgumentsException, ObjectNotFoundException {
    Wordpress wp;
    if (args.length == 3) {
      String username = args[0];
      String password = args[1];
      String xmlRpcUrl = args[2];
      wp = new Wordpress(username, password, xmlRpcUrl);
    } else {
      wp = new Wordpress("admin", "admin",
          "http://wordpressjava-test.local/xmlrpc.php");
    }
    FilterPost filter = new FilterPost();
    filter.setNumber(10);
    List<Post> recentPosts = wp.getPosts(filter);
    System.out.println("Here are the ten recent posts:");
    for (Post page : recentPosts) {
      System.out.println(page.getPost_id() + ":" + page.getPost_title());
    }
    FilterPost filter2 = new FilterPost();
    filter2.setPost_type("page");
    wp.getPosts(filter2);
    List<Post> pages = wp.getPosts(filter2);
    System.out.println("Here are the pages:");
    for (Post pageDefinition : pages) {
      System.out.println(pageDefinition.getPost_title());
    }
    System.out.println("Posting a test (draft) page...");
    Post recentPost = new Post();
    recentPost.setPost_title("Test Page");
    recentPost.setPost_content("Test description");
    recentPost.setPost_status("draft");
    Integer result = wp.newPost(recentPost);
    System.out.println("new post page id: " + result);
    System.out.println("\nThat's all for now.");
  }
  
}
