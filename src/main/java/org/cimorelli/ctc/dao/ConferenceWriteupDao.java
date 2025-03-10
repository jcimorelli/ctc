package org.cimorelli.ctc.dao;

import javax.persistence.NoResultException;

import org.cimorelli.ctc.dbo.ConferenceWriteup;

public class ConferenceWriteupDao extends BaseDao {

	public ConferenceWriteupDao() {

		init();
	}

	public ConferenceWriteup findById( int id ) {

		return em.find( ConferenceWriteup.class, id );
	}

	public ConferenceWriteup findByConferenceAndYear( int conferenceId, int poolYear ) {

		try {
			return em.createQuery(
					"SELECT cw FROM ConferenceWriteup cw WHERE cw.conferenceId = :conferenceId AND cw.poolYear = :poolYear",
					ConferenceWriteup.class )
				.setParameter( "conferenceId", conferenceId )
				.setParameter( "poolYear", poolYear )
				.getSingleResult();
		} catch( NoResultException e ) {
			return null;
		}
	}
}
