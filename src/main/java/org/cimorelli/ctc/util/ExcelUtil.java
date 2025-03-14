package org.cimorelli.ctc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.cimorelli.ctc.dto.PickRow;

public abstract class ExcelUtil {

	public static void parsePicksSpreadsheet( List<PickRow> picks, InputStream is ) throws IOException {

		Workbook workbook = WorkbookFactory.create( is );
		Sheet outputSheet = workbook.getSheetAt( 0 );
		Iterator<Row> rowIter = outputSheet.rowIterator();
		while( rowIter.hasNext() ) {
			Row row = rowIter.next();
			PickRow pickRow = new PickRow();

			//pickRow.setRound( row.getCell( 0 ).getStringCellValue() );
			pickRow.setRound( "R1" );

			//pickRow.setTeamName( row.getCell( 1 ).getStringCellValue() );
			pickRow.setTeamName( "Team X" );

			//pickRow.setUpsetPoints( Integer.parseInt( row.getCell( 2 ).getStringCellValue() ) );
			pickRow.setUpsetPoints( 3 );

			picks.add( pickRow );
		}
	}

}