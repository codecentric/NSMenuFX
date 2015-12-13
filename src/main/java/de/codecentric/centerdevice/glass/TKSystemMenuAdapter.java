package de.codecentric.centerdevice.glass;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;

import com.sun.glass.ui.Application;
import com.sun.glass.ui.Menu;
import com.sun.glass.ui.MenuBar;
import com.sun.glass.ui.MenuItem;
import com.sun.javafx.menu.MenuBase;
import com.sun.javafx.tk.TKSystemMenu;
import com.sun.javafx.tk.Toolkit;

import de.codecentric.centerdevice.util.ReflectionUtils;

public class TKSystemMenuAdapter {
	private static final String GLASS_SYSTEM_MENU_BAR = "glassSystemMenuBar";
	private static final String INSERT_MENU = "insertMenu";

	private static final int APPLE_MENU_INDEX = 0;

	private TKSystemMenu systemMenu;

	private MethodHandle insertMenu;
	private MethodHandle getSystemMenuBar;
	private MethodHandle setSystemMenuBar;

	public TKSystemMenuAdapter() throws ReflectiveOperationException {
		systemMenu = getSystemMenu();

		Field glassSystemMenuBar = ReflectionUtils.getAccessibleField(systemMenu, GLASS_SYSTEM_MENU_BAR);
		getSystemMenuBar = MethodHandles.lookup().unreflectGetter(glassSystemMenuBar);
		setSystemMenuBar = MethodHandles.lookup().unreflectSetter(glassSystemMenuBar);

		insertMenu = ReflectionUtils.getHandle(systemMenu, INSERT_MENU, Menu.class, MenuBase.class, int.class);
	}

	private static TKSystemMenu getSystemMenu() {
		return Toolkit.getToolkit().getSystemMenu();
	}

	public void setAppleMenu(MenuBase menu) throws Throwable {
		MenuBar glassSystemMenuBar = getMenuBar();
		if (glassSystemMenuBar == null) {
			createMenuBar();
		} else {
			removeMenu(glassSystemMenuBar, 0);
		}
		insertMenu(menu, APPLE_MENU_INDEX);
	}

	private void insertMenu(MenuBase menu, int index) throws Throwable {
		insertMenu.invoke(systemMenu, null, menu, index);
	}

	private void createMenuBar() throws Throwable {
		setSystemMenuBar.invoke(systemMenu, Application.GetApplication().createMenuBar());
	}

	public MenuBar getMenuBar() throws Throwable {
		return (MenuBar) getSystemMenuBar.invoke(systemMenu);
	}

	protected void setMenuBar(MenuBar bar) throws Throwable {
		setSystemMenuBar.invoke(systemMenu, bar);
	}

	public void removeMenu(MenuBar glassSystemMenuBar, int index) {
		if (glassSystemMenuBar.getMenus().size() <= index) {
			return;
		}
		clearMenu(glassSystemMenuBar.getMenus().get(index));
		glassSystemMenuBar.remove(index);
	}

	private void clearMenu(Menu menu) {
		for (int i = menu.getItems().size() - 1; i >= 0; i--) {
			Object o = menu.getItems().get(i);

			if (o instanceof MenuItem) {
				((MenuItem) o).setCallback(null);
			} else if (o instanceof Menu) {
				clearMenu((Menu) o);
			}
		}
		menu.setEventHandler(null);
	}
}
