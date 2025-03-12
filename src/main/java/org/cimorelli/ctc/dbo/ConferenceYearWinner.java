package org.cimorelli.ctc.dbo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ConferenceYearWinner")
public class ConferenceYearWinner extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int conferenceYearWinnerId;
	private int conferenceYearId;
	private int entrantId;

	public int getConferenceYearWinnerId() {

		return conferenceYearWinnerId;
	}

	public void setConferenceYearWinnerId( int conferenceYearWinnerId ) {

		this.conferenceYearWinnerId = conferenceYearWinnerId;
	}

	public int getConferenceYearId() {

		return conferenceYearId;
	}

	public void setConferenceYearId( int conferenceYearId ) {

		this.conferenceYearId = conferenceYearId;
	}

	public int getEntrantId() {

		return entrantId;
	}

	public void setEntrantId( int entrantId ) {

		this.entrantId = entrantId;
	}

}
