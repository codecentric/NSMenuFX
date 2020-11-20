package de.codecentric.centerdevice.cleanup;

import de.jangassen.jfa.appkit.NSObject;

public class NSObjectCleaner implements Runnable {
  private final NSObject object;

  public NSObjectCleaner(NSObject object) {
    this.object = object;
  }

  @Override
  public void run() {
    object.release();
  }
}
