package com.joymain.jecs.service;


/**
 * An exception that is thrown by classes wanting to trap unique 
 * constraint violations.  This is used to wrap Spring's 
 * DataIntegrityViolationException so it's checked in the web layer.
 *
 * <p><a href="UserExistsException.java.html"><i>View Source</i></a></p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class UserExistsException extends Exception {
	private static final long serialVersionUID = 4050482305178810162L;

	/**
     * Constructor for UserExistsException.
     *
     * @param message
     */
    public UserExistsException(String message) {
        super(message);
    }
}
