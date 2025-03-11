package org.cimorelli.ctc.dto;

import java.math.BigDecimal;
import java.util.List;

import org.cimorelli.ctc.dbo.Pick;
import org.cimorelli.ctc.enums.PickResult;
import org.cimorelli.ctc.enums.Round;

public class LeaderboardRow {

	private String nickname;
	private boolean playingForFun;
	private int poolYear;
	private List<Pick> picks;

	private int totalCorrectPicks = 0;
	private int totalCorrectUpsets = 0;
	private int totalCorrectChampions = 0;
	private BigDecimal totalUpsetPoints = BigDecimal.ZERO;
	private BigDecimal totalPoints = BigDecimal.ZERO;
	private BigDecimal pointsOnTable = BigDecimal.ZERO;

	public void calculate() {

		for( Pick pick : picks ) {
			if( pick.getResult() == PickResult.CORRECT ) {
				totalCorrectPicks++;
				if( pick.getWeightedUpsetPoints().signum() > 0 ) {
					totalCorrectUpsets++;
					totalUpsetPoints = totalUpsetPoints.add( pick.getWeightedUpsetPoints() );
				}
				if( pick.getRound() == Round.CHAMP ) {
					totalCorrectChampions++;
				}
				totalPoints = totalPoints.add( pick.getTotalPotentialPoints() );
			} else if( pick.getResult() == PickResult.UNDECIDED ) {
				pointsOnTable = pointsOnTable.add( pick.getTotalPotentialPoints() );
			}
		}
	}

	public String getNickname() {

		return nickname;
	}

	public void setNickname( String nickname ) {

		this.nickname = nickname;
	}

	public boolean isPlayingForFun() {

		return playingForFun;
	}

	public void setPlayingForFun( boolean playingForFun ) {

		this.playingForFun = playingForFun;
	}

	public int getPoolYear() {

		return poolYear;
	}

	public void setPoolYear( int poolYear ) {

		this.poolYear = poolYear;
	}

	public List<Pick> getPicks() {

		return picks;
	}

	public void setPicks( List<Pick> picks ) {

		this.picks = picks;
	}

	public int getTotalCorrectPicks() {

		return totalCorrectPicks;
	}

	public void setTotalCorrectPicks( int totalCorrectPicks ) {

		this.totalCorrectPicks = totalCorrectPicks;
	}

	public int getTotalCorrectUpsets() {

		return totalCorrectUpsets;
	}

	public void setTotalCorrectUpsets( int totalCorrectUpsets ) {

		this.totalCorrectUpsets = totalCorrectUpsets;
	}

	public int getTotalCorrectChampions() {

		return totalCorrectChampions;
	}

	public void setTotalCorrectChampions( int totalCorrectChampions ) {

		this.totalCorrectChampions = totalCorrectChampions;
	}

	public BigDecimal getTotalUpsetPoints() {

		return totalUpsetPoints;
	}

	public void setTotalUpsetPoints( BigDecimal totalUpsetPoints ) {

		this.totalUpsetPoints = totalUpsetPoints;
	}

	public BigDecimal getTotalPoints() {

		return totalPoints;
	}

	public void setTotalPoints( BigDecimal totalPoints ) {

		this.totalPoints = totalPoints;
	}

	public BigDecimal getPointsOnTable() {

		return pointsOnTable;
	}

	public void setPointsOnTable( BigDecimal pointsOnTable ) {

		this.pointsOnTable = pointsOnTable;
	}
}
