package com.htc.fdapp.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.Instant;

import com.htc.fdapp.bo.Database;
import com.htc.fdapp.bo.FaceFinder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ApplicationController {
	@FXML
	private Button button;
	
	@FXML
	private ImageView frame;
	
	ImageView imageView1;
	public String filePath="./faces";
	FaceFinder faceFinder = new FaceFinder();
	Database database = new Database();	
	public boolean isDBready = false;
	public static ObservableList<String> event = FXCollections.observableArrayList();
	@FXML
	public void launchFaceDetector(ActionEvent event) throws Exception {
		
		Stage primaryStage = new Stage();
		BorderPane root = FXMLLoader.load(getClass().getResource("/com/htc/fdapp/cfgs/FaceDetect.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Self Service Application");
		primaryStage.setScene(scene);
		primaryStage.show();
		this.startCamera();
		
	}
	
	public void putOnLog(String data) {

		Instant now = Instant.now();

		String logs = now.toString() + ":\n" + data;

		event.add(logs);

		//logList.setItems(event);

	}
	
	public void startCamera() throws SQLException {
		
		faceFinder.init();

		faceFinder.setFrame(frame);

		faceFinder.start();

		/*if (!database.init()) {

			putOnLog("Error: Database Connection Failed ! ");

		} else {
			isDBready = true;
			putOnLog("Success: Database Connection Succesful ! ");
		}
*/
	
		
		String path = filePath;

		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		
		//Image reader from the mentioned folder
		for (final File file : listOfFiles) {

			imageView1 = createImageView(file);
			//tile.getChildren().addAll(imageView1);
		}
		putOnLog(" Real Time WebCam Stream Started !");
		
		//**********************************************************************************************
		
	}
	
	private ImageView createImageView(final File imageFile) {

		try {
			final Image img = new Image(new FileInputStream(imageFile), 120, 0, true, true);
			imageView1 = new ImageView(img);

			imageView1.setStyle("-fx-background-color: BLACK");
			imageView1.setFitHeight(120);

			imageView1.setPreserveRatio(true);
			imageView1.setSmooth(true);
			imageView1.setCache(true);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return imageView1;
	}
	
}
