package de.codecentric.centerdevice.cocoa;

public class NSApplication extends NSObject {

  protected NSApplication(long id) {
    super(id);
  }

  public static native NSApplication sharedApplication();

  public native void hide();

  public native void unhideAllApplications();

  public native void hideOtherApplications();

  public native NSMenu mainMenu();
}
