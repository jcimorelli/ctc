package org.cimorelli.ctc.web;

import static spark.Spark.before;
import static spark.Spark.get;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.freemarker.FreeMarkerEngine;

public class MainMenuController extends BaseController {

	public static void defineRoutes( FreeMarkerEngine freeMarker ) {

		MainMenuController mainMenuController = new MainMenuController();
		before( "/mainMenu", mainMenuController::requireLogin );
		get( "/mainMenu", mainMenuController::showMainMenu, freeMarker );
	}

	public ModelAndView showMainMenu( Request req, Response res ) {

		Map<String, Object> model = new HashMap<>();
		updateAlerts( req, model );
		return new ModelAndView( model, "mainMenu.ftl" );
	}

}
