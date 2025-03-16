package org.cimorelli.ctc.web;

import static spark.Spark.get;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.cimorelli.ctc.dbo.Entrant;
import org.cimorelli.ctc.dbo.Pick;
import org.cimorelli.ctc.dbo.Result;
import org.cimorelli.ctc.dto.GameResultRow;
import org.cimorelli.ctc.enums.PickResult;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.freemarker.FreeMarkerEngine;

public class ResultsController extends BaseController {

	public static void setup( FreeMarkerEngine freeMarker ) {

		ResultsController resultsController = new ResultsController();
		get( "/results", resultsController::showGameResults, freeMarker );
	}

	public ModelAndView showGameResults( Request req, Response res ) {

		Map<String, Object> model = new HashMap<>();
		updateAlerts( req, model );

		Integer currentYear = conferenceYearDao.findCurrentYear();
		List<Result> results = resultDao.findByYear( currentYear );
		List<GameResultRow> resultRows = mapResultRows( results );

		model.put( "gameResults", resultRows );

		return new ModelAndView( model, "results.ftl" );
	}

	private List<GameResultRow> mapResultRows( List<Result> results ) {

		List<GameResultRow> resultRows = new ArrayList<>();
		for( Result result : results ) {
			GameResultRow resultRow = new GameResultRow();
			resultRow.setGameDate( result.getGameDate() );
			resultRow.setConference( conferenceDao.findById( result.getConferenceId() ).getCtcName() );
			resultRow.setRound( result.getRound() );
			resultRow.setWinningTeam( result.getWinningTeamName() );
			resultRow.setLosingTeam( result.getLosingTeamName() );

			List<Pick> associatedPicks = pickDao.findByResult( result.getResultId() );
			List<Pick> correctPicks = associatedPicks.stream().filter( p -> p.getResult() == PickResult.CORRECT ).toList();
			Pick randomCorrectPick = correctPicks.stream().findFirst().orElse( null );
			BigDecimal pointValue = randomCorrectPick != null ? randomCorrectPick.getTotalPotentialPoints() : BigDecimal.ZERO;
			resultRow.setPointValue( pointValue );

			List<Integer> entrantWinnersIds = correctPicks.stream().map( Pick::getEntrantId ).collect( Collectors.toList() );
			List<Entrant> entrantWinners = entrantDao.findById( entrantWinnersIds );
			resultRow.setEntrantWinners( entrantWinners.stream().map( Entrant::getNickname ).toList() );

			resultRows.add( resultRow );
		}
		return resultRows;
	}
}
