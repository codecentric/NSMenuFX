package de.codecentric.centerdevice.cleanup;

import java.lang.ref.Cleaner;

public final class NSCleaner {
  private NSCleaner() {}

  public static final Cleaner CLEANER = Cleaner.create();
}
