package org.cimorelli.ctc.web;

import static spark.Spark.post;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;

import org.cimorelli.ctc.dto.PickRow;

import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import spark.template.freemarker.FreeMarkerEngine;

public class UploadPicksController extends BaseController {

	public static void setup( FreeMarkerEngine freeMarker ) {

		post( "/parsePicksExcel", UploadPicksController::parsePicksExcel );
	}

	public static Object parsePicksExcel( Request req, Response res ) {
		// Set multipart config for file upload processing
		req.attribute( "org.eclipse.jetty.multipartConfig", new MultipartConfigElement( "/temp" ) );
		List<PickRow> picks = new ArrayList<>();
		try {
			Part filePart = req.raw().getPart( "picksFile" );
			InputStream is = filePart.getInputStream();

			// TODO: Use Apache POI to parse the Excel file from 'is'
			// For this stub, we'll simulate parsing by adding dummy data.
			picks.add( new PickRow( "Round 1", "Team A", 2 ) );
			picks.add( new PickRow( "Round 2", "Team B", 1 ) );

			is.close();
		} catch( Exception e ) {
			e.printStackTrace();
			res.status( 500 );
			return "Error parsing Excel file.";
		}
		// Return JSON using Gson (add Gson dependency if needed)
		res.type( "application/json" );
		return new Gson().toJson( picks );
	}
}
