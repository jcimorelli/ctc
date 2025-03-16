package org.cimorelli.ctc.dao;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.cimorelli.ctc.dbo.Pick;

public class PickDao extends BaseDao {

	public List<Pick> findByEntrantIdAndYear( int entrantId, int poolYear ) {

		Map<String, Object> params = new HashMap<>();
		params.put( "entrantId", entrantId );
		params.put( "poolYear", poolYear );
		return getResultList( "SELECT p FROM Pick p WHERE p.entrantId = :entrantId AND p.poolYear = :poolYear", Pick.class, params );
	}

	public List<Pick> findByConferenceAndYear( int conferenceId, int poolYear ) {

		Map<String, Object> params = new HashMap<>();
		params.put( "conferenceId", conferenceId );
		params.put( "poolYear", poolYear );
		return getResultList( "SELECT p FROM Pick p WHERE p.conferenceId = :conferenceId AND p.poolYear = :poolYear", Pick.class, params );
	}

	public List<Pick> findByTeamAndYear( String teamName, int poolYear ) {

		Map<String, Object> params = new HashMap<>();
		params.put( "teamName", teamName );
		params.put( "poolYear", poolYear );
		return getResultList( "SELECT p FROM Pick p WHERE p.teamName = :teamName AND p.poolYear = :poolYear", Pick.class, params );
	}

	public List<Pick> findByEntrantAndConferenceAndYear( int entrantId, int conferenceId, int poolYear ) {

		Map<String, Object> params = new HashMap<>();
		params.put( "entrantId", entrantId );
		params.put( "conferenceId", conferenceId );
		params.put( "poolYear", poolYear );
		return getResultList( "SELECT p FROM Pick p WHERE p.entrantId = :entrantId AND p.conferenceId = :conferenceId AND p.poolYear = :poolYear", Pick.class, params );
	}

	public List<String> findTeamsByYear( int poolYear ) {

		Map<String, Object> params = new HashMap<>();
		params.put( "poolYear", poolYear );
		List<String> teamNames = getResultList( "SELECT DISTINCT p.teamName FROM Pick p WHERE p.poolYear = :poolYear", String.class, params );
		return teamNames.stream().sorted().collect( Collectors.toList() );
	}

	public List<Pick> findByFilters( int entrantId, int conferenceId, int poolYear, String teamName, LocalDate gameDate ) {

		StringBuilder sb = new StringBuilder();
		Map<String, Object> params = new HashMap<>();
		sb.append( "SELECT p FROM Pick p " );
		if( gameDate != null ) {
			sb.append( "JOIN Result r ON p.resultId = r.resultId " );
		}
		sb.append( "WHERE p.poolYear = :poolYear " );
		params.put( "poolYear", poolYear );
		if( entrantId > 0 ) {
			sb.append( "AND p.entrantId = :entrantId " );
			params.put( "entrantId", entrantId );
		}
		if( conferenceId > 0 ) {
			sb.append( "AND p.conferenceId = :conferenceId " );
			params.put( "conferenceId", conferenceId );
		}
		if( teamName != null && !teamName.isEmpty() ) {
			sb.append( "AND p.teamName = :teamName " );
			params.put( "teamName", teamName );
		}
		if( gameDate != null ) {
			sb.append( "AND r.gameDate = :gameDate " );
			params.put( "gameDate", gameDate );
		}
		return getResultList( sb.toString(), Pick.class, params );
	}

	public List<Pick> findByResult( int resultId ) {

		Map<String, Object> params = new HashMap<>();
		params.put( "resultId", resultId );
		return getResultList( "SELECT p FROM Pick p WHERE p.resultId = :resultId", Pick.class, params );
	}

	public List<String> findDistinctGameDaysByYear( int currentPoolYear ) {

		Map<String, Object> params = new HashMap<>();
		params.put( "poolYear", currentPoolYear );
		return getResultList( "SELECT DISTINCT r.gameDate FROM Pick p " +
							  "JOIN Result r ON p.resultId = r.resultId " +
							  "WHERE p.poolYear = :poolYear", String.class, params );
	}
}
