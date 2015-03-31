package de.codecentric.centerdevice.sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import de.codecentric.centerdevice.platform.osx.NSMenuBarAdapter;

public class RenameMenuBar extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		StackPane root = new StackPane();
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.requestFocus();
		primaryStage.show();

		NSMenuBarAdapter creator = new NSMenuBarAdapter();

		creator.renameMenuItem(0, 0, "Hide Test");
		creator.renameMenuItem(0, -1, "Quit Test");

		creator.insertMenuItems(0, 0, new MenuItem("blub"),
				new SeparatorMenuItem());
		creator.renameApplicationMenu("hmmm");
	}

	public static void main(String[] args) {
		launch(args);
	}

}
