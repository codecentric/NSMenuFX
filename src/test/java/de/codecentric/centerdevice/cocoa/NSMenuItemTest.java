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
  }

  @Test
  public void InitMenuItem() {
    NSMenuItem item = NSMenuItem.alloc().init("Test", (v) -> System.out.println("test"),  "");

    Assert.assertNotNull(item);
    Assert.assertNotEquals(0, item.getId());
  }
}
