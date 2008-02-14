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

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;

/**
 * 
 * A composite configuration class that combines preferences and command line.
 * 
 * @author Can Bican &lt;can@bican.net&gt;
 * 
 */
public class WpCliConfiguration {

  private CompositeConfiguration config = null;

  /**
   * @param args
   *          Command line arguments
   * @param options
   *          Command line options
   * @param cl
   *          Calling class
   * @throws ParseException
   */
  public WpCliConfiguration(String[] args, Options options, Class<?> cl)
      throws ParseException {
    Collection<Configuration> confs = new ArrayList<Configuration>();
    confs.add(new CliConfiguration(args, options));
    confs.add(new PreferencesConfiguration(cl));
    config = new CompositeConfiguration(confs);
  }

  /**
   * @param key
   *          Option key to check
   * @return <code>true</code> if the configuration contains the key
   */
  public boolean hasOption(String key) {
    return config.containsKey(key);
  }

  /**
   * @param key
   *          Option key to retrieve
   * @return Value of the option key
   */
  public String getOptionValue(String key) {
    return config.getString(key);
  }

}
