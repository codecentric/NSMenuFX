package de.jangassen.platform.mac;

import de.jangassen.jfa.JavaToObjc;
import de.jangassen.jfa.ObjcToJava;
import de.jangassen.jfa.appkit.*;
import de.jangassen.jfa.foundation.Foundation;
import de.jangassen.jfa.foundation.ID;
import de.jangassen.platform.NativeAdapter;
import de.jangassen.platform.mac.convert.MenuConverter;
import javafx.application.Platform;
import javafx.scene.control.Menu;

public class MacNativeAdapter implements NativeAdapter {

  private final NSApplication sharedApplication;
  private final NSWorkspace sharedWorkspace;

  private boolean forceQuitOnCmdQ = true;

  public MacNativeAdapter() {
    sharedApplication = NSApplication.sharedApplication();
    sharedWorkspace = NSWorkspace.sharedWorkspace();
  }

  public static boolean isAvailable() {
    return Foundation.isAvailable();
  }

  public void setApplicationMenu(Menu menu) {
    NSMenu nsMenu = sharedApplication.mainMenu();
    NSMenuItem mainMenu = NSMenuItem.alloc().initWithTitle("", null, "");
    mainMenu.setSubmenu(MenuConverter.convert(menu));

    nsMenu.removeItemAtIndex(0);
    nsMenu.insertItem(mainMenu, 0);
  }

  public void hide() {
    sharedApplication.hide(ID.NIL);
  }

  public void hideOtherApplications() {
    sharedWorkspace.hideOtherApplications();
  }

  public void showAllWindows() {
    sharedApplication.unhide(ID.NIL);
  }

  public void quit() {
    if (forceQuitOnCmdQ) {
      Platform.exit();
    }
  }

  public void setForceQuitOnCmdQ(boolean forceQuit) {
    this.forceQuitOnCmdQ = forceQuit;
  }

  public void setDocIconMenu(Menu menu) {
    NSApplicationDelegate delegate = sharedApplication.delegate();
    NSMenu convert = MenuConverter.convert(menu);

    ApplicationDelegateWithMenu foundationProxy = new ApplicationDelegateWithMenu(delegate, convert);

    ID mappedObject = JavaToObjc.map(foundationProxy);
    sharedApplication.setDelegate(ObjcToJava.map(mappedObject, NSApplicationDelegate.class));
  }
}
