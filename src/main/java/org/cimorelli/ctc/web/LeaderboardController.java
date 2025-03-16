package org.cimorelli.ctc.web;

import static spark.Spark.get;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cimorelli.ctc.dbo.Entrant;
import org.cimorelli.ctc.dbo.Pick;
import org.cimorelli.ctc.dto.LeaderboardRow;
import org.cimorelli.ctc.util.DateUtil;

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

		// Retrieve filter parameters from query string.
		String conferenceIdParam = req.queryParams( "conferenceId" );
		String gameDayParam = req.queryParams( "gameDay" );

		Map<String, Object> model = new HashMap<>();
		updateAlerts( req, model );
		model.put( "conferenceId", conferenceIdParam );
		model.put( "gameDay", gameDayParam );

		// Obtain distinct game days for the current pool year.
		List<String> gameDays = pickDao.findDistinctGameDaysByYear( currentPoolYear );
		model.put( "gameDays", gameDays );

		List<LeaderboardRow> leaderboard = new ArrayList<>();
		for( Entrant e : entrants ) {
			LeaderboardRow row = new LeaderboardRow();
			row.setNickname( e.getNickname() );
			row.setPlayingForFun( e.isPlayingForFun() );
			row.setPoolYear( currentPoolYear );

			List<Pick> picks = pickDao.findByFilters( e.getEntrantId(), conferenceIdParam == null || conferenceIdParam.isEmpty() ? 0 : Integer.parseInt( conferenceIdParam ),
													  currentPoolYear, "", DateUtil.parseDate( gameDayParam ) );
			row.setPicks( picks );
			row.calculate();
			leaderboard.add( row );
		}

		// Sort by totalPoints descending.
		leaderboard.sort( ( r1, r2 ) -> r2.getTotalPoints().compareTo( r1.getTotalPoints() ) );

		model.put( "leaderboard", leaderboard );

		// Also pass dropdown data for filtering.
		model.put( "entrantOptions", entrantDao.findAll() );
		model.put( "conferenceOptions", conferenceDao.findAll() );

		// JSON string for round options, if needed elsewhere.
		// (Not used in the leaderboard filter in this case)
		// model.put("roundOptionsJson", new Gson().toJson(Arrays.asList(Round.values())));

		return new ModelAndView( model, "leaderboard.ftl" );
	}
}
