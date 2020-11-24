package de.jangassen.platform.mac.convert;

import de.jangassen.jfa.appkit.NSMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MenuConverterTest {

  @Test
  void testEmptyMenu() {
    Menu menu = new Menu();

    NSMenu nsMenu = MenuConverter.convert(menu);

    Assertions.assertEquals("", nsMenu.title());
  }

  @Test
  void testMenuWithTitle() {
    Menu menu = new Menu("test");

    NSMenu nsMenu = MenuConverter.convert(menu);

    Assertions.assertEquals("test", nsMenu.title());
  }

  @Test
  void testMenuWithItems() {
    Menu menu = new Menu();
    menu.getItems().add(new MenuItem());

    NSMenu nsMenu = MenuConverter.convert(menu);

    Assertions.assertEquals(1, nsMenu.numberOfItems());
  }

  @Test
  void testUpdateTitle() {
    Menu menu = new Menu();
    NSMenu nsMenu = MenuConverter.convert(menu);

    menu.setText("test");
    Assertions.assertEquals("test", nsMenu.title());
  }

  @Test
  void testAddItem() {
    Menu menu = new Menu();
    NSMenu nsMenu = MenuConverter.convert(menu);

    menu.getItems().add(new MenuItem());
    Assertions.assertEquals(1, nsMenu.numberOfItems());
  }

  @Test
  void testRemoveItem() {
    Menu menu = new Menu();
    MenuItem item = new MenuItem();
    menu.getItems().add(item);

    NSMenu nsMenu = MenuConverter.convert(menu);

    menu.getItems().remove(item);
    Assertions.assertEquals(0, nsMenu.numberOfItems());
  }
}