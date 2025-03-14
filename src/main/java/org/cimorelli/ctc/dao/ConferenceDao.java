package org.cimorelli.ctc.dao;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;

import org.cimorelli.ctc.dbo.Conference;

public class ConferenceDao extends BaseDao {

	public ConferenceDao() {

		init();
	}

	public Conference findById( int id ) {

		return em.find( Conference.class, id );
	}

	public Conference findByCtcName( String name ) {

		try {
			return em.createQuery( "SELECT c FROM Conference c WHERE c.ctcName = :name", Conference.class )
				.setParameter( "name", name )
				.getSingleResult();
		} catch( NoResultException e ) {
			return null;
		}
	}

	public List<Conference> findAll() {

		List<Conference> conferences = em.createQuery( "SELECT c FROM Conference c", Conference.class ).getResultList();
		return conferences.stream()
			.sorted( Comparator.comparing( Conference::getCtcName ) )
			.collect( Collectors.toList() );
	}

	public List<String> findAllCtcNames() {

		List<String> ctcNames = em.createQuery( "SELECT c.ctcName FROM Conference c", String.class ).getResultList();
		return ctcNames.stream().sorted().collect( Collectors.toList() );
	}

}
