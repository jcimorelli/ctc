package org.cimorelli.ctc.app;

import org.cimorelli.ctc.util.DatabaseUtil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

	public static void main( String[] args ) {

		launch( args );
	}

	@Override
	public void init() throws Exception {

		System.out.println( "init" );
		DatabaseUtil.updateLiquibase();
	}

	@Override
	public void start( Stage primaryStage ) throws Exception {

		System.out.println( "start" );
		FXMLLoader loader = new FXMLLoader( getClass().getResource( "/fxml/Login.fxml" ) );
		Scene scene = new Scene( loader.load() );
		primaryStage.setScene( scene );
		primaryStage.setTitle( "Conference Tournament Challenge - Login" );
		primaryStage.show();
	}
}

