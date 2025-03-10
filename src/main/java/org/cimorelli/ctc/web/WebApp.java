package org.cimorelli.ctc.web;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public class WebApp {

	public static void main(String[] args) {
		// Set the server port (default is 4567)
		port(4567);

		// Serve static files from the /public directory in resources
		staticFiles.location("/public");

		// Initialize the FreeMarker configuration
		Configuration freeMarkerConfig = new Configuration( Configuration.VERSION_2_3_31);
		freeMarkerConfig.setClassForTemplateLoading(WebApp.class, "/templates");
		freeMarkerConfig.setDefaultEncoding("UTF-8");
		freeMarkerConfig.setTemplateExceptionHandler( TemplateExceptionHandler.RETHROW_HANDLER);

		// Initialize the FreeMarker template engine with the configuration
		FreeMarkerEngine freeMarker = new FreeMarkerEngine(freeMarkerConfig);

		// Print exceptions to the console
		exception(Exception.class, (e, request, response) -> {
			e.printStackTrace();
		});

		// Root Re-Route to Login
		get("/", (req, res) -> {
			res.redirect("/login");
			return null;
		});

		// Route: Display the login page
		get("/login", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return new ModelAndView(model, "login.ftl");
		}, freeMarker);

		// Route: Handle login submission
		post("/login", (req, res) -> {
			String username = req.queryParams("username");
			String password = req.queryParams("password");

			// Add your authentication logic here.
			// For example, check the credentials against your database.
			boolean validUser = authenticate(username, password);

			if (validUser) {
				// After successful login, redirect to main menu.
				res.redirect("/mainMenu");
			} else {
				res.redirect("/login");
			}
			return null;
		});

		// Route: Main Menu
		get("/mainMenu", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			// You might want to pass user info in the model if needed.
			return new ModelAndView(model, "mainMenu.ftl");
		}, freeMarker);

		// Add additional routes for conference write-ups, leaderboard, etc.
	}


	private static boolean authenticate( String username, String password ) {
		// Replace with actual authentication logic
		return "user".equals( username ) && "pass".equals( password );
	}
}
