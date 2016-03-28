package de.codecentric.centerdevice.cocoa;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jan on 12/03/16.
 */
public class NSMenuItemTest {

  @Test
  public void CreateMenuItem() {
    NSMenuItem item = NSMenuItem.alloc();

    Assert.assertNotNull(item);
    Assert.assertNotEquals(0, item.getId());
    Assert.assertFalse(item.hasSubmenu());
  }

  @Test
  public void InitMenuItem() {
    NSMenuItem item = NSMenuItem.alloc().init("Test", (v) -> System.out.println("test"),  "");

    Assert.assertNotNull(item);
    Assert.assertNotEquals(0, item.getId());
    Assert.assertEquals("Test", item.title());
  }

  @Test
  public void AddSubmenu() {
    NSMenuItem item = NSMenuItem.alloc().init("Test", (v) -> System.out.println("test"),  "");
    NSMenu submenu = NSMenu.alloc().init("test");
    item.setSubmenu(submenu);

    Assert.assertNotNull(item);
    Assert.assertNotEquals(0, item.getId());
    Assert.assertTrue(item.hasSubmenu());
    Assert.assertEquals(submenu.getId(), item.submenu().getId());
  }
}
