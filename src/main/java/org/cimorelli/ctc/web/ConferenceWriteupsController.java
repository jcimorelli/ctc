package org.cimorelli.ctc.web;

import static spark.Spark.get;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.freemarker.FreeMarkerEngine;

public class ConferenceWriteupsController extends BaseController {

	public static void setup( FreeMarkerEngine freeMarker ) {

		ConferenceWriteupsController conferenceWriteupsController = new ConferenceWriteupsController();
		get( "/conferenceWriteups", conferenceWriteupsController::showConferenceWriteups, freeMarker );
	}

	public ModelAndView showConferenceWriteups( Request req, Response res ) {

		Map<String, Object> model = new HashMap<>();
		updateAlerts( req, model );
		return new ModelAndView( model, "conferenceWriteups.ftl" );
	}

}
