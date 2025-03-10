package org.cimorelli.ctc.dbo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User extends BaseEntity {

	@Id
	private int userId;
	private String userName;
	private String password;
	private LocalDateTime createdAt;

	public int getUserId() {

		return userId;
	}

	public void setUserId( int userId ) {

		this.userId = userId;
	}

	public String getUserName() {

		return userName;
	}

	public void setUserName( String userName ) {

		this.userName = userName;
	}

	public String getPassword() {

		return password;
	}

	public void setPassword( String password ) {

		this.password = password;
	}

	public LocalDateTime getCreatedAt() {

		return createdAt;
	}

	public void setCreatedAt( LocalDateTime createdAt ) {

		this.createdAt = createdAt;
	}
}
