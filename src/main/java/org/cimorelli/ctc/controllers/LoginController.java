package org.cimorelli.ctc.controllers;

import org.cimorelli.ctc.dbo.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController extends BaseController {

	@FXML
	private TextField userNameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	public void init() {

		super.init();
	}

	@FXML
	private void onLoginClicked() {

		String userName = userNameField.getText();
		String password = passwordField.getText();

		try {
			User user = userDao.findByUserName( userName );
			if( user != null && checkPassword( password, user.getPasswordHash() ) ) {
				// Login successful: load the main menu
				loadMainMenu( user );
			} else {
				showError( "Invalid username or password." );
			}
		} catch( Exception e ) {
			e.printStackTrace();
			showError( "An error occurred during login." );
		}
	}

	// Simple password check; replace with a secure hash check in production
	private boolean checkPassword( String rawPassword, String storedHash ) {

		return rawPassword.equals( storedHash );
	}

	// Loads the MainMenu scene and passes the logged-in user to it
	private void loadMainMenu( User user ) {

		try {
			FXMLLoader loader = new FXMLLoader( getClass().getResource( "/fxml/MainMenu.fxml" ) );
			Stage stage = ( Stage )userNameField.getScene().getWindow();
			Scene scene = new Scene( loader.load() );
			stage.setScene( scene );
			stage.setTitle( "Conference Tournament Challenge - Main Menu" );

			MainMenuController controller = loader.getController();
			controller.setCurrentUser( user );
		} catch( Exception e ) {
			e.printStackTrace();
			showError( "Failed to load main menu." );
		}
	}
}
