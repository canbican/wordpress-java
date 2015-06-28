package net.bican.wordpress;

/**
 * Option object for a blog.
 * 
 * @author Can Bican &lt;can@bican.net&gt;
 */
public class Option extends XmlRpcMapped implements StringHeader {
  String name;
  String desc;
  String value;
  Boolean readonly;
  
  /**
   * @return the name
   */
  public String getName() {
    return this.name;
  }
  
  /**
   * @param name
   *          name to set
   */
  public void setName(String name) {
    this.name = name;
  }
  
  /**
   * @return the description
   */
  public String getDesc() {
    return this.desc;
  }
  
  /**
   * @return the value
   */
  public String getValue() {
    return this.value;
  }
  
  /**
   * @return read-only status
   */
  public Boolean isReadonly() {
    return this.readonly;
  }
  
  /**
   * @param desc
   *          description to set
   */
  public void setDesc(String desc) {
    this.desc = desc;
  }
  
  /**
   * @param readonly
   *          read-only status to set
   */
  public void setReadonly(Boolean readonly) {
    this.readonly = readonly;
  }
  
  /**
   * @param value
   *          value to set
   */
  public void setValue(String value) {
    this.value = value;
  }
  
  @Override
  public String getStringHeader() {
    return ""; //$NON-NLS-1$
  }
  
}
