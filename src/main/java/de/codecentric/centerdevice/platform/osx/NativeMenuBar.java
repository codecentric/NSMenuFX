package de.codecentric.centerdevice.platform.osx;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public interface NativeMenuBar {

	public void setMenuBar(MenuBar bar);

	public void renameMenu(int menuIndex, String title);

	public void renameMenuItem(int menuIndex, int menuItemIndex, String title);

	public void insertMenuItems(int menuIndex, int menuItemIndex,
			MenuItem... jfxItems);

	public void insertMenuItem(int menuIndex, int menuItemIndex,
			MenuItem jfxItem);

	public void removeMenuItem(int menuIndex, int menuItemIndex);

	public void replaceMenuItem(int menuIndex, int menuItemIndex,
			MenuItem jfxItem);

	public void addMenuItems(int menuIndex, MenuItem... jfxItems);

	public void addMenuItem(int menuIndex, MenuItem jfxItem);

	public void addMenu(Menu jfxMenu);

	public MenuBar getMenuBar();

}