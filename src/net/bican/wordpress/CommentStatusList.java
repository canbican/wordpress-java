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
 * Comment status names for the blog
 * 
 * @author Can Bican <can@bican.net>
 *
 */
public class CommentStatusList extends XmlRpcMapped implements StringHeader {

  String hold;

  String approve;

  String spam;

  /**
   * @return hold description
   */
  public String getHold() {
    return this.hold;
  }

  /**
   * @return approved description
   */
  public String getApprove() {
    return this.approve;
  }

  /**
   * @return spam description
   */
  public String getSpam() {
    return this.spam;
  }

  /**
   * (non-Javadoc)
   * @see net.bican.wordpress.StringHeader#getStringHeader()
   */
  public String getStringHeader() {
    final String TAB = ":";
    return "Name" + TAB + "Description";
  }

}
