package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCombination;


public final class ModuleChooserMenuBar extends MenuBar {

	private final MenuItem loadStudentData, saveStudentData, exit, Info;

	
	public ModuleChooserMenuBar() {

		Menu menu;

		
		menu = new Menu("Open");

		loadStudentData = new MenuItem("_Load Student Data");
		loadStudentData.setAccelerator(KeyCombination.keyCombination("SHORTCUT+L"));
		menu.getItems().add(loadStudentData);

		saveStudentData = new MenuItem("Save Student Data");
		saveStudentData.setAccelerator(KeyCombination.keyCombination("SHORTCUT+S"));
		menu.getItems().add(saveStudentData);

	
		menu.getItems().add(new SeparatorMenuItem());

		exit = new MenuItem("Exit");
		exit.setAccelerator(KeyCombination.keyCombination("SHORTCUT+X"));
		menu.getItems().add(exit);

		

		this.getMenus().add(menu);

		
		menu = new Menu("Help");

	
		Info = new MenuItem("Info");
		Info.setAccelerator(KeyCombination.keyCombination("SHORTCUT+I"));
		menu.getItems().add(Info);

		this.getMenus().add(menu);

	}

	
	public void addSaveProfileHandler(EventHandler<ActionEvent> handler) {
		saveStudentData.setOnAction(handler);
	}

	
	public void addLoadStudentDataHandler(EventHandler<ActionEvent> handler) {
		loadStudentData.setOnAction(handler);
	}

	
	public void addAboutHandler(EventHandler<ActionEvent> handler) {
		Info.setOnAction(handler);
	}

	
	public void addExitHandler(EventHandler<ActionEvent> handler) {
		exit.setOnAction(handler);
	}
}