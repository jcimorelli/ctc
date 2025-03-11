package org.cimorelli.ctc.web;

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
		get( "/", homePageController::redirectToHome, freeMarker );
		get( "/home", homePageController::showHomePage, freeMarker );
	}

	public ModelAndView redirectToHome( Request req, Response res ) {

		res.redirect( "/home" );
		return null;
	}

	public ModelAndView showHomePage( Request req, Response res ) {

		Map<String, Object> model = new HashMap<>();
		updateAlerts( req, model );
		return new ModelAndView( model, "homePage.ftl" );
	}

}
