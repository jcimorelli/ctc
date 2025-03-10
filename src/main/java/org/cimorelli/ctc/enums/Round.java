package org.cimorelli.ctc.enums;

import java.math.BigDecimal;

public enum Round {
	CHAMP( 4, "Final" ),
	SF( 3, "Semifinal" ),
	QF( 2, "Quarterfinal" ),
	OF( 2, "Octofinal" ),
	R1( 1, "Round 1" ),
	R2( 1, "Round 2" ),
	R3( 1, "Round 3" ),
	R4( 1, "Round 4" );

	private final BigDecimal roundMultiplier;
	private final String description;

	Round( int roundMultiplier, String description ) {

		this.roundMultiplier = BigDecimal.valueOf( roundMultiplier );
		this.description = description;
	}

	public BigDecimal getRoundMultiplier() {

		return roundMultiplier;
	}

	public String getDescription() {

		return description;
	}

}
