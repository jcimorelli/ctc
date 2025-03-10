package org.cimorelli.ctc.controllers;

import org.cimorelli.ctc.dao.ConferenceDao;
import org.cimorelli.ctc.dao.ConferenceWriteupDao;
import org.cimorelli.ctc.dao.PickDao;
import org.cimorelli.ctc.dao.ResultDao;
import org.cimorelli.ctc.dao.UserDao;

import javafx.scene.control.Alert;

public class BaseController {

	protected ConferenceDao conferenceDao;
	protected ConferenceWriteupDao conferenceWriteupDao;
	protected PickDao pickDao;
	protected ResultDao resultDao;
	protected UserDao userDao;

	protected void init() {

		conferenceDao = new ConferenceDao();
		conferenceWriteupDao = new ConferenceWriteupDao();
		pickDao = new PickDao();
		resultDao = new ResultDao();
		userDao = new UserDao();
	}

	protected void showError( String message ) {

		Alert alert = new Alert( Alert.AlertType.ERROR );
		alert.setContentText( message );
		alert.showAndWait();
	}
}
