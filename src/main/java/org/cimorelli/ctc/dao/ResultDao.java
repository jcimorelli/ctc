package org.cimorelli.ctc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cimorelli.ctc.dbo.Result;

public class ResultDao extends BaseDao {

	public List<Result> findByConferenceAndYear( int conferenceId, int poolYear ) {

		Map<String, Object> params = new HashMap<>();
		params.put( "conferenceId", conferenceId );
		params.put( "poolYear", poolYear );
		return getResultList( "SELECT r FROM Result r WHERE r.conferenceId = :conferenceId AND r.poolYear = :poolYear",
							  Result.class, params );
	}

	public List<Result> findByYear( int poolYear ) {

		Map<String, Object> params = new HashMap<>();
		params.put( "poolYear", poolYear );
		return getResultList( "SELECT r FROM Result r WHERE r.poolYear = :poolYear ORDER BY r.submittedTime DESC",
							  Result.class, params );
	}
}
