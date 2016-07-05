package de.codecentric.centerdevice.cocoa;

import de.codecentric.centerdevice.javafx.NSMenuFX;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by jan on 19/03/16.
 */
public class SampleApp extends Application {

  static final String appName = "Standard";
  static final String mainWindowTitle = "Main";
  static final String childWindowTitle = "Child";

  static long counter = 1;

  @Override public void start(Stage primaryStage) throws Exception {
    StackPane root = new StackPane();
    Button button = new Button();
    button.setText("Create new Stage");
    button.setOnAction(action -> createNewStage());
    root.getChildren().add(button);

    primaryStage.setScene(new Scene(root, 300, 200));
    primaryStage.requestFocus();
    primaryStage.setTitle(mainWindowTitle);
    primaryStage.show();

    Menu menu = new Menu("Menu");
    MenuItem item1 = new MenuItem("Item1");
    MenuItem item2 = new MenuItem("Item2");
    Menu item3 = new Menu("Item3");
    item3.getItems().add(new MenuItem("SubItem"));
    menu.getItems().addAll(item1, item2, item3);

    NSMenuFX menuFX = new NSMenuFX(menu); // TODO: NSMenuFX not yet supporting submenus
    
    /*
     * This API has not been carved in stone. It'll probably always look a bit odd, as Cocoa has no MenuBar class but
     * uses a NSMenu instead. The most important thing however is to keep the properties connected so that once the
     * menu has been set, updates to the JavaFX objects will update their native counterparts.
     */

    // TODO: Replace the following code with something like

    // MenuBar bar = new MenuBar();
    // bar.getMenus().addAll(menu);
    // NSApplication.sharedApplication().setMainMenu(menuBarFX.getNsMenu());
    NSMenu menuBar = NSMenu.alloc().init("");
    NSMenuItem mainMenu = NSMenuItem.alloc().init("Application");
    mainMenu.setSubmenu(menuFX.getNsMenu());
    menuBar.addItem(mainMenu);

    // TODO: There seems to be a timing issue when setting a new menu bar before all code for showing the window
    // has finished. In this case it might happen that the default menu bar will overwrite our menu again.
    Platform.runLater(() -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      NSApplication.sharedApplication().setMainMenu(menuBar);
    });

  }

  private void createNewStage() {
    Stage stage = new Stage();
    stage.setScene(new Scene(new StackPane()));
    stage.setTitle(childWindowTitle + " " + (counter++));
    stage.show();
  }
}
