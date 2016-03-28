package de.codecentric.centerdevice.cocoa;

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

  public native void setTitle(String title);

  public native void removeAllItems();

  public native void removeItem(NSMenuItem item);

  public native void removeItemAtIndex(int index);

  public native void addItem(NSMenuItem item);

  public native void insertItem(NSMenuItem item, int index);

  public native NSMenuItem itemAtIndex(int index);

  public native long numberOfItems();

  public native void setAutoenablesItems(boolean autoenablesItems);
}
