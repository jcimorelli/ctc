package org.cimorelli.ctc.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	public static LocalDate parseDate( String dateStr ) {

		if( dateStr == null || dateStr.isEmpty() ) {
			return null;
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );
		return LocalDate.parse( dateStr, formatter );
	}
}
