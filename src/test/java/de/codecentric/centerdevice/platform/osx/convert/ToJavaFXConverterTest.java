package de.codecentric.centerdevice.platform.osx.convert;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import org.eclipse.swt.internal.cocoa.NSMenu;
import org.eclipse.swt.internal.cocoa.NSMenuItem;
import org.eclipse.swt.internal.cocoa.NSString;
import org.eclipse.swt.internal.cocoa.OS;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ToJavaFXConverterTest {

	private static final String MENU_ITEM_TITLE = "MenuItemTitle";
	private static final String MENU_TITLE = "MenuTitle";

	private final ToJavaFXConverter converter = new ToJavaFXConverter();
	private final List<NSString> strings = new ArrayList<NSString>();

	private final NSMenuItem menuItem = (NSMenuItem) new NSMenuItem().alloc();
	private final NSMenu menu = (NSMenu) new NSMenu().alloc();

	@Before
	public void setUp() {
		menuItem.setTitle(getAutoreleasedNSString(MENU_ITEM_TITLE));
		menu.setTitle(getAutoreleasedNSString(MENU_TITLE));
	}

	@After
	public void cleanUp() {
		menuItem.release();
		menu.release();

		strings.forEach(s -> s.release());
	}

	@Test
	public void convertMenuItemTitle() {
		MenuItem fxItem = converter.convert(menuItem);

		assertEquals(MENU_ITEM_TITLE, fxItem.getText());
	}

	@Test
	public void convertKeyCombination() {
		menuItem.setKeyEquivalent(getAutoreleasedNSString("n"));
		menuItem.setKeyEquivalentModifierMask(OS.NSShiftKeyMask
				| OS.NSCommandKeyMask);

		MenuItem fxItem = converter.convert(menuItem);

		assertEquals("Shift+Meta+N", fxItem.getAccelerator().getName());
	}

	@Test
	public void convertMenu() {
		Menu fxMenu = converter.convert(menu);

		assertEquals(MENU_TITLE, fxMenu.getText());
	}

	@Test
	public void convertSubMenu() {
		menu.addItem(menuItem);

		Menu fxMenu = converter.convert(menu);

		assertEquals(1, fxMenu.getItems().size());
		assertEquals(MENU_ITEM_TITLE, fxMenu.getItems().get(0).getText());
	}

	private NSString getAutoreleasedNSString(String value) {
		NSString nsString = NSString.stringWith(value);
		strings.add(nsString);
		return nsString;
	}
}
