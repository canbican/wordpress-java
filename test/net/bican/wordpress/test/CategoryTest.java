package net.bican.wordpress.test;

import static org.junit.Assert.*;

import java.util.List;

import net.bican.wordpress.Category;

import org.junit.Test;

@SuppressWarnings({ "static-method", "javadoc", "nls", "boxing", "null" })
public class CategoryTest extends AbstractWordpressTest {

  @Test
  public void testCategories() throws Exception {
    List<Category> categories = WP.getCategories();
    if (categories != null) {
      for (Category cat : categories) {
        WP.deleteCategory(Integer.valueOf(cat.getCategoryId()));
      }
    }
    categories = WP.getCategories();
    assertEquals(1, categories.size());
    int r = WP.newCategory("deneme", "deneme-slug", 0);
    String rStr = r + "";
    categories = WP.getCategories();
    assertNotNull(categories);
    assertEquals(2, categories.size());
    Category found = null;
    for (Category cat : categories) {
      if (cat.getCategoryId().equals(rStr)) {
        found = cat;
        break;
      }
    }
    assertNotNull(found);
    assertEquals("deneme", found.getCategoryName());
    assertEquals(rStr, found.getCategoryId());
  }
}
