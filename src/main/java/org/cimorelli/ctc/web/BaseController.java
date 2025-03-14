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

	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String WARNING_MESSAGE = "warningMessage";
	private static final String CONFIRM_MESSAGE = "confirmMessage";

	protected ConferenceDao conferenceDao = new ConferenceDao();
	protected ConferenceWriteupDao conferenceWriteupDao = new ConferenceWriteupDao();
	protected ConferenceYearDao conferenceYearDao = new ConferenceYearDao();
	protected ConferenceYearWinnerDao conferenceYearWinnerDao = new ConferenceYearWinnerDao();
	protected PickDao pickDao = new PickDao();
	protected ResultDao resultDao = new ResultDao();
	protected EntrantDao entrantDao = new EntrantDao();

	protected void updateAlerts( Request req, Map<String, Object> model ) {

		model.put( ERROR_MESSAGE, req.session().attribute( ERROR_MESSAGE ) );
		model.put( WARNING_MESSAGE, req.session().attribute( WARNING_MESSAGE ) );
		model.put( CONFIRM_MESSAGE, req.session().attribute( CONFIRM_MESSAGE ) );
		req.session().removeAttribute( ERROR_MESSAGE );
		req.session().removeAttribute( WARNING_MESSAGE );
		req.session().removeAttribute( CONFIRM_MESSAGE );
	}

	protected void displayError( Request req, String message ) {

		req.session( true ).attribute( ERROR_MESSAGE, message );
	}

	protected void displayWarning( Request req, String message ) {

		req.session( true ).attribute( WARNING_MESSAGE, message );
	}

	protected void displayConfirmation( Request req, String message ) {

		req.session( true ).attribute( CONFIRM_MESSAGE, message );
	}
}
