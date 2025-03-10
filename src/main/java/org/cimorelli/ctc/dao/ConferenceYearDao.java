package org.cimorelli.ctc.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.cimorelli.ctc.dbo.ConferenceYear;

public class ConferenceYearDao extends BaseDao {

	public ConferenceYearDao() {

		init();
	}

	public ConferenceYear findById( int id ) {

		return em.find( ConferenceYear.class, id );
	}

	public Integer findCurrentYear() {

		TypedQuery<Integer> q = em.createQuery( "SELECT MAX(cy.poolYear) FROM ConferenceYear cy", Integer.class );
		return q.getSingleResult();
	}

	public List<ConferenceYear> findAll() {

		return em.createQuery( "SELECT cy FROM ConferenceYear cy", ConferenceYear.class )
			.getResultList();
	}

	public ConferenceYear findByConferenceIdAndYear( int conferenceId, int poolYear ) {

		try {
			return em.createQuery(
					"SELECT cy FROM ConferenceYear cy WHERE cy.conferenceId = :conferenceId AND cy.poolYear = :poolYear",
					ConferenceYear.class )
				.setParameter( "conferenceId", conferenceId )
				.setParameter( "poolYear", poolYear )
				.getSingleResult();
		} catch( NoResultException e ) {
			return null;
		}
	}

	public BigDecimal findMultiplier( int conferenceId ) {

		return findMultiplier( conferenceId, findCurrentYear() );
	}

	public BigDecimal findMultiplier( int conferenceId, int poolYear ) {

		ConferenceYear conferenceYear = findByConferenceIdAndYear( conferenceId, poolYear );
		if( conferenceYear == null ) {
			return null;
		}
		return conferenceYear.getMultiplier();
	}
}
