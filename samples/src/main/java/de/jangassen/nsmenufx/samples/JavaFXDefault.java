package de.jangassen.nsmenufx.samples;

import de.jangassen.MenuToolkit;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Locale;

public class JavaFXDefault extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		MenuToolkit tk = MenuToolkit.toolkit(Locale.getDefault());
		tk.setApplicationMenu(tk.createDefaultApplicationMenu("test"));

		tk.setDocIconMenu(new Menu());

		MenuBar menuBar = new MenuBar();
		menuBar.useSystemMenuBarProperty().set(true);

		Menu menu = new Menu("java");
		MenuItem item = new MenuItem("Test");

		Menu help = new Menu("Help");
		menu.getItems().add(item);
		menuBar.getMenus().addAll(menu, help);

		primaryStage.setScene(new Scene(new Pane(menuBar)));
		primaryStage.setTitle("Test");
		primaryStage.show();
	}
}
