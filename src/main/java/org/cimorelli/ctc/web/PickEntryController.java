package org.cimorelli.ctc.web;

import static spark.Spark.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.cimorelli.ctc.dbo.Entrant;
import org.cimorelli.ctc.dbo.Pick;
import org.cimorelli.ctc.enums.PickResult;
import org.cimorelli.ctc.enums.Round;

import com.google.gson.Gson;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.freemarker.FreeMarkerEngine;

public class PickEntryController extends BaseController {

	public static void setup( FreeMarkerEngine freeMarker ) {

		PickEntryController pickEntryController = new PickEntryController();
		get( "/pickEntry", pickEntryController::showPickEntry, freeMarker );
		post( "/pickEntry", pickEntryController::processPickEntry, freeMarker );
	}

	public ModelAndView showPickEntry( Request req, Response res ) {

		Map<String, Object> model = new HashMap<>();
		updateAlerts( req, model );

		model.put( "conferenceOptions", conferenceDao.findAllCtcNames() );
		model.put( "entrantOptions", entrantDao.findAll() );
		model.put( "roundOptions", Arrays.asList( Round.values() ) );

		// Convert round options to JSON and add as roundOptionsJson
		Gson gson = new Gson();
		String conferenceOptionsJson = gson.toJson( conferenceDao.findAllCtcNames() );
		model.put( "conferenceOptionsJson", conferenceOptionsJson );
		String roundOptionsJson = gson.toJson( Arrays.asList( Round.values() ) );
		model.put( "roundOptionsJson", roundOptionsJson );

		return new ModelAndView( model, "pickEntry.ftl" );
	}

	public ModelAndView processPickEntry( Request req, Response res ) {

		try {
			int entrantId = Integer.parseInt( req.queryParams( "entrantId" ) );
			String[] conferences = req.queryParamsValues( "conference[]" );
			String[] rounds = req.queryParamsValues( "round[]" );
			String[] teamNames = req.queryParamsValues( "teamName[]" );
			String[] upsetPoints = req.queryParamsValues( "upsetPoints[]" );

			// Basic validation: ensure arrays are present and have matching lengths
			if( rounds == null || teamNames == null || upsetPoints == null || conferences == null ) {
				displayError( req, "No picks were submitted." );
				res.redirect( "/pickEntry" );
				return null;
			}
			if( rounds.length != teamNames.length || rounds.length != upsetPoints.length || rounds.length != conferences.length ) {
				displayError( req, "Mismatch in number of submitted rows." );
				res.redirect( "/pickEntry" );
				return null;
			}

			savePicks( conferences, rounds, teamNames, upsetPoints, entrantId );
			Entrant entrant = entrantDao.findById( entrantId, Entrant.class );
			displayConfirmation( req, "Picks submitted for " + entrant.getNickname() + "!" );
		} catch( Exception e ) {
			displayError( req, e.getMessage() );
			e.printStackTrace();
		}

		// After processing, redirect back to the pick entry back
		res.redirect( "/pickEntry" );
		return null;
	}

	private void savePicks( String[] conferences, String[] rounds, String[] teamNames, String[] upsetPoints, int entrantId ) {

		int poolYear = conferenceYearDao.findCurrentYear();
		for( int i = 0; i < rounds.length; i++ ) {
			int conferenceId = conferenceDao.findByCtcName( conferences[i] ).getConferenceId();
			BigDecimal conferenceMultiplier = conferenceYearDao.findMultiplier( conferenceId, poolYear );
			Round round = Round.valueOf( rounds[i] );
			String teamName = teamNames[i];
			BigDecimal upsetPts = BigDecimal.valueOf( Long.parseLong( upsetPoints[i] ) );

			Pick pick = new Pick();
			pick.setEntrantId( entrantId );
			pick.setPoolYear( poolYear );
			pick.setConferenceId( conferenceId );
			pick.setRound( round );
			pick.setTeamName( teamName );
			pick.setUpsetPoints( upsetPts );
			pick.setUpsetMultiplier( round.getUpsetMultiplier() );
			pick.setRoundPoints( round.getRoundPoints() );
			pick.setConfMultiplier( conferenceMultiplier );
			pick.calculatePotentialPoints();
			pick.setResult( PickResult.UNDECIDED );
			pick.setSubmittedTime( LocalDateTime.now() );
			pickDao.create( pick );
		}
	}
}
