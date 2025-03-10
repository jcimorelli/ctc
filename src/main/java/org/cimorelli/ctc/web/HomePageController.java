package org.cimorelli.ctc.web;

import static spark.Spark.before;
import static spark.Spark.get;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.freemarker.FreeMarkerEngine;

public class HomePageController extends BaseController {

	public static void setup( FreeMarkerEngine freeMarker ) {

		HomePageController homePageController = new HomePageController();
		before( "/home", homePageController::requireLogin );
		get( "/home", homePageController::showHomePage, freeMarker );
	}

	public ModelAndView showHomePage( Request req, Response res ) {

		Map<String, Object> model = new HashMap<>();
		updateAlerts( req, model );
		return new ModelAndView( model, "homePage.ftl" );
	}

}
