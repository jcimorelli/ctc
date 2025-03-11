package org.cimorelli.ctc.dao;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;

import org.cimorelli.ctc.dbo.Entrant;

public class EntrantDao extends BaseDao {

	public EntrantDao() {

		init();
	}

	public Entrant findById( int id ) {

		return em.find( Entrant.class, id );
	}

	public Entrant findByNickname( String nickname ) {

		try {
			return em.createQuery( "SELECT e FROM Entrant e WHERE e.nickname = :nickname", Entrant.class )
				.setParameter( "nickname", nickname )
				.getSingleResult();
		} catch( NoResultException e ) {
			return null;
		}
	}

	public List<Entrant> findAll() {

		List<Entrant> entrants = em.createQuery( "SELECT e FROM Entrant e", Entrant.class ).getResultList();
		return entrants.stream()
			.sorted( Comparator.comparing( Entrant::getNickname ) )
			.collect( Collectors.toList() );
	}

}
