package de.codecentric.centerdevice;

import de.codecentric.centerdevice.javafx.NSMenuFX;
import de.jangassen.jfa.FoundationCallbackFactory;
import de.jangassen.jfa.appkit.NSApplication;
import de.jangassen.jfa.appkit.NSMenu;
import de.jangassen.jfa.appkit.NSMenuItem;
import javafx.application.Application;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;


public class SampleApp extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.show();

    NSApplication.sharedApplication().setMainMenu(getNsMenu());
  }

  private void old() {
    NSApplication o = NSApplication.sharedApplication();

    NSMenu nsMenu = o.mainMenu();

    NSMenuItem newItem = NSMenuItem.alloc();
    newItem.setTitle("unused");

    FoundationCallbackFactory.FoundationCallback id = FoundationCallbackFactory.instance().registerCallback((objId) -> System.out.println("hallo welt"));
    NSMenuItem newItem1 = NSMenuItem.alloc();
    newItem1.setTitle("bla");
    newItem1.setAction(id.getSelector());
    newItem1.setTarget(id.getTarget());
    newItem1.setTag("my tag");

    NSMenu menu = NSMenu.alloc();
    menu.setTitle("title");
    menu.addItem(newItem1);

    newItem.setSubmenu(menu);

    nsMenu.addItem(newItem);
    System.out.println(newItem.title());
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
    NSMenu menuBar = NSMenu.alloc();
    NSMenuItem mainMenu = NSMenuItem.alloc().initWithTitle("Application", null, "");
    mainMenu.setSubmenu(menuFX.getNsMenu());
    menuBar.addItem(mainMenu);
    return menuBar;
  }
}
