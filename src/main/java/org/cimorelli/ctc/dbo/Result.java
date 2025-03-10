package org.cimorelli.ctc.dbo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Results")
public class Result extends BaseEntity {

	@Id
	private int resultId;
	private int conferenceId;
	private int poolYear;
	private int roundNo;
	private String teamName;

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

	public int getRoundNo() {

		return roundNo;
	}

	public void setRoundNo( int roundNo ) {

		this.roundNo = roundNo;
	}

	public String getTeamName() {

		return teamName;
	}

	public void setTeamName( String teamName ) {

		this.teamName = teamName;
	}
}
