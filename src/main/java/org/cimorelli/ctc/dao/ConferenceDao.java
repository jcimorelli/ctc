package org.cimorelli.ctc.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.cimorelli.ctc.dbo.Conference;

public class ConferenceDao extends BaseDao {

	public ConferenceDao() {

		init();
	}

	public Conference findById( int id ) {

		return em.find( Conference.class, id );
	}

	public Conference findByName( String name ) {

		try {
			return em.createQuery( "SELECT c FROM Conference c WHERE c.conferenceName = :name", Conference.class )
				.setParameter( "name", name )
				.getSingleResult();
		} catch( NoResultException e ) {
			return null;
		}
	}

	public List<Conference> findAll() {

		return em.createQuery( "SELECT c FROM Conference c", Conference.class )
			.getResultList();
	}

}
