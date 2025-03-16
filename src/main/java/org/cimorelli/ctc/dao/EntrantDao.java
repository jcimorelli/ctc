package org.cimorelli.ctc.dao;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.cimorelli.ctc.dbo.Entrant;

public class EntrantDao extends BaseDao {

	public List<Entrant> findById( List<Integer> idList ) {

		Map<String, Object> params = new HashMap<>();
		params.put( "idList", idList );
		return getResultList( "SELECT e FROM Entrant e WHERE e.entrantId IN :idList", Entrant.class, params );
	}

	public Entrant findByNickname( String nickname ) {

		Map<String, Object> params = new HashMap<>();
		params.put( "nickname", nickname );
		return getSingleResult( "SELECT e FROM Entrant e WHERE e.nickname = :nickname", Entrant.class, params );
	}

	public List<Entrant> findAll() {

		List<Entrant> entrants = getResultList( "SELECT e FROM Entrant e", Entrant.class );
		return entrants.stream()
			.sorted( Comparator.comparing( Entrant::getNickname ) )
			.collect( Collectors.toList() );
	}

	public List<Entrant> findWinnersByConferenceAndYear( int conferenceId, int poolYear ) {

		Map<String, Object> params = new HashMap<>();
		params.put( "conferenceId", conferenceId );
		params.put( "poolYear", poolYear );
		return getResultList( "SELECT e FROM Entrant e " +
							  "JOIN ConferenceYearWinner cyw ON cyw.entrantId = e.entrantId " +
							  "JOIN ConferenceYear cy ON cy.conferenceYearId = cyw.conferenceYearId " +
							  "WHERE cy.conferenceId = :conferenceId AND cy.poolYear = :poolYear", Entrant.class, params );
	}

}
