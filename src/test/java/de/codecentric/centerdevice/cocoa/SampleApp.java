package de.codecentric.centerdevice.cocoa;

import com.sun.javafx.stage.StageHelper;
import de.codecentric.centerdevice.javafx.NSMenuFX;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WeakChangeListener;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.concurrent.Executors;

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

    Scene scene = new Scene(root, 300, 200);
    primaryStage.setScene(scene);
    primaryStage.requestFocus();
    primaryStage.setTitle(mainWindowTitle);

    primaryStage.addEventHandler(WindowEvent.WINDOW_SHOWN, (event) -> {
      Executors.newSingleThreadExecutor().execute(() -> {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("Setting");
        NSApplication.sharedApplication().setMainMenu(getNsMenu());
      });
    });

    primaryStage.show();
  }

  private NSMenu getNsMenu() {
    Menu menu = new Menu("Menu");
    MenuItem item1 = new MenuItem("Quit");
    item1.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.META_DOWN));
    item1.setOnAction(e -> Runtime.getRuntime().exit(0));
    MenuItem item2 = new MenuItem("Item2");
    Menu item3 = new Menu("Item3");
    item3.getItems().add(new MenuItem("SubItem"));
    menu.getItems().addAll(item1, item2, item3);

    NSMenuFX menuFX = new NSMenuFX(menu);

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
    return menuBar;
  }

  private void createNewStage() {
    Stage stage = new Stage();
    stage.setScene(new Scene(new StackPane()));
    stage.setTitle(childWindowTitle + " " + (counter++));
    stage.show();
  }
}
