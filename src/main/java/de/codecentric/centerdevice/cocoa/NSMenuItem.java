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
}
