package org.cimorelli.ctc.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

public abstract class MathUtil {

	private static BigInteger precisionLimit = new BigInteger( "999999999999999" );
	private static int SCALE = 15;

	public static boolean isInteger( String string ) {

		try {
			Integer.parseInt( string );
			return true;
		} catch( NumberFormatException e ) {
			return false;
		}
	}

	public static Integer median( Set<Integer> integers ) {

		Integer median = null;
		final List<Integer> list = new ArrayList<Integer>( integers );
		Collections.sort( list );
		final int length = list.size();
		// If our length is even, then we need to find the average of the two centered values
		if( length % 2 == 0 ) {
			int indexA = ( length - 1 ) / 2;
			int indexB = length / 2;

			median = ( list.get( indexA ) + list.get( indexB ) ) / 2;
		}
		// Else if our length is odd, then we simply find the value at the center index
		else {
			int index = ( length - 1 ) / 2;
			median = list.get( index );
		}
		return median;
	}

	public static final BigDecimal divide( BigDecimal dividend, BigDecimal divisor ) {

		if( divisor == null || divisor.equals( BigDecimal.ZERO ) ) {
			return null;
		}
		int scale = SCALE > dividend.scale() ? SCALE : dividend.scale();
		scale = scale > divisor.scale() ? scale : divisor.scale();
		return dividend.divide( divisor, scale, BigDecimal.ROUND_HALF_EVEN );
	}

	public static Double divide( Double dividend, Double divisor ) {

		if( divisor == null || divisor.equals( 0d ) ) {
			return null;
		}
		return divide( BigDecimal.valueOf( dividend ), BigDecimal.valueOf( divisor ) ).doubleValue();
	}

	public static Integer divide( Integer dividend, Integer divisor ) {

		if( divisor == null || divisor.equals( 0 ) ) {
			return null;
		}
		return round0( divide( BigDecimal.valueOf( dividend ), BigDecimal.valueOf( divisor ) ) ).intValue();
	}

	public static Double round0( Double value ) {

		return round( new BigDecimal( value ), 0 ).doubleValue();
	}

	public static Double round2( Double value ) {

		return round( new BigDecimal( value ), 2 ).doubleValue();
	}

	public static BigDecimal round0( BigDecimal value ) {

		return round( value, 0 );
	}

	public static BigDecimal round2( BigDecimal value ) {

		return round( value, 2 );
	}

	public static Double round( Double value, int precision ) {

		return round( new BigDecimal( value ), precision ).doubleValue();
	}

	public static final BigDecimal round( BigDecimal value, int precision ) {

		if( value == null ) {
			return value;
		}
		BigDecimal roundedValue = value;
		int scale = value.scale();
		if( exceedsPrecisionLimit( value ) && precision < scale - 1 ) {
			roundedValue = value.setScale( scale - 1, BigDecimal.ROUND_HALF_UP );
		}
		return roundedValue.setScale( precision, BigDecimal.ROUND_HALF_UP );
	}

	private static boolean exceedsPrecisionLimit( BigDecimal value ) {

		return value.unscaledValue().compareTo( precisionLimit ) > 0;
	}

	public static BigDecimal minOf( BigDecimal... values ) {

		BigDecimal min = values[0];
		for( BigDecimal value : values ) {
			if( lessThan( value, min ) ) {
				min = value;
			}
		}
		return min;
	}

	public static boolean lessThan( BigDecimal value1, BigDecimal value2 ) {

		return value1.compareTo( value2 ) < 0;
	}

	public static boolean lessThan( Integer value1, Integer value2 ) {

		return value1.compareTo( value2 ) < 0;
	}

	public static BigDecimal maxOf( BigDecimal... values ) {

		BigDecimal max = values[0];
		for( BigDecimal value : values ) {
			if( greaterThan( value, max ) ) {
				max = value;
			}
		}
		return max;
	}

	public static Integer maxOf( Integer... values ) {

		Integer max = values[0];
		for( Integer value : values ) {
			if( greaterThan( value, max ) ) {
				max = value;
			}
		}
		return max;
	}

	public static boolean greaterThan( BigDecimal value1, BigDecimal value2 ) {

		return value1.compareTo( value2 ) > 0;
	}

	public static boolean greaterThan( Integer value1, Integer value2 ) {

		return value1.compareTo( value2 ) > 0;
	}

	public static BigDecimal sum( BigDecimal... addends ) {

		BigDecimal result = BigDecimal.ZERO;
		for( BigDecimal addend : addends ) {
			result = result.add( addend );
		}
		return result;
	}

	public static Integer mean( List<Integer> intList ) {

		if( intList == null || intList.isEmpty() ) {
			return null;
		}
		int sum = 0;
		for( Integer item : intList ) {
			sum += item;
		}
		return divide( sum, intList.size() );
	}

	public static double pearsonCorrelation( List<?> orderedList1, List<?> orderedList2 ) {

		ValidateUtil.check( orderedList1.size() != orderedList2.size(), "Cannot compute Pearson Correlation Calculation of differently sized lists." );
		double[] array1 = new double[orderedList1.size()];
		double[] array2 = new double[orderedList2.size()];
		for( int n = 0; n < orderedList1.size(); n++ ) {
			array1[n] = doubleValueOf( orderedList1.get( n ) );
		}
		for( int n = 0; n < orderedList2.size(); n++ ) {
			array2[n] = doubleValueOf( orderedList2.get( n ) );
		}
		return new PearsonsCorrelation().correlation( array1, array2 );
	}

	private static double doubleValueOf( Object obj ) {

		if( obj instanceof Double ) {
			return ( Double )obj;
		} else if( obj instanceof BigDecimal ) {
			return ( ( BigDecimal )obj ).doubleValue();
		} else if( obj instanceof Integer ) {
			return ( ( Integer )obj ).doubleValue();
		}
		ValidateUtil.fail( "Unsupported Type: " + obj.getClass().getSimpleName() );
		return 0d;
	}

}