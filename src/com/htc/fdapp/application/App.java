package com.htc.fdapp.application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class App extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		
			
			Parent root = FXMLLoader.load(getClass().getResource("/com/htc/fdapp/cfgs/App.fxml"));
			Scene scene = new Scene(root);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			//primaryStage.getScene().addEventFilter(OnClick, arg1);
			//((Node)(event.getSource())).getScene().getWindow().hide();

		
	}
	


	public static void main(String[] args) {
		launch(args);
	}
}
