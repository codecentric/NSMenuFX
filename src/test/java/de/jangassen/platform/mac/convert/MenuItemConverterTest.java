package de.jangassen.platform.mac.convert;

import de.jangassen.jfa.appkit.NSMenuItem;
import javafx.scene.control.MenuItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

class MenuItemConverterTest {

  @Test
  void testUnnamedItem() {
    MenuItem item = new MenuItem();

    NSMenuItem nsMenuItem = MenuItemConverter.convert(item);

    Assertions.assertEquals("", nsMenuItem.title());
  }

  @Test
  void testItemWithTitle() {
    MenuItem item = new MenuItem("test");

    NSMenuItem nsMenuItem = MenuItemConverter.convert(item);

    Assertions.assertEquals("test", nsMenuItem.title());
  }

  @Test
  void testItemWithAction() {
    AtomicBoolean wasHandled = new AtomicBoolean(false);

    MenuItem item = new MenuItem("test");
    item.setOnAction(actionEvent -> wasHandled.set(true));

    NSMenuItem nsMenuItem = MenuItemConverter.convert(item);

    item.fire();
    Assertions.assertTrue(wasHandled.get());
  }
}