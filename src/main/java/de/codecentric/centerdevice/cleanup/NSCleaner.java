package de.codecentric.centerdevice.cleanup;

import de.jangassen.jfa.FoundationCallbackFactory;
import de.jangassen.jfa.appkit.NSObject;

import java.lang.ref.Cleaner;

public final class NSCleaner {
  private NSCleaner() {}

  public static final Cleaner CLEANER = Cleaner.create();

  public static void register(Object obj, NSObject nsObject) {
    CLEANER.register(obj, nsObject::release);
  }

  public static void register(Object obj, FoundationCallbackFactory.FoundationCallback callback) {
    CLEANER.register(obj, () -> FoundationCallbackFactory.instance().unregister(callback));
  }
}
