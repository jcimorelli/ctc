package org.cimorelli.ctc.web;

import java.util.Map;

import org.cimorelli.ctc.dao.ConferenceDao;
import org.cimorelli.ctc.dao.ConferenceWriteupDao;
import org.cimorelli.ctc.dao.ConferenceYearDao;
import org.cimorelli.ctc.dao.ConferenceYearWinnerDao;
import org.cimorelli.ctc.dao.EntrantDao;
import org.cimorelli.ctc.dao.PickDao;
import org.cimorelli.ctc.dao.ResultDao;

import spark.Request;

public abstract class BaseController {

	protected ConferenceDao conferenceDao = new ConferenceDao();
	protected ConferenceWriteupDao conferenceWriteupDao = new ConferenceWriteupDao();
	protected ConferenceYearDao conferenceYearDao = new ConferenceYearDao();
	protected ConferenceYearWinnerDao conferenceYearWinnerDao = new ConferenceYearWinnerDao();
	protected PickDao pickDao = new PickDao();
	protected ResultDao resultDao = new ResultDao();
	protected EntrantDao entrantDao = new EntrantDao();

	protected void updateAlerts( Request req, Map<String, Object> model ) {

		model.put( "errorMessage", req.session().attribute( "errorMessage" ) );
		model.put( "warningMessage", req.session().attribute( "confirmMessage" ) );
		model.put( "confirmMessage", req.session().attribute( "confirmMessage" ) );
		req.session().removeAttribute( "errorMessage" );
		req.session().removeAttribute( "warningMessage" );
		req.session().removeAttribute( "confirmMessage" );
	}

	protected void displayError( Request req, String message ) {

		req.session( true ).attribute( "errorMessage", message );
	}

	protected void displayWarning( Request req, String message ) {

		req.session( true ).attribute( "warningMessage", message );
	}

	protected void displayConfirmation( Request req, String message ) {

		req.session( true ).attribute( "confirmMessage", message );
	}
}
