package org.cimorelli.ctc.web;

import static spark.Spark.get;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cimorelli.ctc.dbo.Entrant;
import org.cimorelli.ctc.dto.LeaderboardRow;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.freemarker.FreeMarkerEngine;

public class LeaderboardController extends BaseController {

	public static void setup( FreeMarkerEngine freeMarker ) {

		LeaderboardController leaderboardController = new LeaderboardController();
		get( "/", leaderboardController::redirectToLeaderboard, freeMarker );
		get( "/leaderboard", leaderboardController::showLeaderboard, freeMarker );
	}

	public ModelAndView redirectToLeaderboard( Request req, Response res ) {

		res.redirect( "/leaderboard" );
		return null;
	}

	public ModelAndView showLeaderboard( Request req, Response res ) {

		int currentPoolYear = conferenceYearDao.findCurrentYear();
		List<Entrant> entrants = entrantDao.findAll();

		List<LeaderboardRow> leaderboard = new ArrayList<>();
		for( Entrant e : entrants ) {
			LeaderboardRow row = new LeaderboardRow();
			row.setNickname( e.getNickname() );
			row.setPlayingForFun( e.isPlayingForFun() );
			row.setPoolYear( currentPoolYear );
			row.setPicks( pickDao.findByEntrantIdAndYear( e.getEntrantId(), currentPoolYear ) );
			row.calculate();
			leaderboard.add( row );
		}

		// Sort by totalPoints descending
		leaderboard.sort( ( r1, r2 ) -> r2.getTotalPoints().compareTo( r1.getTotalPoints() ) );

		Map<String, Object> model = new HashMap<>();
		updateAlerts( req, model );
		model.put( "leaderboard", leaderboard );
		return new ModelAndView( model, "leaderboard.ftl" );
	}
}