package org.cimorelli.ctc.dao;

import java.util.HashMap;
import java.util.Map;

import org.cimorelli.ctc.dbo.ConferenceWriteup;

public class ConferenceWriteupDao extends BaseDao {

	public ConferenceWriteup findByConferenceAndYear( int conferenceId, int poolYear ) {

		Map<String, Object> params = new HashMap<>();
		params.put( "conferenceId", conferenceId );
		params.put( "poolYear", poolYear );
		return getSingleResult( "SELECT cw FROM ConferenceWriteup cw WHERE cw.conferenceId = :conferenceId AND cw.poolYear = :poolYear",
								ConferenceWriteup.class, params );
	}
}
