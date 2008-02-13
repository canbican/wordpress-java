package net.bican.wordpress;

/**
 * 
 * Class that hold trackback information
 * 
 * @author Can Bican &lt;can@bican.net&gt;
 *
 */
public class Ping extends XmlRpcMapped {
  String pingTitle;
  String pingURL;
  String pingIP;

  /**
   * @return the pingTitle
   */
  public String getPingTitle() {
    return pingTitle;
  }

  /**
   * @param pingTitle the pingTitle to set
   */
  public void setPingTitle(String pingTitle) {
    this.pingTitle = pingTitle;
  }

  /**
   * @return the pingURL
   */
  public String getPingURL() {
    return pingURL;
  }

  /**
   * @param pingURL the pingURL to set
   */
  public void setPingURL(String pingURL) {
    this.pingURL = pingURL;
  }

  /**
   * @return the pingIP
   */
  public String getPingIP() {
    return pingIP;
  }

  /**
   * @param pingIP the pingIP to set
   */
  public void setPingIP(String pingIP) {
    this.pingIP = pingIP;
  }
}
