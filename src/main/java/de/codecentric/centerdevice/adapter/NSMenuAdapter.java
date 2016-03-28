package de.codecentric.centerdevice.adapter;

import de.codecentric.centerdevice.cocoa.NSApplication;
import de.codecentric.centerdevice.cocoa.NSMenu;

/**
 * Created by jan on 20/03/16.
 */
public class NSMenuAdapter {
  public void setMenuBar(NSMenuProvider menuProvider) {
    NSMenu mainMenu = NSApplication.sharedApplication().mainMenu();

  }

  public void addMenu(NSMenuItemProvider menuProvider) {
    NSMenu mainMenu = NSApplication.sharedApplication().mainMenu();

    mainMenu.addItem(menuProvider.getNsMenuItem());
  }
}
