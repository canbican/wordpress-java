/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress.example;

import java.net.MalformedURLException;
import java.util.List;

import net.bican.wordpress.FilterPost;
import net.bican.wordpress.Post;
import net.bican.wordpress.Wordpress;
import net.bican.wordpress.exceptions.InsufficientRightsException;
import net.bican.wordpress.exceptions.InvalidArgumentsException;
import net.bican.wordpress.exceptions.ObjectNotFoundException;
import redstone.xmlrpc.XmlRpcFault;

/**
 * Example implementation Run this as java net.bican.wordpress.example.Main
 * &lt;username&gt; &lt;password&gt; &lt;xmlrpc-url&gt; for your blog.
 *
 * @author Can Bican
 */
public class Main {
  
  @SuppressWarnings({ "nls", "javadoc", "boxing" })
  public static void main(final String[] args)
      throws MalformedURLException, XmlRpcFault, InsufficientRightsException,
      InvalidArgumentsException, ObjectNotFoundException {
    Wordpress wp;
    if (args.length == 3) {
      final String username = args[0];
      final String password = args[1];
      final String xmlRpcUrl = args[2];
      wp = new Wordpress(username, password, xmlRpcUrl);
    } else {
      wp = new Wordpress("admin", "admin",
          "http://wordpressjava-test.local/xmlrpc.php");
    }
    final FilterPost filter = new FilterPost();
    filter.setNumber(10);
    final List<Post> recentPosts = wp.getPosts(filter);
    System.out.println("Here are the ten recent posts:");
    for (final Post page : recentPosts) {
      System.out.println(page.getPost_id() + ":" + page.getPost_title());
    }
    final FilterPost filter2 = new FilterPost();
    filter2.setPost_type("page");
    wp.getPosts(filter2);
    final List<Post> pages = wp.getPosts(filter2);
    System.out.println("Here are the pages:");
    for (final Post pageDefinition : pages) {
      System.out.println(pageDefinition.getPost_title());
    }
    System.out.println("Posting a test (draft) page...");
    final Post recentPost = new Post();
    recentPost.setPost_title("Test Page");
    recentPost.setPost_content("Test description");
    recentPost.setPost_status("draft");
    final Integer result = wp.newPost(recentPost);
    System.out.println("new post page id: " + result);
    System.out.println("\nThat's all for now.");
  }
  
}
