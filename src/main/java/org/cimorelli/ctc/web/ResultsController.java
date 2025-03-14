package org.cimorelli.ctc.web;

import static spark.Spark.get;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.freemarker.FreeMarkerEngine;

public class ResultsController extends BaseController {

	public static void setup( FreeMarkerEngine freeMarker ) {

		ResultsController resultsController = new ResultsController();
		get( "/results", resultsController::showResults, freeMarker );
	}

	public ModelAndView showResults( Request req, Response res ) {

		Map<String, Object> model = new HashMap<>();
		updateAlerts( req, model );
		return new ModelAndView( model, "results.ftl" );
	}

}
