package org.cimorelli.ctc.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.cimorelli.ctc.dto.PickRow;

public abstract class ExcelUtil {

	private static final boolean DEBUG = true;

	public static void parsePicksSpreadsheet( List<PickRow> picks, InputStream is ) throws IOException {

		Workbook workbook = WorkbookFactory.create( is );
		Sheet outputSheet = workbook.getSheetAt( 0 );
		Iterator<Row> rowIter = outputSheet.rowIterator();

		// Skip header row
		rowIter.next();

		int rowIndex = 0;
		String currentConf = "";
		String currentRound = "";
		while( rowIter.hasNext() ) {
			Row row = rowIter.next();

			String currentTeam = "";
			BigDecimal currentUpsetPoints = BigDecimal.ZERO;
			for( int cellIndex = 0; cellIndex < 5; cellIndex++ ) {
				Cell cell = row.getCell( cellIndex );
				CellValue cellVal = getCellValue( workbook, cell );
				debugStmt( cellVal, rowIndex, cellIndex );

				// Parse Conference
				if( cellIndex == 1 && cellVal != null ) {
					currentConf = getString( cellVal );
				}
				// Parse Round
				if( cellIndex == 2 && cellVal != null ) {
					currentRound = getString( cellVal );
				}
				// Parse Team
				if( cellIndex == 3 ) {
					currentTeam = getString( cellVal );
				}
				// Parse UpsetPoints
				if( cellIndex == 4 ) {
					currentUpsetPoints = getDecimal( cellVal );
				}
			}

			// Add Pick to List
			picks.add( new PickRow( currentConf, currentRound, currentTeam, currentUpsetPoints ) );
		}
	}

	private static CellValue getCellValue( Workbook workbook, Cell cell ) {

		if( cell == null ) {
			return null;
		}
		return workbook.getCreationHelper().createFormulaEvaluator().evaluate( cell );
	}

	private static String getString( Workbook workbook, Cell cell ) {

		return getString( getCellValue( workbook, cell ) );
	}

	private static String getString( CellValue cellVal ) {

		if( cellVal == null ) {
			ValidateUtil.fail( "Cell is null" );
		} else if( cellVal.getCellType() == CellType.BLANK ) {
			return "";
		} else if( cellVal.getCellType() == CellType.STRING ) {
			return cellVal.getStringValue().trim();
		} else if( cellVal.getCellType() == CellType.NUMERIC ) {
			return Double.toString( cellVal.getNumberValue() );
		}
		ValidateUtil.fail( "Cell parsing failed" );
		return null;
	}

	private static Integer getInteger( Workbook workbook, Cell cell ) {

		return getInteger( getCellValue( workbook, cell ) );
	}

	private static Integer getInteger( CellValue cellVal ) {

		if( cellVal == null ) {
			ValidateUtil.fail( "Cell is null" );
		} else if( cellVal.getCellType() == CellType.BLANK ) {
			return null;
		} else if( cellVal.getCellType() == CellType.STRING ) {
			try {
				return Integer.parseInt( cellVal.getStringValue().trim() );
			} catch( NumberFormatException e ) {
				ValidateUtil.fail( "Cell parsing failed", e );
			}
		}
		ValidateUtil.fail( "Cell parsing failed" );
		return null;
	}

	private static BigDecimal getDecimal( Workbook workbook, Cell cell ) {

		return getDecimal( getCellValue( workbook, cell ) );
	}

	private static BigDecimal getDecimal( CellValue cellVal ) {

		if( cellVal == null ) {
			ValidateUtil.fail( "Cell is null" );
		} else if( cellVal.getCellType() == CellType.BLANK ) {
			return null;
		} else if( cellVal.getCellType() == CellType.NUMERIC ) {
			return BigDecimal.valueOf( cellVal.getNumberValue() );
		} else if( cellVal.getCellType() == CellType.STRING ) {
			try {
				return BigDecimal.valueOf( Double.parseDouble( cellVal.getStringValue().trim() ) );
			} catch( NumberFormatException e ) {
				ValidateUtil.fail( "Cell parsing failed", e );
			}
		}
		ValidateUtil.fail( "Cell parsing failed" );
		return null;
	}

	private static void debugStmt( CellValue cellVal, int rowIndex, int cellIndex ) {

		if( DEBUG ) {
			StringBuilder sb = new StringBuilder();
			sb.append( "Cell " ).append( rowIndex ).append( "-" ).append( cellIndex )
				.append( " (" ).append( cellVal == null ? "NULL" : cellVal.getCellType() ).append( ")" ).append( ": " )
				.append( cellVal == null ? "NULL" : getString( cellVal ) );
			System.out.println( sb );
		}
	}
}