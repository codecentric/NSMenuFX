package de.codecentric.centerdevice.javafx;

import de.codecentric.centerdevice.cleanup.FoundationCallbackCleaner;
import de.codecentric.centerdevice.cleanup.NSCleaner;
import de.codecentric.centerdevice.cleanup.NSObjectCleaner;
import de.jangassen.jfa.FoundationCallbackFactory;
import de.jangassen.jfa.FoundationProxy;
import de.jangassen.jfa.appkit.NSMenuItem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCombination;

import java.util.Optional;

public class NSMenuItemFX {

  public static final FoundationCallbackFactory.FoundationCallback VOID_CALLBACK = new FoundationCallbackFactory.FoundationCallback(-1, null, null);

  public static NSMenuItem convert(MenuItem menuItem) {
    if (menuItem instanceof SeparatorMenuItem) {
      return FoundationProxy.invokeStatic(NSMenuItem.class, "separatorItem");
    } else {
      FoundationCallbackFactory.FoundationCallback foundationCallback = getFoundationCallback(menuItem);

      String text = Optional.ofNullable(menuItem.getText()).orElse("");
      NSMenuItem nsMenuItem = NSMenuItem.alloc()
              .initWithTitle(text, foundationCallback.getSelector(), toKeyEquivalentString(menuItem.getAccelerator()));
      nsMenuItem.setTarget(foundationCallback.getTarget());

      menuItem.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.equals(oldValue)) {
          nsMenuItem.setTitle(newValue);
        }
      });

      NSCleaner.CLEANER.register(menuItem, new NSObjectCleaner(nsMenuItem));
      if (foundationCallback != VOID_CALLBACK) {
        NSCleaner.CLEANER.register(menuItem, new FoundationCallbackCleaner(foundationCallback));
      }

      return nsMenuItem;
    }
  }

  private static FoundationCallbackFactory.FoundationCallback getFoundationCallback(MenuItem menuItem) {
    EventHandler<ActionEvent> onAction = menuItem.getOnAction();
    if (onAction == null) {
      return VOID_CALLBACK;
    }
    return FoundationCallbackFactory.instance().registerCallback(id -> onAction.handle(new ActionEvent()));
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
}
