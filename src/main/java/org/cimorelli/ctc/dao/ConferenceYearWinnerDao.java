package org.cimorelli.ctc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cimorelli.ctc.dbo.ConferenceYearWinner;

public class ConferenceYearWinnerDao extends BaseDao {

	public List<ConferenceYearWinner> findByConferenceYear( int conferenceYearId ) {

		Map<String, Object> params = new HashMap<>();
		params.put( "conferenceYearId", conferenceYearId );
		return getResultList( "SELECT c FROM ConferenceYearWinner c WHERE c.conferenceYearId = :conferenceYearId", ConferenceYearWinner.class, params );
	}
}
