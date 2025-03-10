package org.cimorelli.ctc.util;

public class UserUtil {

	public static boolean authenticate( String username, String password ) {
		// Replace with actual authentication logic
		return "user".equals( username ) && "pass".equals( password );
	}
}
