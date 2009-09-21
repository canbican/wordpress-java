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
package net.bican.wordpress.example;

import java.net.MalformedURLException;
import java.util.List;

import redstone.xmlrpc.XmlRpcFault;

import net.bican.wordpress.Page;
import net.bican.wordpress.PageDefinition;
import net.bican.wordpress.Wordpress;

/**
 * 
 * Example implementation
 * 
 * Run this as java net.bican.wordpress.example.Main &lt;username&gt;
 * &lt;password&gt; &lt;xmlrpc-url&gt; for your blog.
 * 
 * @author Can Bican
 * 
 */
public class Main {

  /**
   * @param args
   * @throws MalformedURLException
   * @throws XmlRpcFault
   */
  public static void main(String[] args) throws MalformedURLException,
      XmlRpcFault {
    String username = args[0];
    String password = args[1];
    String xmlRpcUrl = args[2];
    Wordpress wp = new Wordpress(username, password, xmlRpcUrl);
    List<Page> recentPosts = wp.getRecentPosts(10);
    System.out.println("Here are the ten recent posts:");
    for (Page page : recentPosts) {
      System.out.println(page.getPostid() + ":" + page.getTitle());
    }
    List<PageDefinition> pages = wp.getPageList();
    System.out.println("Here are the pages:");
    for (PageDefinition pageDefinition : pages) {
      System.out.println(pageDefinition.getPage_title());
    }
    System.out.println("Posting a test (draft) page from a previous page...");
    Page recentPost = wp.getRecentPosts(1).get(0);
    recentPost.setTitle("Test Page");
    recentPost.setDescription("Test description");
    String result = wp.newPost(recentPost, "draft");
    System.out.println("new post page id: " + result);
    System.out.println("\nThat's all for now.");
  }

}
