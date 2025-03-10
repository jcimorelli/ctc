package org.cimorelli.ctc.dao;

import java.util.List;

import org.cimorelli.ctc.dbo.Pick;

public class PickDao extends BaseDao {

	public PickDao() {

		init();
	}

	public Pick findById( int id ) {

		return em.find( Pick.class, id );
	}

	public List<Pick> findByUser( int userId ) {

		return em.createQuery( "SELECT p FROM Pick p WHERE p.userId = :userId", Pick.class )
			.setParameter( "userId", userId )
			.getResultList();
	}

	public List<Pick> findByConferenceAndYear( int conferenceId, int poolYear ) {

		return em.createQuery( "SELECT p FROM Pick p WHERE p.conferenceId = :conferenceId AND p.poolYear = :poolYear", Pick.class )
			.setParameter( "conferenceId", conferenceId )
			.setParameter( "poolYear", poolYear )
			.getResultList();
	}

}
