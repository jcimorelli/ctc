package org.cimorelli.ctc.dao;

import java.util.List;

import org.cimorelli.ctc.dbo.Result;

public class ResultDao extends BaseDao {

	public ResultDao() {

		init();
	}

	public Result findById( int id ) {

		return em.find( Result.class, id );
	}

	public List<Result> findByConferenceAndYear( int conferenceId, int poolYear ) {

		return em.createQuery(
				"SELECT r FROM Result r WHERE r.conferenceId = :conferenceId AND r.poolYear = :poolYear",
				Result.class )
			.setParameter( "conferenceId", conferenceId )
			.setParameter( "poolYear", poolYear )
			.getResultList();
	}

	public List<Result> findByYear( int poolYear ) {

		return em.createQuery(
				"SELECT r FROM Result r WHERE r.poolYear = :poolYear ORDER BY r.submittedTime DESC",
				Result.class )
			.setParameter( "poolYear", poolYear )
			.getResultList();
	}
}
