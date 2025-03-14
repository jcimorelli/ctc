package org.cimorelli.ctc.dto;

import java.math.BigDecimal;

public class PickRow {

	private String conference;
	private String round;
	private String teamName;
	private BigDecimal upsetPoints;

	public PickRow( String conference, String round, String teamName, BigDecimal upsetPoints ) {

		this.conference = conference;
		this.round = round;
		this.teamName = teamName;
		this.upsetPoints = upsetPoints;
	}

	public String getConference() {

		return conference;
	}

	public void setConference( String conference ) {

		this.conference = conference;
	}

	public String getRound() {

		return round;
	}

	public String getTeamName() {

		return teamName;
	}

	public BigDecimal getUpsetPoints() {

		return upsetPoints;
	}
}
