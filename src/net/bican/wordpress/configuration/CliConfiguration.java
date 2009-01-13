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

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.configuration.BaseConfiguration;

/**
 * 
 * Implement a simple CLI configuration in apache commons configuration style
 * 
 * @author Can Bican &lt;can@bican.net&gt;
 * 
 */
public class CliConfiguration extends BaseConfiguration {
  /**
   * 
   * generates the configuration in terms of arguments and options
   * 
   * @param args Command line arguments
   * @param options Command line options
   * @throws ParseException When the configuration cannot be parsed
   */
  public CliConfiguration(String[] args, Options options) throws ParseException {
    CommandLineParser parser = new BasicParser();
    CommandLine commandLine = parser.parse(options, args);
    for (Option option : commandLine.getOptions()) {
      String key = option.getLongOpt();
      String val = option.getValue();
      if (val == null) {
        this.addProperty(key, "N/A");
      } else {
        this.addProperty(key, val);
      }
    }
  }
}
