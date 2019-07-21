package main;

import ModuleChooserController.ModuleChooserController;
import javafx.application.Application;


import javafx.scene.Scene;

import javafx.stage.Stage;
import model.StudentProfile;
import view.ModuleChooserRootPane;


public class ApplicationLoader extends Application {

	private ModuleChooserRootPane view;
	
	@Override
	public void init() {
		//create model and view 
		StudentProfile model = new StudentProfile();
		view = new ModuleChooserRootPane();
		new ModuleChooserController(view, model);	
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setMinWidth(700); 
		stage.setMinHeight(900);
		stage.setTitle("Final Year Module Chooser Tool");
		stage.setScene(new Scene(view));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}