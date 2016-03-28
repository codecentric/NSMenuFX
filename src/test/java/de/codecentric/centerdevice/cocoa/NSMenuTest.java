package de.codecentric.centerdevice.cocoa;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jan on 13/03/16.
 */
public class NSMenuTest {
  @Test
  public void CreateMenu() {
    NSMenu menu = NSMenu.alloc().init("title");

    Assert.assertNotNull(menu);
    Assert.assertNotEquals(0, menu.getId());
    Assert.assertEquals("title", menu.title());
  }

  @Test
  public void AddMenuItem() {
    NSMenu menu = NSMenu.alloc().init("title");
    NSMenuItem menuItem = NSMenuItem.alloc().init("title", null, "");

    menu.addItem(menuItem);
    NSMenuItem item = menu.itemAtIndex(0);

    Assert.assertEquals(menuItem.getId(), item.getId());
    Assert.assertEquals("title", menuItem.title());
  }

  @Test
  public void InsertMenuItem() {
    NSMenu menu = NSMenu.alloc().init("title");
    NSMenuItem menuItem = NSMenuItem.alloc().init("title", null, "");

    menu.insertItem(menuItem, 0);
    NSMenuItem item = menu.itemAtIndex(0);

    Assert.assertEquals(menuItem.getId(), item.getId());
    Assert.assertEquals("title", menuItem.title());
  }

  @Test
  public void RemoveMenuItem() {
    NSMenu menu = NSMenu.alloc().init("title");
    NSMenuItem menuItem = NSMenuItem.alloc().init("title", null, "");

    menu.addItem(menuItem);
    menu.removeItem(menuItem);

    Assert.assertEquals(0, menu.numberOfItems());
  }

  @Test
  public void RemoveMenuItemAtIndex() {
    NSMenu menu = NSMenu.alloc().init("title");
    NSMenuItem menuItem = NSMenuItem.alloc().init("title", null, "");

    menu.addItem(menuItem);
    menu.removeItemAtIndex(0);

    Assert.assertEquals(0, menu.numberOfItems());
  }

  @Test
  public void RemoveAllItems() {
    NSMenu menu = NSMenu.alloc().init("title");
    NSMenuItem menuItem = NSMenuItem.alloc().init("title", null, "");

    menu.addItem(menuItem);
    menu.removeAllItems();

    Assert.assertEquals(0, menu.numberOfItems());
  }
}
