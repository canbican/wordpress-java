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
package net.bican.wordpress.configuration;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import org.apache.commons.configuration.BaseConfiguration;

/**
 * 
 * 
 * @author Can Bican &lt;can@bican.net&gt;
 * 
 */
public class PreferencesConfiguration extends BaseConfiguration {

  /**
   * @param cl
   *          Calling class
   */
  public PreferencesConfiguration(Class<?> cl) {
    try {
      Preferences p = Preferences.userNodeForPackage(cl);
      for (String preference : p.keys()) {
        this.addProperty(preference, p.get(preference, null));
      }
    } catch (BackingStoreException e) {
      // then we end up with an empty set of preferences, no big deal
    }
  }
}
