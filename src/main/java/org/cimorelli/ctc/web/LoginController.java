package org.cimorelli.ctc.web;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.HashMap;
import java.util.Map;

import org.cimorelli.ctc.dbo.User;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.freemarker.FreeMarkerEngine;

public class LoginController extends BaseController {

	public static void setup( FreeMarkerEngine freeMarker ) {

		LoginController loginController = new LoginController();
		get( "/", loginController::redirectToLogin, freeMarker );
		get( "/login", loginController::showLogin, freeMarker );
		post( "/login", loginController::processLogin, freeMarker );
	}

	public ModelAndView redirectToLogin( Request req, Response res ) {

		res.redirect( "/login" );
		return null;
	}

	public ModelAndView showLogin( Request req, Response res ) {

		Map<String, Object> model = new HashMap<>();
		updateAlerts( req, model );
		return new ModelAndView( model, "login.ftl" );
	}

	public ModelAndView processLogin( Request req, Response res ) {

		String username = req.queryParams( "username" );
		String password = req.queryParams( "password" );

		if( authenticate( username, password ) ) {
			// After successful login, redirect to main menu.
			req.session( true ).attribute( "username", username );
			res.redirect( "/home" );
		} else {
			displayError( req, "Login Failed!" );
			res.redirect( "/login" );
		}
		return null;
	}

	private boolean authenticate( String username, String password ) {

		User user = userDao.findByUserName( username );
		return user != null && user.getPassword().equals( password );
	}
}



