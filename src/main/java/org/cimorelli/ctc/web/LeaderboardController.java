package org.cimorelli.ctc.web;

import static spark.Spark.get;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.freemarker.FreeMarkerEngine;

public class LeaderboardController extends BaseController {

	public static void setup( FreeMarkerEngine freeMarker ) {

		LeaderboardController leaderboardController = new LeaderboardController();
		get( "/leaderboard", leaderboardController::showLeaderboard, freeMarker );
	}

	public ModelAndView showLeaderboard( Request req, Response res ) {

		Map<String, Object> model = new HashMap<>();
		updateAlerts( req, model );
		return new ModelAndView( model, "leaderboard.ftl" );
	}

}
