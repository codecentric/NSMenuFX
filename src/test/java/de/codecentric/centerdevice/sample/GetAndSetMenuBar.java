package de.codecentric.centerdevice.sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import de.codecentric.centerdevice.platform.osx.NSMenuBarAdapter;

public class GetAndSetMenuBar extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		StackPane root = new StackPane();
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.requestFocus();
		primaryStage.show();

		NSMenuBarAdapter adapter = new NSMenuBarAdapter();

		MenuBar menuBar = adapter.getMenuBar();
		menuBar.getMenus().get(0).setText("Hello World");
		menuBar.getMenus().get(0).getItems().get(0).setText("Yeeha");
		adapter.setMenuBar(menuBar);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
