package net.bican.wordpress.test;

import static org.junit.Assert.*;

import java.util.List;

import net.bican.wordpress.Page;

import org.junit.Test;

@SuppressWarnings({ "static-method", "javadoc", "nls", "boxing" })
public class PageTest extends AbstractWordpressTest {

  @Test
  public void testPage() throws Exception {
    List<Page> pages = WP.getPages();
    for (Page page : pages) {
      WP.deletePage(page.getPage_id(), "true");
    }
    pages = WP.getPages();
    assertNotNull(pages);
    assertEquals(0, pages.size());
    Page page = new Page();
    page.setDescription("deneme");
    String newPageIds = WP.newPage(page, "publish");
    int newPageId = Integer.valueOf(newPageIds).intValue();
    Page pageNow = WP.getPage(newPageId);
    assertNotNull(pageNow);
    assertEquals("deneme", pageNow.getDescription());
  }
}
