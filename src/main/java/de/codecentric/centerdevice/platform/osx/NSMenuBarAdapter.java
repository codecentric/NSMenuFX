package de.codecentric.centerdevice.platform.osx;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import org.eclipse.swt.internal.cocoa.NSApplication;
import org.eclipse.swt.internal.cocoa.NSArray;
import org.eclipse.swt.internal.cocoa.NSMenu;
import org.eclipse.swt.internal.cocoa.NSMenuItem;
import org.eclipse.swt.internal.cocoa.NSString;

import de.codecentric.centerdevice.platform.osx.convert.ToCocoaConverter;
import de.codecentric.centerdevice.platform.osx.convert.ToJavaFXConverter;

public class NSMenuBarAdapter {

  public static final String APPLE_LOGO = "\uF8FF";
  public static final String APPLE_TITLE = "Apple";

  private final NSMenu mainMenu;
  private final ToJavaFXConverter toJavaFX;
  private final ToCocoaConverter toCocoa;

  public NSMenuBarAdapter() {
    NSApplication sharedApplication = NSApplication.sharedApplication();
    mainMenu = sharedApplication.mainMenu();

    toJavaFX = new ToJavaFXConverter();
    toCocoa = new ToCocoaConverter();
  }

  public void setMenuBar(MenuBar bar) {
    clear(mainMenu);

    if (bar.getMenus().isEmpty()) {
      return;
    }

    for (Menu menu : bar.getMenus()) {
      addMenu(menu);
    }

    String applicationMenuTitle = bar.getMenus().get(0).getText();
    if (!isAppleConstant(applicationMenuTitle)) {
      // Fix to ensure first menu title is displayed correctly
      renameApplicationMenu("");
      renameApplicationMenu(applicationMenuTitle);
    }
  }

  private boolean isAppleConstant(String value) {
    return APPLE_LOGO.equals(value) || APPLE_TITLE.equals(value);
  }

  public void renameApplicationMenu(String title) {
    renameMenu(0, title);
  }

  public void renameMenu(int menuIndex, String title) {
    NSMenu menu = getMenuByIndex(0);
    menu.setTitle(NSString.stringWith(title));
  }

  public void renameMenuItem(int menuIndex, int menuItemIndex, String title) {
    NSMenu menu = getMenuByIndex(menuIndex);

    long newMenuItemIndex = getPositiveIndex(menuItemIndex, menu.itemArray().count());
    NSMenuItem menuItem = menu.itemAtIndex(newMenuItemIndex);

    menuItem.setTitle(NSString.stringWith(title));
  }

  public void insertMenuItems(int menuIndex, int menuItemIndex, MenuItem... jfxItems) {
    for (int i = 0; i < jfxItems.length; i++) {
      insertMenuItem(menuIndex, menuItemIndex + i, jfxItems[i]);
    }
  }

  public void insertMenuItem(int menuIndex, int menuItemIndex, MenuItem jfxItem) {
    NSMenu menu = getMenuByIndex(menuIndex);
    NSMenuItem menuItem = toCocoa.convert(jfxItem);
    menu.insertItem(menuItem, getPositiveIndex(menuItemIndex, menu.itemArray().count()));
    toCocoa.release(menuItem);
  }

  public void removeMenuItem(int menuIndex, int menuItemIndex) {
    NSMenu menu = getMenuByIndex(menuIndex);
    menu.removeItemAtIndex(getPositiveIndex(menuItemIndex, menu.itemArray().count()));
  }

  public void replaceMenuItem(int menuIndex, int menuItemIndex, MenuItem jfxItem) {
    removeMenuItem(menuIndex, menuItemIndex);
    insertMenuItem(menuIndex, menuItemIndex, jfxItem);
  }

  public void addMenuItems(int menuIndex, MenuItem... jfxItems) {
    if (jfxItems.length == 0) {
      return;
    }

    NSMenu menu = getMenuByIndex(menuIndex);
    for (MenuItem menuItem : jfxItems) {
      addMenuItems(menu, menuItem);
    }
  }

  public void addMenuItem(int menuIndex, MenuItem jfxItem) {
    addMenuItem(getMenuByIndex(menuIndex), jfxItem);
  }

  private void addMenuItems(NSMenu menu, MenuItem... jfxItem) {
    for (MenuItem menuItem : jfxItem) {
      addMenuItem(menu, menuItem);
    }
  }

  private void addMenuItem(NSMenu target, MenuItem menuItem) {
    NSMenuItem nsMenuItem = toCocoa.convert(menuItem);
    target.addItem(nsMenuItem);
    toCocoa.release(nsMenuItem);
  }

  public void addMenu(Menu jfxMenu) {
    addMenuItem(mainMenu, jfxMenu);
  }

  private NSMenu getMenuByIndex(int menuIndex) {
    long count = mainMenu.itemArray().count();
    if (menuIndex >= count) {
      throw new IndexOutOfBoundsException();
    }
    long newMenuIndex = getPositiveIndex(menuIndex, count);
    return mainMenu.itemAtIndex(newMenuIndex).submenu();
  }

  private long getPositiveIndex(int menuIndex, long count) {
    return (menuIndex < 0) ? menuIndex + count : menuIndex;
  }

  private void clear(NSMenu submenu) {
    NSArray itemArray = submenu.itemArray();
    for (long i = itemArray.count() - 1; i >= 0; i--) {
      submenu.removeItemAtIndex(i);
    }
  }

  public MenuBar getMenuBar() {
    MenuBar bar = new MenuBar();
    NSArray itemArray = mainMenu.itemArray();
    for (long i = 0; i < itemArray.count(); i++) {
      NSMenuItem item = new NSMenuItem(itemArray.objectAtIndex(i));
      bar.getMenus().add(toJavaFX.convert(item.submenu()));
    }

    return bar;
  }
}
