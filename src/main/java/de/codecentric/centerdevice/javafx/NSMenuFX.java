package de.codecentric.centerdevice.javafx;

import de.codecentric.centerdevice.adapter.NSMenuProvider;
import de.codecentric.centerdevice.cocoa.NSMenu;
import de.codecentric.centerdevice.cocoa.NSMenuItem;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jan on 20/03/16.
 */
public class NSMenuFX implements NSMenuProvider {
  private final Map<MenuItem, NSMenuItem> items = new HashMap<>();
  private final NSMenu nsMenu;

  public NSMenuFX(Menu menu) {
    this.nsMenu = NSMenu.alloc().init(menu.getText());

    menu.getItems().forEach(this::addMenuItem);

    menu.textProperty().addListener((observable, oldValue, newValue) -> {
      nsMenu.setTitle(newValue);
    });

    menu.getItems().addListener((ListChangeListener<MenuItem>) c -> handleMenuItemChange(c));
  }

  private void addMenuItem(MenuItem menuItem) {
    NSMenuItemFX nsMenuItemFX = new NSMenuItemFX(menuItem);
    if (menuItem instanceof Menu) {
      nsMenuItemFX.setSubmenu(new NSMenuFX((Menu) menuItem));
    }

    NSMenuItem nsMenuItem = nsMenuItemFX.getNsMenuItem();
    items.put(menuItem, nsMenuItem);
    this.nsMenu.addItem(nsMenuItem);
  }

  private void removeMenuItem(MenuItem menuItem) {
    NSMenuItem nsMenuItem = items.get(menuItem);
    if (nsMenuItem != null) {
      this.nsMenu.removeItem(nsMenuItem);
      items.remove(menuItem);
    }
  }

  private void handleMenuItemChange(ListChangeListener.Change<? extends MenuItem> change) {
    while (change.next()) {
      if (change.wasPermutated()) {
        for (int i = change.getFrom(); i < change.getTo(); ++i) {
          //permutate
        }
      } else if (change.wasUpdated()) {
        //update item
      } else {
        change.getRemoved().forEach(this::removeMenuItem);
        change.getAddedSubList().forEach(this::addMenuItem);
      }
    }
  }

  public NSMenu getNsMenu() {
    return nsMenu;
  }
}
