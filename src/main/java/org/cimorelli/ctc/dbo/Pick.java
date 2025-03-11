package org.cimorelli.ctc.dbo;

import java.math.BigDecimal;
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
@Table(name = "Picks")
public class Pick extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pickId;
	private int entrantId;
	private int conferenceId;
	private int poolYear;
	@Enumerated(EnumType.STRING)
	private Round round;
	private String teamName;
	private BigDecimal upsetPoints;
	private BigDecimal upsetMultiplier;
	private BigDecimal roundPoints;
	private BigDecimal confMultiplier;
	private BigDecimal totalPotentialPoints;
	private BigDecimal pointsEarned;
	private LocalDateTime submittedTime;

	public void calculateTotalPotentialPoints() {

		BigDecimal weightedUpsetPoints = upsetPoints.multiply( upsetMultiplier );
		BigDecimal unweightedTotalPoints = roundPoints.add( weightedUpsetPoints );
		totalPotentialPoints = unweightedTotalPoints.multiply( confMultiplier );
	}

	public void scorePick( boolean isCorrect ) {

		pointsEarned = isCorrect ? totalPotentialPoints : BigDecimal.ZERO;
	}

	public int getPickId() {

		return pickId;
	}

	public void setPickId( int pickId ) {

		this.pickId = pickId;
	}

	public int getEntrantId() {

		return entrantId;
	}

	public void setEntrantId( int entrantId ) {

		this.entrantId = entrantId;
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

	public BigDecimal getUpsetMultiplier() {

		return upsetMultiplier;
	}

	public void setUpsetMultiplier( BigDecimal upsetMultiplier ) {

		this.upsetMultiplier = upsetMultiplier;
	}

	public BigDecimal getConfMultiplier() {

		return confMultiplier;
	}

	public void setConfMultiplier( BigDecimal confMultiplier ) {

		this.confMultiplier = confMultiplier;
	}

	public BigDecimal getTotalPotentialPoints() {

		return totalPotentialPoints;
	}

	public void setTotalPotentialPoints( BigDecimal totalPotentialPoints ) {

		this.totalPotentialPoints = totalPotentialPoints;
	}

	public BigDecimal getPointsEarned() {

		return pointsEarned;
	}

	public void setPointsEarned( BigDecimal pointsEarned ) {

		this.pointsEarned = pointsEarned;
	}

	public LocalDateTime getSubmittedTime() {

		return submittedTime;
	}

	public void setSubmittedTime( LocalDateTime submittedTime ) {

		this.submittedTime = submittedTime;
	}
}
