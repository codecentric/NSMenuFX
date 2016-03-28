package de.codecentric.centerdevice.javafx;

import de.codecentric.centerdevice.adapter.NSMenuItemProvider;
import de.codecentric.centerdevice.cocoa.NSMenuItem;
import javafx.scene.control.MenuItem;

/**
 * Created by jan on 19/03/16.
 */
public class NSMenuItemFX implements NSMenuItemProvider {

  private final NSMenuItem nsMenuItem;
  private final MenuItem menuItem;

  public NSMenuItemFX(MenuItem menuItem) {
    this.menuItem = menuItem;
    this.nsMenuItem = NSMenuItem.alloc().init(menuItem.getText(), menuItem.getOnAction(), "");

    menuItem.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.equals(oldValue)) {
        nsMenuItem.setTitle(newValue);
      }
    });
  }

  public NSMenuItem getNsMenuItem() {
    return nsMenuItem;
  }
}
