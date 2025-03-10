package org.cimorelli.ctc.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import org.cimorelli.ctc.dbo.User;

public class MainMenuController extends BaseController {

	private User currentUser;

	@FXML
	private BorderPane rootPane;

	@FXML
	protected void init() {

		super.init();
	}

	/**
	 * Sets the current logged-in user.
	 */
	public void setCurrentUser( User user ) {

		this.currentUser = user;
	}

	@FXML
	private void onConferenceWriteupsClicked() {

		loadView( "/fxml/ConferenceWriteup.fxml" );
	}

	@FXML
	private void onLeaderboardClicked() {

		loadView( "/fxml/Leaderboard.fxml" );
	}

	@FXML
	private void onPickEntryClicked() {

		loadView( "/fxml/PickEntry.fxml" );
	}

	@FXML
	private void onResultsEntryClicked() {

		loadView( "/fxml/ResultsEntry.fxml" );
	}

	/**
	 * Loads the FXML view specified by fxmlPath and sets it to the center of the rootPane.
	 * Optionally, you can pass the current user to the sub-controller if that controller
	 * defines a setCurrentUser(User user) method.
	 *
	 * @param fxmlPath the path to the FXML file to load.
	 */
	private void loadView( String fxmlPath ) {

		try {
			FXMLLoader loader = new FXMLLoader( getClass().getResource( fxmlPath ) );
			Pane pane = loader.load();

			// Optionally pass the currentUser to the sub-controller.
			// For example, if ConferenceWriteupController has a setCurrentUser method:
			// ((ConferenceWriteupController) loader.getController()).setCurrentUser(currentUser);

			rootPane.setCenter( pane );
		} catch( Exception e ) {
			e.printStackTrace();
			// Optionally, show an error dialog if the view fails to load.
		}
	}
}
