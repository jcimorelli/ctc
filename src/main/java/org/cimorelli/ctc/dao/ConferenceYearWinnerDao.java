package org.cimorelli.ctc.dao;

import java.util.List;

import org.cimorelli.ctc.dbo.ConferenceYearWinner;

public class ConferenceYearWinnerDao extends BaseDao {

	public ConferenceYearWinnerDao() {

		init();
	}

	public ConferenceYearWinner findById( int id ) {

		return em.find( ConferenceYearWinner.class, id );
	}

	public List<ConferenceYearWinner> findByConferenceYear( int conferenceYearId ) {

		return em.createQuery( "SELECT c FROM ConferenceYearWinner c WHERE c.conferenceYearId = :conferenceYearId", ConferenceYearWinner.class )
			.setParameter( "conferenceYearId", conferenceYearId )
			.getResultList();
	}
}
