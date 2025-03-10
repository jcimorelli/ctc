package org.cimorelli.ctc.enums;

import java.math.BigDecimal;

public enum Round {
	CHAMP( 12, "Final" ),
	SF( 8, "Semifinal" ),
	QF( 4, "Quarterfinal" ),
	OF( 2, "Octofinal" ),
	R1( 1, "Round 1" ),
	R2( 1, "Round 2" ),
	R3( 1, "Round 3" ),
	R4( 1, "Round 4" );

	private final BigDecimal roundPoints;
	private final String description;

	Round( int roundPoints, String description ) {

		this.roundPoints = BigDecimal.valueOf( roundPoints );
		this.description = description;
	}

	public BigDecimal getRoundPoints() {

		return roundPoints;
	}

	public String getDescription() {

		return description;
	}

}
