package de.codecentric.centerdevice.cocoa;

public class NSApplication extends NSObject {

  protected NSApplication(long id) {
    super(id);
  }

  public static native NSApplication sharedApplication();

  public native void hide();

  public native void unhideAllApplications();

  public native void hideOtherApplications();

  public native void setWindowsMenu(NSMenu windowsMenu);

  public native NSMenu windowsMenu();

  public native void setHelpMenu(NSMenu helpMenu);

  public native NSMenu helpMenu();

  public native NSMenu mainMenu();

  public native void setMainMenu(NSMenu mainMenu);
}
