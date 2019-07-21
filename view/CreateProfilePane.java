package view;

import java.time.LocalDate;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.Course;
import model.Name;
import model.StudentProfile;

public final class CreateProfilePane extends GridPane {

	private ComboBox<Course> Courses;
	private Label notification;
	private TextField txtPnumber, Firstname, Surname, Email;
	private Button btnCreateProfile;
	private DatePicker datePicker = new DatePicker();
	private LocalDate selectedDate;
	private Name studentName = new Name();

	public CreateProfilePane() {
		
		this.setPadding(new Insets(80, 80, 80, 80));
		this.setVgap(15);
		this.setHgap(20);
		this.setAlignment(Pos.CENTER);

		
		Label lblCourse = new Label("Select course: ");
		Label lblPnumber = new Label("Input P number: ");
		Label lblFirstname = new Label("Input first name: ");
		Label lblSurname = new Label("Input surname: ");
		Label lblEmail = new Label("Input email: ");
		Label lblDate = new Label("Input date: ");


		notification = new Label("");
		notification.setTextFill(Color.web("#FF0000"));

		
		Courses = new ComboBox<Course>(); 

		
		txtPnumber = new TextField();
		Firstname = new TextField();
		Surname = new TextField();
		Email = new TextField();
		
		btnCreateProfile = new Button("Create Profile");
		
	
		datePicker.setEditable(false);

		
		this.add(lblCourse, 0, 0);
		this.add(Courses, 1, 0);

		this.add(lblPnumber, 0, 1);
		this.add(txtPnumber, 1, 1);

		this.add(lblFirstname, 0, 2);
		this.add(Firstname, 1, 2);

		this.add(lblSurname, 0, 3);
		this.add(Surname, 1, 3);

		this.add(lblEmail, 0, 4);
		this.add(Email, 1, 4);

		this.add(lblDate, 0, 5);
		this.add(datePicker, 1, 5);

		this.add(new HBox(), 0, 6);
		this.add(btnCreateProfile, 1, 6);

		this.add(notification, 1, 7);
	}

	
	public void populateComboBox(Course[] courses) {
		Courses.getItems().addAll(courses);
		Courses.getSelectionModel().select(0);
	}


	
	public Course getSelectedCourse() {
		return Courses.getSelectionModel().getSelectedItem();
	}

	
	public String getPNumberInput() {
		String pNumber = "";

		if (txtPnumber.getText().matches("^[Pp][0-9]{8}[a-zA-Z{1}]?$")) {
			pNumber = txtPnumber.getText();
			notification.setText("");
		} else {
			notification.setText("Please check your PNumber is correct!");
		}
		return pNumber;
	}


	public Name getStudentNameInput() {
		if(Firstname.getText().matches("[a-zA-Z]+\\.?") && Surname.getText().matches("[a-zA-Z]+\\.?")) {	
			studentName = new Name(Firstname.getText(), Surname.getText());
			notification.setText("");  
		} else {
			studentName = new Name("Incorrect", "Name");
			notification.setText("Please check your first & second are correct!"); 
		}

		return studentName;
	}

	
	public String getEmailInput() {
		String email = "";

		if (Email.getText().matches("^(.+)@(.+)$")) {
			email = Email.getText();
			notification.setText("");
		} else {
			notification.setText("Please check your email is valid!");
		}

		return email;
	}

	
	public LocalDate getDateInput() {
		datePicker.setOnAction(event -> {
			datePicker.getValue();

			if (datePicker.valueProperty().isNull() != null) {
				selectedDate = datePicker.getValue();
				notification.setText("");
			} else {
				notification.setText("Please check your date is valid!");
				selectedDate = null;
			}
		});

		if (datePicker.valueProperty().isNull() != null) {
			selectedDate = datePicker.getValue();
			notification.setText("");
		} else {
			notification.setText("Please check your date is valid!");
			selectedDate = null;
		}
		return selectedDate;
	}


	public boolean checkFields() {
		boolean result = false;

		if (getSelectedCourse().equals(null) || getPNumberInput().isEmpty()|| getStudentNameInput().getFirstName().toString() == "Incorrect" 
				|| getEmailInput().equals("") || getDateInput() == null) {
			result = true;
		}
		return result;
	}

	
	public void updateFields(StudentProfile studentProfile) {
		Courses.setValue(studentProfile.getCourse());  
		datePicker.setValue(studentProfile.getDate());
		Email.setText(studentProfile.getEmail());
		Firstname.setText(studentProfile.getStudentName().getFirstName());
		Surname.setText(studentProfile.getStudentName().getFamilyName());
		txtPnumber.setText(studentProfile.getpNumber());
	}

	
	public void addCreateProfile(EventHandler<ActionEvent> handler) {
		btnCreateProfile.setOnAction(handler);
	}

}
