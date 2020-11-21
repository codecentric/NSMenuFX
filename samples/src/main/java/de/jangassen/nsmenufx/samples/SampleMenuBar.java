package de.jangassen.nsmenufx.samples;

import de.jangassen.MenuToolkit;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SampleMenuBar extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		StackPane root = new StackPane();
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.requestFocus();
		primaryStage.show();

		MenuToolkit tk = MenuToolkit.toolkit();

		MenuBar bar = new MenuBar();

		MenuItem item1 = new MenuItem("Item1");
		MenuItem item2 = new MenuItem("Item2");
		MenuItem item3 = new MenuItem("Item3");
		item3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Item3 clicked");
			}
		});

		MenuItem item4 = tk.createQuitMenuItem("my app");

		Menu menu2 = new Menu("Menu2");
		menu2.getItems().add(item2);
		Menu menu1 = new Menu("Menu1");
		menu1.getItems().addAll(item1, menu2, item4);

		Menu menu3 = new Menu("Menu3");
		menu3.getItems().addAll(item3);

		bar.getMenus().addAll(menu1, menu3);

		tk.setMenuBar(primaryStage, bar);

	}

	public static void main(String[] args) {
		launch(args);
	}

}
