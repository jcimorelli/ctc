package org.cimorelli.ctc.dao;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.cimorelli.ctc.dbo.Conference;

public class ConferenceDao extends BaseDao {

	public Conference findByCtcName( String name ) {

		Map<String, Object> params = new HashMap<>();
		params.put( "name", name );
		return getSingleResult( "SELECT c FROM Conference c WHERE c.ctcName = :name", Conference.class, params );
	}

	public List<Conference> findAll() {

		List<Conference> conferences = getResultList( "SELECT c FROM Conference c", Conference.class );
		return conferences.stream()
			.sorted( Comparator.comparing( Conference::getCtcName ) )
			.collect( Collectors.toList() );
	}

	public List<String> findAllCtcNames() {

		List<String> ctcNames = getResultList( "SELECT c.ctcName FROM Conference c", String.class );
		return ctcNames.stream().sorted().collect( Collectors.toList() );
	}

}
