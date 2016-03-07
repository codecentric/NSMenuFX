package de.codecentric.centerdevice.platform;


public class NativeAdapter {
  static {
    try {
      System.load("/Users/jan/Library/Developer/Xcode/DerivedData/NSMenuFX-bctmkrjhymrfwwggigdrbkiwlvzd/Build/Products/Debug/libNSMenuFX.dylib");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public native void hide();

  public native void unhideAllApplications();

  public native void hideOtherApplications();
}
