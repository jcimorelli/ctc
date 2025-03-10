package org.cimorelli.ctc.dbo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.cimorelli.ctc.enums.Round;

@Entity
@Table(name = "Picks")
public class Pick extends BaseEntity {

	@Id
	private int pickId;
	private int userId;
	private int conferenceId;
	private int poolYear;
	@Enumerated(EnumType.STRING)
	private Round round;
	private String teamName;
	private BigDecimal upsetPoints;
	private BigDecimal roundPoints;
	private LocalDateTime submittedTime;
	private BigDecimal pointsEarned;

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

	public BigDecimal getUpsetPoints() {

		return upsetPoints;
	}

	public void setUpsetPoints( BigDecimal upsetPoints ) {

		this.upsetPoints = upsetPoints;
	}

	public BigDecimal getRoundPoints() {

		return roundPoints;
	}

	public void setRoundPoints( BigDecimal roundPoints ) {

		this.roundPoints = roundPoints;
	}

	public LocalDateTime getSubmittedTime() {

		return submittedTime;
	}

	public void setSubmittedTime( LocalDateTime submittedTime ) {

		this.submittedTime = submittedTime;
	}

	public BigDecimal getPointsEarned() {

		return pointsEarned;
	}

	public void setPointsEarned( BigDecimal pointsEarned ) {

		this.pointsEarned = pointsEarned;
	}
}
