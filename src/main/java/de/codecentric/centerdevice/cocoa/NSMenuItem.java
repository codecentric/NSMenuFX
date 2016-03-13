package de.codecentric.centerdevice.cocoa;

import java.util.function.Consumer;

public class NSMenuItem extends NSObject {
  private Consumer<NSMenuItem> callback;

  protected NSMenuItem(long id) {
    super(id);
  }

  public static native NSMenuItem alloc();

  public NSMenuItem init(String title, Consumer<NSMenuItem> callback, String keyEquivalent) {
    this.callback = callback;
    return init(title, keyEquivalent);
  }

  private native NSMenuItem init(String title, String keyEquivalent);

  protected void callAction() {
    this.callback.accept(this);
  }

  public native String title();

  public native boolean hasSubmenu();

  public native void setSubmenu(NSMenu menu);

  public native NSMenu submenu();

  private native void release();

  @Override protected void finalize() throws Throwable {
    super.finalize();
    release();
  }
}
