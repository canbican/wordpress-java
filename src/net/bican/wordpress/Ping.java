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
