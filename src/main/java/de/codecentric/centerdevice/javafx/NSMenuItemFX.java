package de.codecentric.centerdevice.javafx;

import de.codecentric.centerdevice.adapter.NSMenuItemProvider;
import de.codecentric.centerdevice.adapter.NSMenuProvider;
import de.codecentric.centerdevice.cocoa.NSMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;

/**
 * Created by jan on 19/03/16.
 */
public class NSMenuItemFX implements NSMenuItemProvider {

  private final NSMenuItem nsMenuItem;
  private final MenuItem menuItem;

  public NSMenuItemFX(MenuItem menuItem) {
    this.menuItem = menuItem;
    this.nsMenuItem = NSMenuItem.alloc()
        .init(menuItem.getText(), menuItem.getOnAction(), toKeyEquivalentString(menuItem.getAccelerator()));

    menuItem.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.equals(oldValue)) {
        nsMenuItem.setTitle(newValue);
      }
    });
  }

  public void setSubmenu(NSMenuProvider menu) {
    nsMenuItem.setSubmenu(menu.getNsMenu());
  }

  private static String toKeyEquivalentString(KeyCombination accelerator) {
    if (accelerator == null) {
      return "";
    }

    System.out.println(keyEquivalent(accelerator));
    return keyEquivalent(accelerator);
  }

  private static String keyEquivalent(KeyCombination accelerator) {
    String keyEquivalendString = accelerator.getName().replace("Meta", "");
    if (accelerator.getShift() != KeyCombination.ModifierValue.DOWN) {
      keyEquivalendString = keyEquivalendString.toLowerCase();
    }
    if (keyEquivalendString.startsWith("+")) {
      keyEquivalendString = keyEquivalendString.substring(1);
    }
    return keyEquivalendString;
  }

  public NSMenuItem getNsMenuItem() {
    return nsMenuItem;
  }
}
