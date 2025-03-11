package org.cimorelli.ctc.web;

import static spark.Spark.*;

import org.cimorelli.ctc.util.DatabaseUtil;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import spark.template.freemarker.FreeMarkerEngine;

public class WebApp {

	public static void main( String[] args ) {

		DatabaseUtil.updateLiquibase();
		configureApp();
	}

	private static void configureApp() {
		// Set the server port (default is 4567)
		port( 4567 );

		// Serve static files from the /public directory in resources
		staticFiles.location( "/public" );

		// Print exceptions to the console
		exception( Exception.class, ( e, request, response ) -> {
			e.printStackTrace();
		} );

		// Initialize the FreeMarker configuration
		Configuration freeMarkerConfig = new Configuration( Configuration.VERSION_2_3_31 );
		freeMarkerConfig.setClassForTemplateLoading( WebApp.class, "/templates" );
		freeMarkerConfig.setDefaultEncoding( "UTF-8" );
		freeMarkerConfig.setTemplateExceptionHandler( TemplateExceptionHandler.RETHROW_HANDLER );

		// Initialize the FreeMarker template engine with the configuration
		FreeMarkerEngine freeMarker = new FreeMarkerEngine( freeMarkerConfig );

		// Build the app routes for each controller
		setupControllers( freeMarker );
	}

	private static void setupControllers( FreeMarkerEngine freeMarker ) {

		HomePageController.setup( freeMarker );
		PickEntryController.setup( freeMarker );
		LeaderboardController.setup( freeMarker );
		ConferenceWriteupsController.setup( freeMarker );
		PicksController.setup( freeMarker );
		ResultEntryController.setup( freeMarker );
	}
}
