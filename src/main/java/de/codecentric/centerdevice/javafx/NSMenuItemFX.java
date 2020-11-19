package de.codecentric.centerdevice.javafx;

import de.codecentric.centerdevice.cleanup.FoundationCallbackCleaner;
import de.codecentric.centerdevice.cleanup.NSCleaner;
import de.codecentric.centerdevice.adapter.NSMenuItemProvider;
import de.codecentric.centerdevice.adapter.NSMenuProvider;
import de.codecentric.centerdevice.cleanup.NSObjectCleaner;
import de.jangassen.jfa.FoundationCallbackFactory;
import de.jangassen.jfa.appkit.NSMenuItem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;

public class NSMenuItemFX implements NSMenuItemProvider {

  public static final FoundationCallbackFactory.FoundationCallback VOID_CALLBACK = new FoundationCallbackFactory.FoundationCallback(-1, null, null);
  private final NSMenuItem nsMenuItem;
  private final FoundationCallbackFactory.FoundationCallback foundationCallback;

  public NSMenuItemFX(MenuItem menuItem) {
    foundationCallback = getFoundationCallback(menuItem);

    this.nsMenuItem = NSMenuItem.alloc().initWithTitle(menuItem.getText(), foundationCallback.getSelector(), toKeyEquivalentString(menuItem.getAccelerator()));
    this.nsMenuItem.setTarget(foundationCallback.getTarget());

    menuItem.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.equals(oldValue)) {
        nsMenuItem.setTitle(newValue);
      }
    });

    NSCleaner.CLEANER.register(this, new NSObjectCleaner(nsMenuItem));
    if (foundationCallback != VOID_CALLBACK) {
      NSCleaner.CLEANER.register(this, new FoundationCallbackCleaner(foundationCallback));
    }
  }

  private FoundationCallbackFactory.FoundationCallback getFoundationCallback(MenuItem menuItem) {
    EventHandler<ActionEvent> onAction = menuItem.getOnAction();
    if (onAction == null) {
      return VOID_CALLBACK;
    }
    return FoundationCallbackFactory.instance().registerCallback(id -> onAction.handle(new ActionEvent()));
  }

  public void setSubmenu(NSMenuProvider menu) {
    nsMenuItem.setSubmenu(menu.getNsMenu());
  }

  private static String toKeyEquivalentString(KeyCombination accelerator) {
    if (accelerator == null) {
      return "";
    }

    return keyEquivalent(accelerator);
  }

  private static String keyEquivalent(KeyCombination accelerator) {
    String keyEquivalentString = accelerator.getName().replace("Meta", "");
    if (accelerator.getShift() != KeyCombination.ModifierValue.DOWN) {
      keyEquivalentString = keyEquivalentString.toLowerCase();
    }
    if (keyEquivalentString.startsWith("+")) {
      keyEquivalentString = keyEquivalentString.substring(1);
    }
    return keyEquivalentString;
  }

  public NSMenuItem getNsMenuItem() {
    return nsMenuItem;
  }

}
