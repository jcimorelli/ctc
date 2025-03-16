package org.cimorelli.ctc.web;

import static spark.Spark.get;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cimorelli.ctc.dbo.Conference;
import org.cimorelli.ctc.dbo.Entrant;
import org.cimorelli.ctc.dbo.Pick;
import org.cimorelli.ctc.enums.Round;
import org.cimorelli.ctc.util.ValidateUtil;

import com.google.gson.Gson;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.freemarker.FreeMarkerEngine;

public class PicksController extends BaseController {

	public static void setup( FreeMarkerEngine freeMarker ) {

		PicksController picksController = new PicksController();
		get( "/picks", picksController::showPicks, freeMarker );
	}

	public ModelAndView showPicks( Request req, Response res ) {

		Map<String, Object> model = new HashMap<>();
		updateAlerts( req, model );

		// Retrieve filter parameters
		String entrantIdParam = req.queryParams( "entrantId" );
		String conferenceIdParam = req.queryParams( "conferenceId" );
		String teamNameParam = req.queryParams( "teamName" );
		model.put( "entrantId", entrantIdParam );
		model.put( "conferenceId", conferenceIdParam );
		model.put( "teamName", teamNameParam );

		Integer entrantId = null;
		Integer conferenceId = null;
		try {
			if( entrantIdParam != null && !entrantIdParam.isEmpty() ) {
				entrantId = Integer.parseInt( entrantIdParam );
			}
			if( conferenceIdParam != null && !conferenceIdParam.isEmpty() ) {
				conferenceId = Integer.parseInt( conferenceIdParam );
			}
		} catch( NumberFormatException e ) {
			ValidateUtil.fail( e );
		}

		// Retrieve the picks for the given filters.
		List<Pick> picks = new ArrayList<>();
		if( entrantId != null && conferenceId != null ) {
			Integer currentPoolYear = conferenceYearDao.findCurrentYear();
			picks = pickDao.findByFilters( entrantId, conferenceId, currentPoolYear, teamNameParam, null );
		}
		model.put( "picks", picks );

		// Populate the dropdowns for filtering.
		model.put( "entrantOptions", entrantDao.findAll() );
		model.put( "conferenceOptions", conferenceDao.findAll() );
		model.put( "teamOptions", pickDao.findTeamsByYear( conferenceYearDao.findCurrentYear() ) );

		// Build a map from entrantId to entrant nickname.
		Map<String, String> entrantNames = new HashMap<>();
		for( Entrant entrant : entrantDao.findAll() ) {
			entrantNames.put( String.valueOf( entrant.getEntrantId() ), entrant.getNickname() );
		}
		model.put( "entrantNames", entrantNames );

		// Build a map from entrantId to entrant nickname.
		Map<String, String> conferenceNames = new HashMap<>();
		for( Conference conference : conferenceDao.findAll() ) {
			conferenceNames.put( String.valueOf( conference.getConferenceId() ), conference.getCtcName() );
		}
		model.put( "conferenceNames", conferenceNames );

		// Also add the JSON string for round options
		Gson gson = new Gson();
		String roundOptionsJson = gson.toJson( Arrays.asList( Round.values() ) );
		model.put( "roundOptionsJson", roundOptionsJson );

		return new ModelAndView( model, "picks.ftl" );
	}

}
