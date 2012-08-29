/*
 * 
 * Wordpress-java
 * http://code.google.com/p/wordpress-java/
 * 
 * Copyright 2012 Can Bican <can@bican.net>
 * See the file 'COPYING' in the distribution for licensing terms.
 * 
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
   * @param cl Calling class
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
