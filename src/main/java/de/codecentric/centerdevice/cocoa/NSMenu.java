package de.codecentric.centerdevice.cocoa;

import java.util.function.Consumer;

/**
 * Created by jan on 13/03/16.
 */
public class NSMenu extends NSObject {
  protected NSMenu(long id) {
    super(id);
  }

  public static native NSMenu alloc();

  public native NSMenu init(String title);

  public native String title();

  public native void removeAllItems();

  public native void addItem(NSMenuItem item);

  public native void insertItem(NSMenuItem item, int index);

  public native NSMenuItem itemAtIndex(int index);
}
