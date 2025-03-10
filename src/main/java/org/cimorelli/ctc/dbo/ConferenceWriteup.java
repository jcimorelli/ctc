package org.cimorelli.ctc.dbo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ConferenceWriteups")
public class ConferenceWriteup extends BaseEntity {

	@Id
	private int conferenceWriteupId;
	private int conferenceId;
	private int poolYear;
	private String markdownText;

	public int getConferenceWriteupId() {

		return conferenceWriteupId;
	}

	public void setConferenceWriteupId( int conferenceWriteupId ) {

		this.conferenceWriteupId = conferenceWriteupId;
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

	public String getMarkdownText() {

		return markdownText;
	}

	public void setMarkdownText( String markdownText ) {

		this.markdownText = markdownText;
	}
}
