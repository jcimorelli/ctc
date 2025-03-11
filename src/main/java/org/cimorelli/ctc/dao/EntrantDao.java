package org.cimorelli.ctc.dao;

import javax.persistence.NoResultException;

import org.cimorelli.ctc.dbo.Entrant;

public class EntrantDao extends BaseDao {

	public EntrantDao() {

		init();
	}

	public Entrant findById( int id ) {

		return em.find( Entrant.class, id );
	}

	public Entrant findByNickname( String nickname ) {

		try {
			return em.createQuery( "SELECT e FROM Entrant e WHERE e.nickname = :nickname", Entrant.class )
				.setParameter( "nickname", nickname )
				.getSingleResult();
		} catch( NoResultException e ) {
			return null;
		}
	}

}
