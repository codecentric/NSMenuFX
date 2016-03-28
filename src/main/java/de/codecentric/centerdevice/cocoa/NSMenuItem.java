package de.codecentric.centerdevice.cocoa;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class NSMenuItem extends NSObject {

  protected NSMenuItem(long id) {
    super(id);
  }

  public static native NSMenuItem alloc();

  public NSMenuItem init(String title) {
    return init(title, null, "");
  }

  public native NSMenuItem init(String title, EventHandler<ActionEvent> callback, String keyEquivalent);

  public native String title();

  public native boolean hasSubmenu();

  public native void setSubmenu(NSMenu menu);

  public native NSMenu submenu();

  public native void setTitle(String title);

  private native void release();

  @Override protected void finalize() throws Throwable {
    super.finalize();
    release();
  }
}
