package org.cimorelli.ctc.dbo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.cimorelli.ctc.enums.Round;

@Entity
@Table(name = "Results")
public class Result extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int resultId;
	private int conferenceId;
	private int poolYear;
	@Enumerated(EnumType.STRING)
	private Round round;
	private String winningTeamName;
	private String losingTeamName;
	private LocalDate gameDate;
	private LocalDateTime submittedTime;

	public int getResultId() {

		return resultId;
	}

	public void setResultId( int resultId ) {

		this.resultId = resultId;
	}

	public int getConferenceId() {

		return conferenceId;
	}

	public void setConferenceId( int conferenceId ) {

		this.conferenceId = conferenceId;
	}

	public int getPoolYear() {

		return poolYear;
	}

	public void setPoolYear( int poolYear ) {

		this.poolYear = poolYear;
	}

	public Round getRound() {

		return round;
	}

	public void setRound( Round round ) {

		this.round = round;
	}

	public String getWinningTeamName() {

		return winningTeamName;
	}

	public void setWinningTeamName( String winningTeamName ) {

		this.winningTeamName = winningTeamName;
	}

	public String getLosingTeamName() {

		return losingTeamName;
	}

	public void setLosingTeamName( String losingTeamName ) {

		this.losingTeamName = losingTeamName;
	}

	public LocalDateTime getSubmittedTime() {

		return submittedTime;
	}

	public void setSubmittedTime( LocalDateTime submittedTime ) {

		this.submittedTime = submittedTime;
	}

	public LocalDate getGameDate() {

		return gameDate;
	}

	public void setGameDate( LocalDate gameDate ) {

		this.gameDate = gameDate;
	}
}
