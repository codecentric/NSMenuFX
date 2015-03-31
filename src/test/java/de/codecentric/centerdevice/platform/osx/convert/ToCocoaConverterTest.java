package de.codecentric.centerdevice.platform.osx.convert;

import static org.junit.Assert.assertEquals;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;

import org.eclipse.swt.internal.cocoa.NSMenu;
import org.eclipse.swt.internal.cocoa.NSMenuItem;
import org.eclipse.swt.internal.cocoa.OS;
import org.junit.Test;

public class ToCocoaConverterTest {

	private static final String MENU_ITEM_TITLE = "MenuItemTitle";
	private static final String MENU_TITLE = "MenuTitle";

	private final ToCocoaConverter converter = new ToCocoaConverter();

	private final MenuItem menuItem = new MenuItem(MENU_ITEM_TITLE);
	private final Menu menu = new Menu(MENU_TITLE);

	@Test
	public void convertMenuItemTitle() {
		NSMenuItem cocoaItem = converter.convert(menuItem);

		assertEquals(MENU_ITEM_TITLE, cocoaItem.title().getString());

		cocoaItem.release();
	}

	@Test
	public void convertKeyCombination() {
		menuItem.setAccelerator(KeyCombination.valueOf("Shift+Meta+n"));

		NSMenuItem cocoaItem = converter.convert(menuItem);

		assertEquals("n", cocoaItem.keyEquivalent().getString());
		assertEquals(OS.NSShiftKeyMask | OS.NSCommandKeyMask,
				cocoaItem.keyEquivalentModifierMask());

		cocoaItem.release();
	}

	@Test
	public void convertMenu() {
		NSMenu cocoaMenu = converter.convertMenu(menu);

		assertEquals(MENU_TITLE, cocoaMenu.title().getString());

		cocoaMenu.release();
	}

	@Test
	public void convertSubMenu() {
		menu.getItems().add(menuItem);

		NSMenu cocoaMenu = converter.convertMenu(menu);

		assertEquals(1, cocoaMenu.itemArray().count());
		assertEquals(MENU_ITEM_TITLE, new NSMenuItem(cocoaMenu.itemAtIndex(0))
				.title().getString());

		cocoaMenu.release();
	}
}
