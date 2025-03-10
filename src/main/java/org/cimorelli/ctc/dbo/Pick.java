package org.cimorelli.ctc.dbo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Picks")
public class Pick extends BaseEntity {

	@Id
	private int pickId;
	private int userId;
	private int conferenceId;
	private int poolYear;
	private int roundNo;
	private String teamName;
	private int upsetPoints;
	private int roundPoints;
	private LocalDateTime submittedTime;
	private int pointsEarned;

	public int getPickId() {

		return pickId;
	}

	public void setPickId( int pickId ) {

		this.pickId = pickId;
	}

	public int getUserId() {

		return userId;
	}

	public void setUserId( int userId ) {

		this.userId = userId;
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

	public int getUpsetPoints() {

		return upsetPoints;
	}

	public void setUpsetPoints( int upsetPoints ) {

		this.upsetPoints = upsetPoints;
	}

	public int getRoundPoints() {

		return roundPoints;
	}

	public void setRoundPoints( int roundPoints ) {

		this.roundPoints = roundPoints;
	}

	public LocalDateTime getSubmittedTime() {

		return submittedTime;
	}

	public void setSubmittedTime( LocalDateTime submittedTime ) {

		this.submittedTime = submittedTime;
	}

	public int getPointsEarned() {

		return pointsEarned;
	}

	public void setPointsEarned( int pointsEarned ) {

		this.pointsEarned = pointsEarned;
	}
}
