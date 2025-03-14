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
	private String ctcName;
	private String formalName;
	private String abbreviation;

	public int getConferenceId() {

		return conferenceId;
	}

	public void setConferenceId( int conferenceId ) {

		this.conferenceId = conferenceId;
	}

	public String getCtcName() {

		return ctcName;
	}

	public void setCtcName( String ctcName ) {

		this.ctcName = ctcName;
	}

	public String getFormalName() {

		return formalName;
	}

	public void setFormalName( String formalName ) {

		this.formalName = formalName;
	}

	public String getAbbreviation() {

		return abbreviation;
	}

	public void setAbbreviation( String abbreviation ) {

		this.abbreviation = abbreviation;
	}
}
