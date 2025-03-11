package org.cimorelli.ctc.enums;

import java.math.BigDecimal;

public enum Round {
	CHAMP( 12, 4, "Final" ),
	SF( 8, 3, "Semifinal" ),
	QF( 4, 2, "Quarterfinal" ),
	OF( 2, 2, "Octofinal" ),
	R1( 1, 1, "Round 1" ),
	R2( 1, 1, "Round 2" ),
	R3( 1, 1, "Round 3" ),
	R4( 1, 1, "Round 4" );

	private final BigDecimal roundPoints;
	private final BigDecimal upsetMultiplier;
	private final String description;

	Round( int roundPoints, int upsetMultiplier, String description ) {

		this.roundPoints = BigDecimal.valueOf( roundPoints );
		this.upsetMultiplier = BigDecimal.valueOf( upsetMultiplier );
		this.description = description;
	}

	public BigDecimal getRoundPoints() {

		return roundPoints;
	}

	public BigDecimal getUpsetMultiplier() {

		return upsetMultiplier;
	}

	public String getDescription() {

		return description;
	}

}
