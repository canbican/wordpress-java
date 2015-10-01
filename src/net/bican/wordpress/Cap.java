/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress;

import net.bican.wordpress.util.StringHeader;

/**
 * Cap object for a blog.
 *
 * @author Can Bican
 */
public class Cap extends XmlRpcMapped implements StringHeader {
  String assign_terms;
  String delete_terms;
  String edit_terms;
  String manage_terms;
  
  /**
   * @return the assign terms
   */
  public String getAssign_terms() {
    return this.assign_terms;
  }
  
  /**
   * @return the delete terms
   */
  public String getDelete_terms() {
    return this.delete_terms;
  }
  
  /**
   * @return edit terms
   */
  public String getEdit_terms() {
    return this.edit_terms;
  }
  
  /**
   * @return manage terms
   */
  public String getManage_terms() {
    return this.manage_terms;
  }
  
  @Override
  public String getStringHeader() {
    return ""; //$NON-NLS-1$
  }
  
  /**
   * @param assign_terms
   *          the assign terms
   */
  public void setAssign_terms(final String assign_terms) {
    this.assign_terms = assign_terms;
  }
  
  /**
   * @param delete_terms
   *          the delete terms
   */
  public void setDelete_terms(final String delete_terms) {
    this.delete_terms = delete_terms;
  }
  
  /**
   * @param edit_terms
   *          the edit terms
   */
  public void setEdit_terms(final String edit_terms) {
    this.edit_terms = edit_terms;
  }
  
  /**
   * @param manage_terms
   *          the manage terms
   */
  public void setManage_terms(final String manage_terms) {
    this.manage_terms = manage_terms;
  }
  
}
