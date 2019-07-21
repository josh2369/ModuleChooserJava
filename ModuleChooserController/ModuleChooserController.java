package ModuleChooserController;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import view.CreateProfilePane;
import view.ModuleChooserMenuBar;
import view.ModuleChooserRootPane;
import view.OverviewSelection;
import view.SelectModules;
import model.StudentProfile;
import model.Course;
import model.Delivery;
import model.Module;

public class ModuleChooserController {

	private CreateProfilePane cpp;
	private SelectModules sm;
	private OverviewSelection os;
	private ModuleChooserMenuBar mcmb;
	private StudentProfile model;
	private ModuleChooserRootPane view;

	
	public ModuleChooserController(ModuleChooserRootPane view, StudentProfile model) {

		
		this.model = model;
		this.view = view;

		cpp = view.getCreateProfilePane();
		sm = view.getSelectModules();
		os = view.getOverviewSelection();
		mcmb = view.getModuleChooserMenuBar();

		cpp.populateComboBox(setupAndGetCourses());

		this.attachEventHandlers();
	}

	


	private Course[] setupAndGetCourses() {
		Module imat3423 = new Module("IMAT3423", "Systems Building: Methods", 15, true, Delivery.TERM_1);
		Module imat3451 = new Module("IMAT3451", "Computing Project", 30, true, Delivery.YEAR_LONG);
		Module ctec3902_SoftEng = new Module("CTEC3902", "Rigerous Systems", 15, true, Delivery.TERM_2);	
		Module ctec3902_CompSci = new Module("CTEC3902", "Rigerous Systems", 15, false, Delivery.TERM_2);
		Module ctec3110 = new Module("CTEC3110", "Secure Web Application Development", 15, false, Delivery.TERM_1);
		Module ctec3426 = new Module("CTEC3426", "Telematics", 15, false, Delivery.TERM_1);
		Module ctec3605 = new Module("CTEC3605", "Multi-service Networks 1", 15, false, Delivery.TERM_1);	
		Module ctec3606 = new Module("CTEC3606", "Multi-service Networks 2", 15, false, Delivery.TERM_2);	
		Module ctec3410 = new Module("CTEC3410", "Web Application Penetration Testing", 15, false, Delivery.TERM_2);
		Module ctec3904 = new Module("CTEC3904", "Functional Software Development", 15, false, Delivery.TERM_2);
		Module ctec3905 = new Module("CTEC3905", "Front-End Web Development", 15, false, Delivery.TERM_1);
		Module ctec3906 = new Module("CTEC3906", "Interaction Design", 15, false, Delivery.TERM_2);
		Module imat3410 = new Module("IMAT3104", "Database Management and Programming", 15, false, Delivery.TERM_2);
		Module imat3406 = new Module("IMAT3406", "Fuzzy Logic and Knowledge Based Systems", 15, false, Delivery.TERM_1);
		Module imat3611 = new Module("IMAT3611", "Computing Ethics and Privacy", 15, false, Delivery.TERM_1);
		Module imat3613 = new Module("IMAT3613", "Data Mining", 15, false, Delivery.TERM_1);
		Module imat3614 = new Module("IMAT3614", "Big Data", 15, false, Delivery.TERM_2);
		Module imat3428_CompSci = new Module("IMAT3428", "Information Technology Services Practice", 15, false, Delivery.TERM_2);

		Course compSci = new Course("Computer Science");
		compSci.addModule(imat3423);
		compSci.addModule(imat3451);
		compSci.addModule(ctec3902_CompSci);
		compSci.addModule(ctec3110);
		compSci.addModule(ctec3426);
		compSci.addModule(ctec3605);
		compSci.addModule(ctec3606);
		compSci.addModule(ctec3410);
		compSci.addModule(ctec3904);
		compSci.addModule(ctec3905);
		compSci.addModule(ctec3906);
		compSci.addModule(imat3410);
		compSci.addModule(imat3406);
		compSci.addModule(imat3611);
		compSci.addModule(imat3613);
		compSci.addModule(imat3614);
		compSci.addModule(imat3428_CompSci);

		Course softEng = new Course("Software Engineering");
		softEng.addModule(imat3423);
		softEng.addModule(imat3451);
		softEng.addModule(ctec3902_SoftEng);
		softEng.addModule(ctec3110);
		softEng.addModule(ctec3426);
		softEng.addModule(ctec3605);
		softEng.addModule(ctec3606);
		softEng.addModule(ctec3410);
		softEng.addModule(ctec3904);
		softEng.addModule(ctec3905);
		softEng.addModule(ctec3906);
		softEng.addModule(imat3410);
		softEng.addModule(imat3406);
		softEng.addModule(imat3611);
		softEng.addModule(imat3613);
		softEng.addModule(imat3614);

		Course[] courses = new Course[2];
		courses[0] = compSci;
		courses[1] = softEng;

		return courses;
	}
	private void attachEventHandlers() {
		cpp.addCreateProfile(new CreateProfile());
		sm.addResetHandler(new ResetHandler());
		sm.addAddHandler(new attachAdd1Handler());
		sm.addAddHandler2(new attachAdd2Handler());
		sm.addARemoveHandler1(new attachRemove1Handler());
		sm.addARemoveHandler2(new attachRemove2Handler());
		sm.addSubmitHandler(new SubmitHandler());
		os.addSaveOverviewHandler(new SaveOverviewHandler());
		mcmb.addSaveProfileHandler(new SaveProfileHandler());
		mcmb.addLoadStudentDataHandler(new LoadStudentDataHandler());
		mcmb.addAboutHandler(new AboutHandler());
		mcmb.addExitHandler(new ExitHandler());
	}

	
	public class CreateProfile implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {

			if (cpp.checkFields() == true) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Profile Error");
				alert.setHeaderText("Incorrect profile");
				alert.setContentText("Please check your details to continue!");

				alert.showAndWait();
		} else {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Profile Confirmation");
				alert.setHeaderText("Make sure you check your profile");
				alert.setContentText("Are you ok with your profile details?");

				Optional<ButtonType> result = alert.showAndWait();
	if (result.get() == ButtonType.OK) {

					model.setCourse(cpp.getSelectedCourse());
					model.setpNumber(cpp.getPNumberInput());
					model.setStudentName(cpp.getStudentNameInput());
					model.setEmail(cpp.getEmailInput());
					model.setDate(cpp.getDateInput());
					Collection<Module> mods = model.getCourse().getModulesOnCourse();
					
					for (Module m: mods){
						sm.addModulesToLists(m);
					}
					view.changeTab(1);
			}
			}
		}
	}
	
		
	
	public class ResetHandler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {
				
		}

	}
	

	public class attachAdd1Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			Module t1 =  sm.getSelectedItemTerm1();
		    int index = sm.modulesT1SelectedIndex1();
		    
		    int counter = 0;
		    ObservableList<Module >current = sm.returnListViews3();
		    
		    for (int i = 0; i < current.size(); i++) {
	    		counter++;
	    	}
		    	
		    if (index != -1) {
		    	if (counter <3 ){
		    	sm.returnListViews1().removeAll(sm.getSelectedItemTerm1());
		    	sm.returnListViews3().addAll(t1);
		    	sm.getint1Term1().setText(Integer.toString(sm.setint1(sm.getint1() + 15)));
		    	}
		    	
			}
		}
	}
	

	public class attachAdd2Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			Module t1 =  sm.getSelectedItemTerm2();
		    int index = sm.modulesT2SelectedIndex();
		    
		    int counter = 0;
		    ObservableList<Module >current = sm.returnListViews4();
		    
		    for (int i = 0; i < current.size(); i++) {
	    		counter++;
	    	}
		    	
		    if (index != -1) {
		    	if (counter <3 ){
		    	sm.returnListViews2().removeAll(sm.getSelectedItemTerm2());
		    	sm.returnListViews4().addAll(t1);
		    	sm.getint1Term2().setText(Integer.toString(sm.setint2(sm.getint2() + 15)));
		    }
		    }
		}
	}
	
	public class attachRemove1Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			Module t1 =  sm.getSelectedItemTerm4();
		    int index = sm.modulesT2SelectedIndex4();
		    

			if(sm.getSelectedItemTerm4().isMandatory()) {
				return;
			}

		    if (index != -1) {
		    	sm.returnListViews3().removeAll(sm.getSelectedItemTerm4());
		    	sm.returnListViews1().addAll(t1);
		    	sm.getint1Term1().setText(Integer.toString(sm.setint1(sm.getint1() - 15)));  ;
		    }
		}
	}
	
	public class attachRemove2Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			Module t1 =  sm.getSelectedItemTerm3();
		    int index = sm.modulesT2SelectedIndex3();
		    
		    if(sm.getSelectedItemTerm3().isMandatory()) {
				return;
			}
		    	
		    if (index != -1) {
		    	sm.returnListViews4().removeAll(sm.getSelectedItemTerm3());
		    	sm.returnListViews2().addAll(t1);
		    	sm.getint1Term2().setText(Integer.toString(sm.setint2(sm.getint2() - 15)));
		    }
		}
	}
	
	public class SubmitHandler implements EventHandler<ActionEvent> {
		
				public void handle(ActionEvent e) {
		
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Submit");
					alert.setHeaderText("Submitting Modules...");
					alert.setContentText("Are you ok with your selected modules?");
		
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK) {
		
						if (sm.creditCheck() == true) { // check if credits == 120
							// add selected modules to student profile
							sm.returnListViews3().forEach(n -> model.addSelectedModule(n));
							
							sm.returnListViews4().forEach(n -> model.addSelectedModule(n));
		
							// loading overview Textbox
						
						
							
							os.setStudentProfile(setOverviewString());
							
							//change to overview tab
							view.changeTab(2);
						} else {
							alertDialogBuilder(AlertType.ERROR, "Error", "Load Failed", "Check that you have 120 credits in order to continue.");
						}
					}
				}

			
	}
	
	
	

	
	public class SaveOverviewHandler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Save Overview");
			alert.setHeaderText("Saving profile to text file...");
			alert.setContentText("Are you sure you want to save the current overview?");

			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == ButtonType.OK) {
				os.saveProfileTxt();
			}

		}

	}

	
	public class SaveProfileHandler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Save");
			alert.setHeaderText("Saving profile to dat file...");
			alert.setContentText("Are you sure you want to save the current profile?");

			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == ButtonType.OK) {
				try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student-profile.dat"));) {
					if(model.getSelectedModules() != null) {
						oos.writeObject(model);
						oos.flush();
						alertDialogBuilder(AlertType.INFORMATION, "Save", "Save success",
								"Profile saved to student-profile.dat");
					}
				} catch (IOException ioExcep) {
					ioExcep.printStackTrace();
				}
			}
		}
	}

	
	public class LoadStudentDataHandler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {
			Alert alertSaving = new Alert(AlertType.CONFIRMATION);
			alertSaving.setTitle("Load");
			alertSaving.setHeaderText("Loading profile ");
			alertSaving.setContentText("Are you sure you want to load a saved profile?");

			Optional<ButtonType> result = alertSaving.showAndWait();

			if (result.get() == ButtonType.OK) {
				try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student-profile.dat"));) {

					model = (StudentProfile) ois.readObject();
					
									
					cpp.updateFields(model);

					
					os.setStudentProfile(setOverviewString());

					
					alertDialogBuilder(AlertType.INFORMATION, "Load", "Load success",
							"Profile loaded from student-profile.dat");
				} catch (IOException ioExcep) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Loading");
					alert.setHeaderText("Error loading profile");
					alert.setContentText("There was anError loading your profile");

					alert.showAndWait();
				} catch (ClassNotFoundException c) {
					System.out.println("Class Not found");
				}
			}
		}

	}

	
	public class AboutHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent e) {
			alertDialogBuilder(AlertType.INFORMATION, "About", "About", "Program for picking final year modules.");
		}

	}

	/**
	 * Handler for Exit option 
	 */
	public class ExitHandler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {
			Platform.exit();
		}

	}

	
	public String setOverviewString() {

		List<String> modulesArray = new ArrayList<String>();

		sm.returnListViews3().forEach(n -> {
			String mandatory;

			if(n.isMandatory() == true) {
				mandatory = "yes";
			} else { 
				mandatory = "no";
			}

			modulesArray.add(" \nModule code: " + n.getModuleCode() + " Credits: " + n.getCredits() + "\nMandatory on your course? "
					+ mandatory + "\nModule name: " + n.getModuleName() + "\n");
		});
		
		sm.returnListViews4().forEach(n -> {
			String mandatory;

			if(n.isMandatory() == true) {
				mandatory = "yes";
			} else { 
				mandatory = "no";
			}

			modulesArray.add(" \nModule code: " + n.getModuleCode() + " Credits: " + n.getCredits() + "\nMandatory on your course? "
					+ mandatory + "\nModule name: " + n.getModuleName() + "\n");
		});

		String selectedModules = Arrays.asList(modulesArray).toString().substring(1).replace(", ", "").replace("[", "").replace("]", "");

		String studentProfile = "Name: " + model.getStudentName() + "\nPNo: " + model.getpNumber() + "\nEmail: " + model.getEmail() + "\nDate: " + model.getDate() 
		+ "\nCourse: " + model.getCourse() + "\n\nSelected modules:" + "\n=========" + selectedModules;

		return studentProfile;
	}
	
	private void alertDialogBuilder(AlertType type, String title, String header, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
}

	

		
	
		
	


		
		

	





