package de.codecentric.centerdevice.cocoa;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jan on 13/03/16.
 */
public class NSApplicationTest {
  @Test
  public void GetSharedApplication() {
    NSApplication app = NSApplication.sharedApplication();

    Assert.assertNotNull(app);
    Assert.assertNotEquals(0, app.getId());
  }
}
