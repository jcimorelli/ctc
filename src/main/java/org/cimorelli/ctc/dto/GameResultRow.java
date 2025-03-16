package org.cimorelli.ctc.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.cimorelli.ctc.enums.Round;

public class GameResultRow {

	private LocalDate gameDate;
	private String conference;
	private Round round;
	private String winningTeam;
	private String losingTeam;
	private BigDecimal pointValue;
	private List<String> entrantWinners;

	public LocalDate getGameDate() {

		return gameDate;
	}

	public void setGameDate( LocalDate gameDate ) {

		this.gameDate = gameDate;
	}

	public String getConference() {

		return conference;
	}

	public void setConference( String conference ) {

		this.conference = conference;
	}

	public Round getRound() {

		return round;
	}

	public void setRound( Round round ) {

		this.round = round;
	}

	public String getWinningTeam() {

		return winningTeam;
	}

	public void setWinningTeam( String winningTeam ) {

		this.winningTeam = winningTeam;
	}

	public String getLosingTeam() {

		return losingTeam;
	}

	public void setLosingTeam( String losingTeam ) {

		this.losingTeam = losingTeam;
	}

	public BigDecimal getPointValue() {

		return pointValue;
	}

	public void setPointValue( BigDecimal pointValue ) {

		this.pointValue = pointValue;
	}

	public List<String> getEntrantWinners() {

		return entrantWinners;
	}

	public void setEntrantWinners( List<String> entrantWinners ) {

		this.entrantWinners = entrantWinners;
	}
}
