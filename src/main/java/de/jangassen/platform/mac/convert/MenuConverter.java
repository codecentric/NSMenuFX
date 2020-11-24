package de.jangassen.platform.mac.convert;

import de.jangassen.jfa.appkit.NSMenu;
import de.jangassen.jfa.appkit.NSMenuItem;
import de.jangassen.jfa.cleanup.NSCleaner;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MenuConverter {
  private MenuConverter() {}

  public static NSMenu convert(Menu menu) {
    if (menu == null) {
      return null;
    }

    String text = Optional.ofNullable(menu.getText()).orElse("");
    NSMenu nsMenu = NSMenu.alloc().initWithTitle(text);

    Map<MenuItem, NSMenuItem> fxToNsMenuItems = new HashMap<>();
    menu.getItems()
            .forEach(item -> MenuConverter.addMenuItem(nsMenu, fxToNsMenuItems, item));
    menu.textProperty()
            .addListener((observable, oldValue, newValue) -> nsMenu.setTitle(newValue));
    menu.getItems()
            .addListener((ListChangeListener<MenuItem>) (change -> MenuConverter.handleMenuItemChange(nsMenu, fxToNsMenuItems, change)));

    NSCleaner.register(menu, nsMenu);
    return nsMenu;
  }

  private static void addMenuItem(NSMenu nsMenu, Map<MenuItem, NSMenuItem> fxToNsMenuItems, MenuItem menuItem) {
    NSMenuItem nsMenuItem = MenuItemConverter.convert(menuItem);
    if (menuItem instanceof Menu) {
      nsMenuItem.setSubmenu(MenuConverter.convert((Menu) menuItem));
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
        change.getRemoved().forEach(item -> MenuConverter.removeMenuItem(nsMenu, fxToNsMenuItems, item));
        change.getAddedSubList().forEach(item -> MenuConverter.addMenuItem(nsMenu, fxToNsMenuItems, item));
      }
    }
  }
}
