package view;


import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

//You may change this class to extend another type if you wish
public class ModuleChooserRootPane extends VBox {
	

		private CreateProfilePane cpp;
		private SelectModules sm;
		private OverviewSelection os;
		private ModuleChooserMenuBar mcmb;
		private TabPane rootTabs;
		
		public ModuleChooserRootPane() {
		

			mcmb = new ModuleChooserMenuBar();
			cpp = new CreateProfilePane();
			sm = new SelectModules();
			os = new OverviewSelection();

			// create tabs with panes added
			rootTabs = new TabPane();
			Tab t1 = new Tab("Create Profile", cpp);
			Tab t2 = new Tab("Select Modules",sm);
			Tab t3 = new Tab("Overview Selection", os);

			rootTabs.getTabs().addAll(t1, t2, t3);

			// add tabs to tab pane
			this.getChildren().addAll(mcmb, rootTabs);

		}

		/**
		 * Method allows tab to be changed
		 * 
		 * @param index of tab
		 */
		public void changeTab(int index) {
			rootTabs.getSelectionModel().select(index);
		}

		// These methods provide a public interface for the root pane and allow each
		// of the sub-containers to be accessed by the controller.

		/**
		 * Gets the menu bar interface
		 * 
		 * @return menu bar
		 */
		public ModuleChooserMenuBar getModuleChooserMenuBar() {
			return mcmb;
		}

		/**
		 * Gets the create profile interface
		 * 
		 * @return create profile pane
		 */
		public CreateProfilePane getCreateProfilePane() {
			return cpp;
		}

		/**
		 * Gets the selected module interface
		 * 
		 * @return select modules pane
		 */
		public SelectModules getSelectModules() {
			return sm;
		}

		/**
		 * Gets the overview selection interface
		 * 
		 * @return overview selection pane
		 */
		public OverviewSelection getOverviewSelection() {
			return os;
		}
	
}

