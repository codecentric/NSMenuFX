package de.codecentric.centerdevice.platform.osx;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public interface NativeMenuBar {

	public abstract void setMenuBar(MenuBar bar);

	public abstract void renameMenu(int menuIndex, String title);

	public abstract void renameMenuItem(int menuIndex, int menuItemIndex,
			String title);

	public abstract void insertMenuItems(int menuIndex, int menuItemIndex,
			MenuItem... jfxItems);

	public abstract void insertMenuItem(int menuIndex, int menuItemIndex,
			MenuItem jfxItem);

	public abstract void removeMenuItem(int menuIndex, int menuItemIndex);

	public abstract void replaceMenuItem(int menuIndex, int menuItemIndex,
			MenuItem jfxItem);

	public abstract void addMenuItems(int menuIndex, MenuItem... jfxItems);

	public abstract void addMenuItem(int menuIndex, MenuItem jfxItem);

	public abstract void addMenu(Menu jfxMenu);

	public abstract MenuBar getMenuBar();

}