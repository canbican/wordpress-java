package net.bican.wordpress.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import net.bican.wordpress.FilterPost;
import net.bican.wordpress.Post;
import net.bican.wordpress.exceptions.InsufficientRightsException;
import net.bican.wordpress.exceptions.InvalidArgumentsException;
import net.bican.wordpress.exceptions.ObjectNotFoundException;
import redstone.xmlrpc.XmlRpcFault;

@SuppressWarnings({"static-method", "javadoc", "nls"})
public class PostFilterTest extends AbstractWordpressTest {
  @Test
  public void testPostPagination() throws InsufficientRightsException, InvalidArgumentsException,
      ObjectNotFoundException, XmlRpcFault {
    Post post1 = create1Post();
    Post post2 = create1Post();
    Post post3 = create1Post();
    Post post4 = create1Post();
    Post post5 = create1Post();
    FilterPost filter = new FilterPost();
    filter.setNumber(Integer.valueOf(2));
    int pos = 0;
    int paginationSize = 2;
    while (true) {
      filter.setOffset(Integer.valueOf(pos));
      List<Post> result = WP.getPosts(filter);
      assertTrue(result.size() <= paginationSize);
      if (result.size() == 0) {
        break;
      }
      pos = pos + paginationSize;
    }
    WP.deletePost(post1.getPost_id());
    WP.deletePost(post2.getPost_id());
    WP.deletePost(post3.getPost_id());
    WP.deletePost(post4.getPost_id());
    WP.deletePost(post5.getPost_id());
  }

  private Post create1Post() throws InsufficientRightsException, InvalidArgumentsException,
      ObjectNotFoundException, XmlRpcFault {
    Post post = new Post();
    post.setPost_name("test post name");
    post.setPost_content("test content");
    post.setPost_title("test post title");
    post.setPost_excerpt("test post excerpt");
    Integer p = WP.newPost(post);
    return WP.getPost(p);
  }
}
