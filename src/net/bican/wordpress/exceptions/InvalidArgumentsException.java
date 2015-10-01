/*
 * Wordpress-java https://github.com/canbican/wordpress-java/ Copyright
 * 2012-2015 Can Bican <can@bican.net> See the file 'COPYING' in the
 * distribution for licensing terms.
 */
package net.bican.wordpress.exceptions;

/**
 * Signals a failure due to invalid arguments
 *
 * @author Can Bican
 */
public class InvalidArgumentsException extends Exception {
  private static final long serialVersionUID = -3967556703733136497L;
  
  /**
   * default constructor
   */
  public InvalidArgumentsException() {
    super();
  }
  
  /**
   * @param message
   *          argument value that caused the exception
   */
  public InvalidArgumentsException(final String message) {
    super(message);
  }
}
