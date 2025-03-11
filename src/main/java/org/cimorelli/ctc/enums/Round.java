package org.cimorelli.ctc.enums;

import java.math.BigDecimal;

public enum Round {
	R1( 1, 1, "Round 1", 1 ),
	R2( 1, 1, "Round 2", 2 ),
	R3( 1, 1, "Round 3", 3 ),
	R4( 1, 1, "Round 4", 4 ),
	OF( 2, 2, "Octofinal", 5 ),
	QF( 4, 2, "Quarterfinal", 6 ),
	SF( 8, 3, "Semifinal", 7 ),
	CHAMP( 12, 4, "Final", 8 );

	private final BigDecimal roundPoints;
	private final BigDecimal upsetMultiplier;
	private final String description;
	private final int order;

	Round( int roundPoints, int upsetMultiplier, String description, int order ) {

		this.roundPoints = BigDecimal.valueOf( roundPoints );
		this.upsetMultiplier = BigDecimal.valueOf( upsetMultiplier );
		this.description = description;
		this.order = order;
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

	public int getOrder() {

		return order;
	}

}
