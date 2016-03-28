package de.codecentric.centerdevice.cocoa;

import com.sun.xml.internal.ws.api.server.WSWebServiceContext;
import de.codecentric.centerdevice.javafx.NSMenuFX;
import javafx.application.Application;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * Created by jan on 19/03/16.
 */
public class SampleApp extends Application {

  @Override public void start(Stage primaryStage) throws Exception {
    primaryStage.show();

    Menu menu = new Menu("Menu");
    MenuItem item1 = new MenuItem("Item1");
    MenuItem item2 = new MenuItem("Item2");
    menu.getItems().addAll(item1, item2);

    NSMenuFX menuFX = new NSMenuFX(menu);

    MenuBar bar = new MenuBar();

    NSMenuItem nsItem = NSMenuItem.alloc().init(menu.getText());
    nsItem.setSubmenu(menuFX.getNsMenu());
    NSApplication.sharedApplication().mainMenu().addItem(nsItem);

    menu.getItems().add(new MenuItem("Item3"));
    menu.getItems().remove(0,2);
    //menuItem.setText("New Test");
  }
}
