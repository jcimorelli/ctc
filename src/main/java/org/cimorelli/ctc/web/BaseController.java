package org.cimorelli.ctc.web;

import static spark.Spark.halt;

import org.cimorelli.ctc.dao.ConferenceDao;
import org.cimorelli.ctc.dao.ConferenceWriteupDao;
import org.cimorelli.ctc.dao.PickDao;
import org.cimorelli.ctc.dao.ResultDao;
import org.cimorelli.ctc.dao.UserDao;

import spark.Request;
import spark.Response;

public abstract class BaseController {

	protected ConferenceDao conferenceDao = new ConferenceDao();
	protected ConferenceWriteupDao conferenceWriteupDao = new ConferenceWriteupDao();
	protected PickDao pickDao = new PickDao();
	protected ResultDao resultDao = new ResultDao();
	protected UserDao userDao = new UserDao();

	protected void requireLogin( Request req, Response res ) {

		if( req.session().attribute( "username" ) == null ) {
			res.redirect( "/login" );
			halt();
		}
	}
}
