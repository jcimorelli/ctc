package org.cimorelli.ctc.dbo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ConferenceYear")
public class ConferenceYear extends BaseEntity {

	@Id
	private int conferenceYearId;
	private int conferenceId;
	private int poolYear;
	private BigDecimal multiplier;

	public int getConferenceYearId() {

		return conferenceYearId;
	}

	public void setConferenceYearId( int conferenceYearId ) {

		this.conferenceYearId = conferenceYearId;
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

	public BigDecimal getMultiplier() {

		return multiplier;
	}

	public void setMultiplier( BigDecimal multiplier ) {

		this.multiplier = multiplier;
	}
}
