package org.cimorelli.ctc.util;

import java.util.Collection;
import java.util.Map;

/**
 * @author cimorelli
 */
public abstract class ValidateUtil {

	public static void check( boolean expression ) {

		if( expression ) {
			fail( "Default Check Error" );
		}
	}

	public static void check( boolean expression, String errorIfTrue ) {

		if( expression ) {
			fail( errorIfTrue );
		}
	}

	public static void nullCheck( Object obj, String errorIfNull ) {

		if( obj == null ) {
			fail( errorIfNull );
		}
	}

	public static void emptyCheck( Collection<? extends Object> collection, String errorIfEmpty ) {

		if( collection == null || collection.size() == 0 ) {
			fail( errorIfEmpty );
		}
	}

	public static void emptyCheck( Map<String, Object> map, String errorIfEmpty ) {

		if( map == null || map.size() == 0 ) {
			fail( errorIfEmpty );
		}
	}

	public static void blankCheck( String string, String errorIfBlank ) {

		if( string == null || string.equals( "" ) ) {
			fail( errorIfBlank );
		}
	}

	public static void failQuietly( Exception exception ) {

		exception.printStackTrace();
	}

	public static void fail( String error ) {

		throw new ValidateException( error );
	}

	public static void fail( Exception exception ) {

		exception.printStackTrace();
		throw new ValidateException( exception );
	}

	public static void fail( String error, Exception exception ) {

		exception.printStackTrace();
		throw new ValidateException( error, exception );
	}

	// Used to determine if the exception was thrown by this class or not
	public static class ValidateException extends RuntimeException {

		private static final long serialVersionUID = -105085098789065782L;

		public ValidateException( String error ) {

			super( error );
		}

		public ValidateException( Exception exception ) {

			super( exception );
		}

		public ValidateException( String error, Exception exception ) {

			super( error, exception );
		}
	}
}