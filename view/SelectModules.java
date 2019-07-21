

package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Delivery;
import model.Module;

/**
 * The 'Select Modules Pane' pane.
 */
public final class SelectModules extends GridPane {

	private Label notification;
	private Button reset, submit, removeTerm1, addTerm1,  removeTerm2, addTerm2;
	private TextField currentTerm1Credits;
	private TextField currentTerm2Credits;
	private ListView<Module> unselectedTerm1Modules, unselectedTerm2Modules, selectedTerm1Modules, selectedTerm2Modules, SelectedYearLongModules;
	private ObservableList<Module> unselectedTerm1ModulesOS, unselectedTerm2ModulesOS, selectedTerm1ModulesOS, selectedTerm2ModulesOS, SelectedYearLongModulesOS;
    private int credits1 = 0;
    private int credits2 = 0;

	public SelectModules(){
		
		this.setAlignment(Pos.CENTER);
		
		//Observable list sets
		unselectedTerm1ModulesOS = FXCollections.observableArrayList();
		unselectedTerm2ModulesOS = FXCollections.observableArrayList();
		selectedTerm1ModulesOS = FXCollections.observableArrayList();
		selectedTerm2ModulesOS = FXCollections.observableArrayList();
		SelectedYearLongModulesOS = FXCollections.observableArrayList();
		
		//Setting List Views and adding Observable Lists
		unselectedTerm1Modules = new ListView<>(unselectedTerm1ModulesOS);
		unselectedTerm2Modules = new ListView<>(unselectedTerm2ModulesOS);
		selectedTerm1Modules = new ListView<>(selectedTerm1ModulesOS);
		selectedTerm2Modules = new ListView<>(selectedTerm2ModulesOS);
		SelectedYearLongModules = new ListView<>(SelectedYearLongModulesOS);

		//Resizing List Views
		unselectedTerm1Modules.setPrefSize(REMAINING, REMAINING);
		unselectedTerm2Modules.setPrefSize(REMAINING, REMAINING);
		selectedTerm1Modules.setPrefSize(REMAINING, REMAINING);
		selectedTerm2Modules.setPrefSize(REMAINING, REMAINING);
		SelectedYearLongModules.setPrefSize(REMAINING, REMAINING);
		
		
		// labels
		Label um1 = new Label("Unselected Term 1 Modules");
		Label um2 = new Label ("Unselected Term 2 Modules");
		Label sm1 = new Label("Selected Term 1 Modules");
		Label sm2 = new Label("Selected Term 2 Modules");
		Label syl = new Label("Selected Year Long Modules");
		Label ct1 = new Label("Current Term 1 Credits: ");
		Label ct2 = new Label("Current Term 2 Credits: ");
		notification = new Label("");


		notification.setPadding(new Insets(30, 0, 0, 120));
		notification.setTextFill(Color.web("#FF0000"));
		notification.setWrapText(true);
		
		this.add(notification, 1, 2);
		
		addTerm1 = new Button("Add");
		addTerm1.setPrefSize(80, 30);
		removeTerm1 = new Button("Remove");
		removeTerm1.setPrefSize(80, 30);
		HBox hbox1 = new HBox(addTerm1, removeTerm1);
		hbox1.setPadding(new Insets(20, 20, 20, 20));
		hbox1.setAlignment(Pos.CENTER);


		addTerm2 = new Button("Add");
		addTerm2.setPrefSize(80, 30);
		removeTerm2 = new Button("Remove");
		removeTerm2.setPrefSize(80, 30);
		HBox hbox2 = new HBox(addTerm2, removeTerm2);
		hbox2.setPadding(new Insets(20, 20, 20, 20));
		hbox2.setAlignment(Pos.CENTER);
		
		reset = new Button("Reset");
		reset.setPrefSize(80, 30);
		
		currentTerm1Credits = new TextField();
		currentTerm1Credits = new TextField();
		currentTerm1Credits.setText(Integer.toString(credits1));
		HBox c1Txt = new HBox(ct1, currentTerm1Credits);
		currentTerm1Credits.setEditable(false);
		currentTerm1Credits.setPrefSize(70, 30);
		c1Txt.setAlignment(Pos.CENTER);
	
		
		VBox unselectedModulesBox = new VBox();
		unselectedModulesBox.getChildren().addAll(um1, unselectedTerm1Modules,hbox1, um2, unselectedTerm2Modules, hbox2, reset, c1Txt);
		unselectedModulesBox.setPadding(new Insets(20, 20, 20, 20));
		this.add(unselectedModulesBox, 0, 0);
		
		submit = new Button("Submit");
		submit.setPrefSize(80, 30);
		
		currentTerm2Credits = new TextField();
		currentTerm2Credits.setText(Integer.toString(credits2));
		HBox c2Txt = new HBox(ct2, currentTerm2Credits);
		currentTerm2Credits.setEditable(false);
		currentTerm2Credits.setPrefSize(70, 30);
		c2Txt.setAlignment(Pos.CENTER);
		
		
		VBox selectedModulesBox = new VBox();
		selectedModulesBox.getChildren().addAll(syl, SelectedYearLongModules,sm1,  selectedTerm1Modules, sm2, selectedTerm2Modules, submit, c2Txt);
		selectedModulesBox.setPadding(new Insets(20, 20, 20, 20));
		this.add(selectedModulesBox, 1, 0);
		
	
	}


	public void addModulesToLists(Module m) {

		if(m.getRunPlan() == Delivery.TERM_1 && !m.isMandatory()) {
			unselectedTerm1ModulesOS.add(m);
		    }
		    else if (m.getRunPlan() == Delivery.TERM_1 && m.isMandatory()) {
		    	selectedTerm1ModulesOS.add(m);
		    	currentTerm1Credits.setText(Integer.toString(credits1 = credits1 + 15));
		    }
		    else if(m.getRunPlan() == Delivery.TERM_2) {
		    	if(m.isMandatory() == true) {
		    		selectedTerm2ModulesOS.add(m);
		    		currentTerm2Credits.setText(Integer.toString(credits2 = credits2 + 15));
		    }
		    else if ( m.isMandatory() == false){
		    	unselectedTerm2ModulesOS.add(m);
		    }
		    }  
		    else if (m.getRunPlan() == Delivery.YEAR_LONG) {
		    	SelectedYearLongModulesOS.add(m);
		    	currentTerm1Credits.setText(Integer.toString(credits1 = credits1 + 15));
		    	currentTerm2Credits.setText(Integer.toString(credits2 = credits2 + 15));
		    }
	}



	/**
	 * Clear module lists
	 */
	public void clear() {
		unselectedTerm1Modules.getItems().clear();
		selectedTerm1ModulesOS.clear();
		selectedTerm2ModulesOS.clear();
	}

	/**
	 * Clear notification label
	 */
	public void clearNotification() {
		notification.setText("");
	}

	/**
	 * Method to check credit amount
	 * @return credit limit reached
	 */
	public boolean creditCheck() {
        if (credits1 == 60 && credits2 == 60) {
            return true;
        } else {
            return false;
        }
    }
	

	/**
	 * Method to attach the reset modules button handler
	 * @param handler
	 */
public void addResetHandler(EventHandler<ActionEvent> handler) {
	reset.setOnAction(handler);
}


public void addAddHandler(EventHandler<ActionEvent> handler) {
	addTerm1.setOnAction(handler);
}

public void addAddHandler2(EventHandler<ActionEvent> handler) {
	addTerm2.setOnAction(handler);
}

public void addARemoveHandler1(EventHandler<ActionEvent> handler) {
	removeTerm1.setOnAction(handler);
}

public void addARemoveHandler2(EventHandler<ActionEvent> handler) {
	removeTerm2.setOnAction(handler);
}


/**
 * Method to attach the remove modules button handler
 * @param handler
 */
public void addRemoveHandler(EventHandler<ActionEvent> handler) {
	removeTerm1.setOnAction(handler);
}

/**
 * Method to attach the submit modules button handler
 * @param handler
 */
public void addSubmitHandler(EventHandler<ActionEvent> handler) {
	submit.setOnAction(handler);
}

/**
 * Rename this
 * @param handler
 */
public Module getSelectedItemTerm1() {
	return unselectedTerm1Modules.getSelectionModel().getSelectedItem();
}

public int modulesT1SelectedIndex1() {
	return unselectedTerm1Modules.getSelectionModel().getSelectedIndex();
}

public Module getSelectedItemTerm2() {
	return unselectedTerm2Modules.getSelectionModel().getSelectedItem();
}

public int modulesT2SelectedIndex() {
	return unselectedTerm2Modules.getSelectionModel().getSelectedIndex();
}

public Module getSelectedItemTerm3() {
	return selectedTerm2Modules.getSelectionModel().getSelectedItem();
}

public int modulesT2SelectedIndex3() {
	return selectedTerm2Modules.getSelectionModel().getSelectedIndex();
}

public Module getSelectedItemTerm4() {
	return selectedTerm1Modules.getSelectionModel().getSelectedItem();
}

public int modulesT2SelectedIndex4() {
	return selectedTerm1Modules.getSelectionModel().getSelectedIndex();
}
public int getint2() {
    return credits2;
}

public int getint1() {
    return credits1;
}

public TextField getint1Term1() {
    return currentTerm1Credits;
}

public TextField getint1Term2() {
    return currentTerm2Credits;
}    

public int setint2(int int2) {
    this.credits2 = int2;
    return int2;
}

public int setint1(int int1) {
    this.credits1 = int1;
    return int1;
}

/**
 * Returns lists views
 * @param handler
 */
public ObservableList<Module> returnListViews1() {
	return unselectedTerm1ModulesOS;
}

public ObservableList<Module> returnListViews2() {
	return unselectedTerm2ModulesOS;
}

public ObservableList<Module> returnListViews3() {
	return selectedTerm1ModulesOS;
}

public ObservableList<Module> returnListViews4() {
	return selectedTerm2ModulesOS;
}

public ObservableList<Module> returnListViews5() {
	return SelectedYearLongModulesOS;
}

}

