package de.codecentric.centerdevice.cleanup;

import de.jangassen.jfa.FoundationCallbackFactory;

public class FoundationCallbackCleaner implements Runnable {
  private final FoundationCallbackFactory.FoundationCallback foundationCallback;

  public FoundationCallbackCleaner(FoundationCallbackFactory.FoundationCallback foundationCallback) {
    this.foundationCallback = foundationCallback;
  }

  @Override
  public void run() {
    FoundationCallbackFactory.instance().unregister(foundationCallback);
  }
}
