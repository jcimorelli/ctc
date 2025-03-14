package org.cimorelli.ctc.dto;

public class PickRow {

	private String round;
	private String teamName;
	private int upsetPoints;

	public PickRow( String round, String teamName, int upsetPoints ) {

		this.round = round;
		this.teamName = teamName;
		this.upsetPoints = upsetPoints;
	}

	public String getRound() {

		return round;
	}

	public String getTeamName() {

		return teamName;
	}

	public int getUpsetPoints() {

		return upsetPoints;
	}
}
