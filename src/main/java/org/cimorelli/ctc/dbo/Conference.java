package org.cimorelli.ctc.dbo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Conferences")
public class Conference extends BaseEntity{

	@Id
	private int conferenceId;
	private String conferenceName;

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
}
