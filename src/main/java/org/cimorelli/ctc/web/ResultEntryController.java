package org.cimorelli.ctc.web;

import static spark.Spark.get;
import static spark.Spark.post;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.cimorelli.ctc.dbo.Pick;
import org.cimorelli.ctc.dbo.Result;
import org.cimorelli.ctc.enums.PickResult;
import org.cimorelli.ctc.enums.Round;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.freemarker.FreeMarkerEngine;

public class ResultEntryController extends BaseController {

	public static void setup( FreeMarkerEngine freeMarker ) {

		ResultEntryController resultEntryController = new ResultEntryController();
		get( "/resultEntry", resultEntryController::showResultEntry, freeMarker );
		post( "/resultEntry", resultEntryController::processResultEntry, freeMarker );
	}

	public ModelAndView showResultEntry( Request req, Response res ) {

		Map<String, Object> model = new HashMap<>();
		updateAlerts( req, model );

		model.put( "conferenceOptions", conferenceDao.findAll() );
		model.put( "roundOptions", Arrays.asList( Round.values() ) );
		return new ModelAndView( model, "resultEntry.ftl" );
	}

	public ModelAndView processResultEntry( Request req, Response res ) {

		try {
			int conferenceId = Integer.parseInt( req.queryParams( "conferenceId" ) );
			String[] rounds = req.queryParamsValues( "round[]" );
			String[] winningTeamNames = req.queryParamsValues( "winningTeamName[]" );
			String[] losingTeamNames = req.queryParamsValues( "losingTeamName[]" );
			String[] gameDates = req.queryParamsValues( "gameDate[]" );

			// Basic validation: ensure arrays are present and have matching lengths
			if( rounds == null || winningTeamNames == null || losingTeamNames == null || gameDates == null ) {
				displayError( req, "No results were submitted." );
				res.redirect( "/resultEntry" );
				return null;
			}
			if( rounds.length != winningTeamNames.length || rounds.length != gameDates.length || rounds.length != losingTeamNames.length ) {
				displayError( req, "Mismatch in number of submitted rows." );
				res.redirect( "/resultEntry" );
				return null;
			}

			saveResults( rounds, winningTeamNames, losingTeamNames, gameDates, conferenceId );
			displayConfirmation( req, "Results submitted!" );
		} catch( Exception e ) {
			displayError( req, e.getMessage() );
			e.printStackTrace();
		}

		// After processing, redirect back to the result entry back
		res.redirect( "/resultEntry" );
		return null;
	}

	private void saveResults( String[] rounds, String[] winningTeamNames, String[] losingTeamNames, String[] gameDates, int conferenceId ) {

		int poolYear = conferenceYearDao.findCurrentYear();
		for( int i = 0; i < rounds.length; i++ ) {
			Round round = Round.valueOf( rounds[i] );
			String winningTeamName = winningTeamNames[i];
			String losingTeamName = losingTeamNames[i];
			LocalDate gameDate = LocalDate.parse( gameDates[i] );

			Result result = new Result();
			result.setConferenceId( conferenceId );
			result.setPoolYear( poolYear );
			result.setRound( round );
			result.setWinningTeamName( winningTeamName );
			result.setLosingTeamName( losingTeamName );
			result.setGameDate( gameDate );
			result.setSubmittedTime( LocalDateTime.now() );
			resultDao.create( result );

			updatePicks( result );
		}
	}

	private void updatePicks( Result result ) {

		int poolYear = result.getPoolYear();
		Round round = result.getRound();
		String winningTeamName = result.getWinningTeamName();
		String losingTeamName = result.getLosingTeamName();

		// Mark all correct picks
		for( Pick pick : pickDao.findByTeamAndYear( winningTeamName, poolYear ) ) {
			if( pick.getResult() == PickResult.UNDECIDED && pick.getRound() == round ) {
				pick.setResultId( result.getResultId() );
				pick.setResult( PickResult.CORRECT );
				pickDao.update( pick );
			}
		}

		// Mark all incorrect picks (losing team in this round or later)
		for( Pick pick : pickDao.findByTeamAndYear( losingTeamName, poolYear ) ) {
			if( pick.getResult() == PickResult.UNDECIDED && pick.getRound().getOrder() >= round.getOrder() ) {
				pick.setResultId( result.getResultId() );
				pick.setResult( PickResult.WRONG );
				pickDao.update( pick );
			}
		}
	}

}
