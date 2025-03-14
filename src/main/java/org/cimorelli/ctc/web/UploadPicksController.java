package org.cimorelli.ctc.web;

import static spark.Spark.post;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;

import org.cimorelli.ctc.dto.PickRow;
import org.cimorelli.ctc.util.ExcelUtil;

import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import spark.template.freemarker.FreeMarkerEngine;

public class UploadPicksController extends BaseController {

	public static void setup( FreeMarkerEngine freeMarker ) {

		UploadPicksController uploadPicksController = new UploadPicksController();
		post( "/parsePicksExcel", uploadPicksController::parsePicksExcel );
	}

	public Object parsePicksExcel( Request req, Response res ) {
		// Set multipart config for file upload processing
		req.attribute( "org.eclipse.jetty.multipartConfig", new MultipartConfigElement( "/temp" ) );
		List<PickRow> picks = new ArrayList<>();
		try {
			InputStream is = req.raw().getPart( "picksFile" ).getInputStream();
			ExcelUtil.parsePicksSpreadsheet( picks, is );
			is.close();
		} catch( Exception e ) {
			e.printStackTrace();
			res.status( 500 );
			return "Upload Failed: " + e.getMessage();
		}
		// Return JSON using Gson (add Gson dependency if needed)
		res.type( "application/json" );
		return new Gson().toJson( picks );
	}
}
