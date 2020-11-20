package de.codecentric.centerdevice.cocoa;

import de.jangassen.jfa.appkit.NSMenu;
import de.jangassen.jfa.appkit.NSMenuItem;
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
    Assert.assertFalse(item.hasSubmenu());
  }

  @Test
  public void InitMenuItem() {
    NSMenuItem item = NSMenuItem.alloc().initWithTitle("Test", null, "");

    Assert.assertNotNull(item);
    Assert.assertEquals("Test", item.title());
  }

  @Test
  public void AddSubmenu() {
    NSMenuItem item = NSMenuItem.alloc().initWithTitle("Test", null, "");
    NSMenu submenu = NSMenu.alloc().initWithTitle("test");
    item.setSubmenu(submenu);

    Assert.assertNotNull(item);
    Assert.assertTrue(item.hasSubmenu());
  }
}
