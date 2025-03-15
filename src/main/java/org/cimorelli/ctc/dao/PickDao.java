package org.cimorelli.ctc.dao;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;

import org.cimorelli.ctc.dbo.Conference;
import org.cimorelli.ctc.dbo.Pick;

public class PickDao extends BaseDao {

	public PickDao() {

		init();
	}

	public Pick findById( int id ) {

		return em.find( Pick.class, id );
	}

	public List<Pick> findByEntrantIdAndYear( int entrantId, int poolYear ) {

		return em.createQuery( "SELECT p FROM Pick p WHERE p.entrantId = :entrantId AND p.poolYear = :poolYear", Pick.class )
			.setParameter( "entrantId", entrantId )
			.setParameter( "poolYear", poolYear )
			.getResultList();
	}

	public List<Pick> findByConferenceAndYear( int conferenceId, int poolYear ) {

		return em.createQuery( "SELECT p FROM Pick p WHERE p.conferenceId = :conferenceId AND p.poolYear = :poolYear", Pick.class )
			.setParameter( "conferenceId", conferenceId )
			.setParameter( "poolYear", poolYear )
			.getResultList();
	}

	public List<Pick> findByTeamAndYear( String teamName, int poolYear ) {

		return em.createQuery( "SELECT p FROM Pick p WHERE p.teamName = :teamName AND p.poolYear = :poolYear", Pick.class )
			.setParameter( "teamName", teamName )
			.setParameter( "poolYear", poolYear )
			.getResultList();
	}

	public List<Pick> findByEntrantAndConferenceAndYear( int entrantId, int conferenceId, int poolYear ) {

		return em.createQuery( "SELECT p FROM Pick p WHERE p.entrantId = :entrantId AND p.conferenceId = :conferenceId AND p.poolYear = :poolYear", Pick.class )
			.setParameter( "entrantId", entrantId )
			.setParameter( "conferenceId", conferenceId )
			.setParameter( "poolYear", poolYear )
			.getResultList();
	}

	public List<String> findTeamsByYear( int poolYear ) {

		List<String> teamNames = em.createQuery( "SELECT DISTINCT p.teamName FROM Pick p WHERE p.poolYear = :poolYear", String.class )
			.setParameter( "poolYear", poolYear )
			.getResultList();
		return teamNames.stream().sorted().collect( Collectors.toList() );
	}

	public List<Pick> findByFilters( int entrantId, int conferenceId, int poolYear, String teamName ) {

		StringBuilder sb = new StringBuilder();
		sb.append( "SELECT p FROM Pick p WHERE p.poolYear = :poolYear " );
		if( entrantId > 0 ) {
			sb.append( "AND p.entrantId = :entrantId " );
		}
		if( conferenceId > 0 ) {
			sb.append( "AND p.conferenceId = :conferenceId " );
		}
		if( teamName != null && !teamName.isEmpty() ) {
			sb.append( "AND p.teamName = :teamName " );
		}
		TypedQuery<Pick> query = em.createQuery( sb.toString(), Pick.class );
		if( entrantId > 0 ) {
			query.setParameter( "entrantId", entrantId );
		}
		if( conferenceId > 0 ) {
			query.setParameter( "conferenceId", conferenceId );
		}
		if( teamName != null && !teamName.isEmpty() ) {
			query.setParameter( "teamName", teamName );
		}
		query.setParameter( "poolYear", poolYear );
		return query.getResultList();
	}
}
