package de.codecentric.centerdevice.cocoa;

import de.jangassen.jfa.FoundationProxy;
import de.jangassen.jfa.appkit.NSMenu;
import de.jangassen.jfa.appkit.NSMenuItem;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jan on 13/03/16.
 */
public class NSMenuTest {
  @Test
  public void CreateMenu() {
    NSMenu menu = FoundationProxy.alloc(NSMenu.class).initWithTitle("title");

    Assert.assertNotNull(menu);
    Assert.assertEquals("title", menu.title());
  }

  @Test
  public void AddMenuItem() {
    NSMenu menu = FoundationProxy.alloc(NSMenu.class).initWithTitle("title");
    NSMenuItem menuItem = FoundationProxy.alloc(NSMenuItem.class).initWithTitle("title", null, "");

    menu.addItem(menuItem);
    NSMenuItem item = menu.itemAtIndex(0);

    Assert.assertEquals("title", menuItem.title());
  }

  @Test
  public void InsertMenuItem() {
    NSMenu menu = FoundationProxy.alloc(NSMenu.class).initWithTitle("title");
    NSMenuItem menuItem = FoundationProxy.alloc(NSMenuItem.class).initWithTitle("title", null, "");

    menu.insertItem(menuItem, 0);
    NSMenuItem item = menu.itemAtIndex(0);

    Assert.assertEquals("title", menuItem.title());
  }

  @Test
  public void RemoveMenuItem() {
    NSMenu menu = FoundationProxy.alloc(NSMenu.class).initWithTitle("title");
    NSMenuItem menuItem = FoundationProxy.alloc(NSMenuItem.class).initWithTitle("title", null, "");

    menu.addItem(menuItem);
    menu.removeItem(menuItem);

    Assert.assertEquals(0, menu.numberOfItems());
  }

  @Test
  public void RemoveMenuItemAtIndex() {
    NSMenu menu = FoundationProxy.alloc(NSMenu.class).initWithTitle("title");
    NSMenuItem menuItem = FoundationProxy.alloc(NSMenuItem.class).initWithTitle("title", null, "");

    menu.addItem(menuItem);
    menu.removeItemAtIndex(0);

    Assert.assertEquals(0, menu.numberOfItems());
  }

  @Test
  public void RemoveAllItems() {
    NSMenu menu = FoundationProxy.alloc(NSMenu.class).initWithTitle("title");
    NSMenuItem menuItem = FoundationProxy.alloc(NSMenuItem.class).initWithTitle("title", null, "");

    menu.addItem(menuItem);
    menu.removeAllItems();

    Assert.assertEquals(0, menu.numberOfItems());
  }
}
