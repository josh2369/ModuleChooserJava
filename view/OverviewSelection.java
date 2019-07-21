package view;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public final class OverviewSelection extends GridPane {

	private TextArea pov;
	private Button sb;

	public OverviewSelection() {

		
		this.setPadding(new Insets(20, 20, 20, 20));
		this.setVgap(15);
		this.setHgap(20);
		

		
		pov = new TextArea("Overview ");
		pov.setEditable(false);

		HBox spBox = new HBox(pov);
		spBox.setPadding(new Insets(40, 40, 40, 40));
		pov.setPrefSize(REMAINING, REMAINING);

		this.add(spBox, 0, 0);

		//create button
		sb = new Button("Save");
		sb.setPrefSize(150, 50);
		

		HBox btnBox = new HBox(sb);
		btnBox.setPadding(new Insets(0, 0, 0, 0));
		this.setAlignment(Pos.CENTER);
		
		this.add(btnBox, 0, 1);

	}

	/**
	 * 
	 * @param profile overview
	 */
	public void setStudentProfile(String profileOverview) {
		pov.setText(profileOverview);
	}

	
	public void saveProfileTxt() {
		List<String> lines = Arrays.asList(pov.getText());
		Path file = Paths.get("stp.txt");
		try {
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			
		}
	}

	/**
	 * 
	 * 
	 * @param handler
	 */
	public void addSaveOverviewHandler(EventHandler<ActionEvent> handler) {
		sb.setOnAction(handler);
	}
}
