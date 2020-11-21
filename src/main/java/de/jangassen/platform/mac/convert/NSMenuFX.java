package de.jangassen.platform.mac.convert;

import de.jangassen.platform.mac.cleanup.NSCleaner;
import de.jangassen.jfa.appkit.NSMenu;
import de.jangassen.jfa.appkit.NSMenuItem;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.util.HashMap;
import java.util.Map;

public class NSMenuFX {
  public static NSMenu convert(Menu menu) {
    NSMenu nsMenu = NSMenu.alloc().initWithTitle(menu.getText());

    Map<MenuItem, NSMenuItem> fxToNsMenuItems = new HashMap<>();
    menu.getItems()
            .forEach(item -> NSMenuFX.addMenuItem(nsMenu, fxToNsMenuItems, item));
    menu.textProperty()
            .addListener((observable, oldValue, newValue) -> nsMenu.setTitle(newValue));
    menu.getItems()
            .addListener((ListChangeListener<MenuItem>) (change -> NSMenuFX.handleMenuItemChange(nsMenu, fxToNsMenuItems, change)));

    NSCleaner.register(menu, nsMenu);
    return nsMenu;
  }

  private static void addMenuItem(NSMenu nsMenu, Map<MenuItem, NSMenuItem> fxToNsMenuItems, MenuItem menuItem) {
    NSMenuItem nsMenuItem = NSMenuItemFX.convert(menuItem);
    if (menuItem instanceof Menu) {
      nsMenuItem.setSubmenu(NSMenuFX.convert((Menu) menuItem));
    }

    fxToNsMenuItems.put(menuItem, nsMenuItem);
    nsMenu.addItem(nsMenuItem);
  }

  private static void removeMenuItem(NSMenu nsMenu, Map<MenuItem, NSMenuItem> fxToNsMenuItems, MenuItem menuItem) {
    NSMenuItem nsMenuItem = fxToNsMenuItems.get(menuItem);
    if (nsMenuItem != null) {
      nsMenu.removeItem(nsMenuItem);
      fxToNsMenuItems.remove(menuItem);
    }
  }

  private static void handleMenuItemChange(NSMenu nsMenu, Map<MenuItem, NSMenuItem> fxToNsMenuItems, ListChangeListener.Change<? extends MenuItem> change) {
    while (change.next()) {
      if (change.wasPermutated()) {
        for (int i = change.getFrom(); i < change.getTo(); ++i) {
          //permutate
        }
      } else if (change.wasUpdated()) {
        //update item
      } else {
        change.getRemoved().forEach(item -> NSMenuFX.removeMenuItem(nsMenu, fxToNsMenuItems, item));
        change.getAddedSubList().forEach(item -> NSMenuFX.addMenuItem(nsMenu, fxToNsMenuItems, item));
      }
    }
  }
}
