package org.cimorelli.ctc.web;

import static spark.Spark.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.cimorelli.ctc.dbo.Pick;
import org.cimorelli.ctc.enums.PickResult;
import org.cimorelli.ctc.enums.Round;

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

		model.put( "conferenceOptions", conferenceDao.findAll() );
		model.put( "roundOptions", Arrays.asList( Round.values() ) );
		return new ModelAndView( model, "pickEntry.ftl" );
	}

	public ModelAndView processPickEntry( Request req, Response res ) {

		try {
			// The conference selection remains a single value
			int conferenceId = Integer.parseInt( req.queryParams( "conferenceId" ) );

			// The following fields are submitted as arrays (from table rows)
			String[] rounds = req.queryParamsValues( "round[]" );
			String[] teamNames = req.queryParamsValues( "teamName[]" );
			String[] upsetPoints = req.queryParamsValues( "upsetPoints[]" );

			// Basic validation: ensure arrays are present and have matching lengths
			if( rounds == null || teamNames == null || upsetPoints == null ) {
				displayError( req, "No picks were submitted." );
				res.redirect( "/pickEntry" );
				return null;
			}
			if( rounds.length != teamNames.length || rounds.length != upsetPoints.length ) {
				displayError( req, "Mismatch in number of submitted rows." );
				res.redirect( "/pickEntry" );
				return null;
			}

			savePicks( rounds, teamNames, upsetPoints, conferenceId );
			displayConfirmation( req, "Picks submitted!" );
		} catch( Exception e ) {
			displayError( req, e.getMessage() );
			e.printStackTrace();
		}

		// After processing, redirect back to the pick entry back
		res.redirect( "/pickEntry" );
		return null;
	}

	private void savePicks( String[] rounds, String[] teamNames, String[] upsetPoints, int conferenceId ) {

		int poolYear = conferenceYearDao.findCurrentYear();
		BigDecimal conferenceMultiplier = conferenceYearDao.findMultiplier( conferenceId, poolYear );
		for( int i = 0; i < rounds.length; i++ ) {
			Round round = Round.valueOf( rounds[i] );
			String teamName = teamNames[i];
			BigDecimal upsetPts = BigDecimal.valueOf( Long.parseLong( upsetPoints[i] ) );

			Pick pick = new Pick();
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
