package de.codecentric.centerdevice.sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import de.codecentric.centerdevice.platform.osx.NSMenuBarAdapter;
import de.codecentric.centerdevice.platform.osx.action.HideOthersAction;

public class SampleMenuBar extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		StackPane root = new StackPane();
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.requestFocus();
		primaryStage.show();

		NSMenuBarAdapter creator = new NSMenuBarAdapter();

		MenuBar bar = new MenuBar();

		MenuItem item1 = new MenuItem("Item1");
		MenuItem item2 = new MenuItem("Item2");
		MenuItem item3 = new MenuItem("Item3");
		MenuItem item4 = new MenuItem("Item4");
		item4.setOnAction(new HideOthersAction());

		Menu menu1 = new Menu("Menu1");
		menu1.getItems().add(item1);
		Menu menu2 = new Menu("Menu2");
		menu2.getItems().add(item2);
		menu1.getItems().add(menu2);

		Menu menu3 = new Menu("Menu3");
		menu3.getItems().addAll(item3, item4);

		bar.getMenus().addAll(menu1, menu3);

		creator.setMenuBar(bar);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
