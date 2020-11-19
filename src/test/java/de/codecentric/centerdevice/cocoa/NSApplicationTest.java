package de.codecentric.centerdevice.cocoa;

import de.jangassen.jfa.appkit.NSApplication;
import org.junit.Assert;
import org.junit.Test;

public class NSApplicationTest {
  @Test
  public void GetSharedApplication() {
    NSApplication app = NSApplication.sharedApplication();

    Assert.assertNotNull(app);
  }
}
