package org.cimorelli.ctc.dao;

import javax.persistence.NoResultException;

import org.cimorelli.ctc.dbo.User;

public class UserDao extends BaseDao {

	public UserDao() {

		init();
	}

	public User findById( int id ) {

		return em.find( User.class, id );
	}

	public User findByUserName( String userName ) {

		try {
			return em.createQuery( "SELECT u FROM User u WHERE u.userName = :userName", User.class )
				.setParameter( "userName", userName )
				.getSingleResult();
		} catch( NoResultException e ) {
			return null;
		}
	}

}
