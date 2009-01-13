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
 * Class that represents a wordpress attachment.
 * 
 * @author Can Bican &lt;can@bican.net&gt;
 * 
 */
class Attachment extends XmlRpcMapped {
  byte[] bits;

  String name;

  Boolean overwrite;

  String type;

  /**
   * @return the bits
   */
  public byte[] getBits() {
    return this.bits;
  }

  /**
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * @return the overwrite
   */
  public Boolean getOverwrite() {
    return this.overwrite;
  }

  /**
   * @return the type
   */
  public String getType() {
    return this.type;
  }

  /**
   * @param bits the bits to set
   */
  public void setBits(byte[] bits) {
    this.bits = bits;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @param overwrite the overwrite to set
   */
  public void setOverwrite(Boolean overwrite) {
    this.overwrite = overwrite;
  }

  /**
   * @param type the type to set
   */
  public void setType(String type) {
    this.type = type;
  }
}
