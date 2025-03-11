package org.cimorelli.ctc.dbo;

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
	private String teamName;
	//FIXME jason - add date without time for the result date

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

	public String getTeamName() {

		return teamName;
	}

	public void setTeamName( String teamName ) {

		this.teamName = teamName;
	}
}
