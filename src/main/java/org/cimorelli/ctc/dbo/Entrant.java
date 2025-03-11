package org.cimorelli.ctc.dbo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Entrants")
public class Entrant extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int entrantId;
	private String nickname;
	private boolean playingForFun;

	public int getEntrantId() {

		return entrantId;
	}

	public void setEntrantId( int entrantId ) {

		this.entrantId = entrantId;
	}

	public String getNickname() {

		return nickname;
	}

	public void setNickname( String nickname ) {

		this.nickname = nickname;
	}

	public boolean isPlayingForFun() {

		return playingForFun;
	}

	public void setPlayingForFun( boolean playingForFun ) {

		this.playingForFun = playingForFun;
	}
}
