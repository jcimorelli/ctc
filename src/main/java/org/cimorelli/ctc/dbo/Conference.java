package org.cimorelli.ctc.dbo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Conferences")
public class Conference extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int conferenceId;
	private String conferenceName;
	private String abbreviation;

	public int getConferenceId() {

		return conferenceId;
	}

	public void setConferenceId( int conferenceId ) {

		this.conferenceId = conferenceId;
	}

	public String getConferenceName() {

		return conferenceName;
	}

	public void setConferenceName( String conferenceName ) {

		this.conferenceName = conferenceName;
	}

	public String getAbbreviation() {

		return abbreviation;
	}

	public void setAbbreviation( String abbreviation ) {

		this.abbreviation = abbreviation;
	}
}
