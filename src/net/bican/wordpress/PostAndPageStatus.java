package net.bican.wordpress;

/**
 * @author can
 * 
 *         TODO
 * 
 */
public class PostAndPageStatus extends XmlRpcMapped implements StringHeader {
  String status;
  String description;

  /**
   * @return the status
   */
  public String getStatus() {
    return this.status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * @param description the description to set 
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * 
   * (non-Javadoc)
   * @see net.bican.wordpress.StringHeader#getStringHeader()
   */
  public String getStringHeader() {
    return "";
  }
}
