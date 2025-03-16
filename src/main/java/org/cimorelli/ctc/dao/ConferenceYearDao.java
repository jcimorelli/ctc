package org.cimorelli.ctc.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cimorelli.ctc.dbo.ConferenceYear;

public class ConferenceYearDao extends BaseDao {

	public Integer findCurrentYear() {

		return getSingleResult( "SELECT MAX(cy.poolYear) FROM ConferenceYear cy", Integer.class );
	}

	public List<ConferenceYear> findAll() {

		return getResultList( "SELECT cy FROM ConferenceYear cy", ConferenceYear.class );
	}

	public ConferenceYear findByConferenceIdAndYear( int conferenceId, int poolYear ) {

		Map<String, Object> params = new HashMap<>();
		params.put( "conferenceId", conferenceId );
		params.put( "poolYear", poolYear );
		return getSingleResult( "SELECT cy FROM ConferenceYear cy WHERE cy.conferenceId = :conferenceId AND cy.poolYear = :poolYear",
								ConferenceYear.class, params );
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
