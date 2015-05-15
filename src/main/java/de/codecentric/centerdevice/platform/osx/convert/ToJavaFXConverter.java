package de.codecentric.centerdevice.platform.osx.convert;

import java.util.ArrayList;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyCombination.Modifier;

import org.eclipse.swt.internal.cocoa.NSArray;
import org.eclipse.swt.internal.cocoa.NSMenu;
import org.eclipse.swt.internal.cocoa.NSMenuItem;
import org.eclipse.swt.internal.cocoa.OS;

import de.codecentric.centerdevice.platform.osx.action.NativeAction;

public class ToJavaFXConverter {

	private static final long sel_isEnabled = OS.sel_registerName("isEnabled");

	public Menu convert(NSMenu menu) {
		Menu jfxMenu = new Menu(menu.title().getString());
		NSArray itemArray = menu.itemArray();

		for (long i = 0; i < itemArray.count(); i++) {
			NSMenuItem item = new NSMenuItem(itemArray.objectAtIndex(i));
			if (item.submenu() != null) {
				jfxMenu.getItems().add(convert(item.submenu()));
			} else {
				jfxMenu.getItems().add(convert(item));
			}
		}

		return jfxMenu;
	}

	public MenuItem convert(NSMenuItem item) {
		if (item.isSeparatorItem()) {
			return new SeparatorMenuItem();
		}

		MenuItem jfxItem = new MenuItem(item.title().getString());
		jfxItem.setVisible(!item.isHidden());
		jfxItem.setDisable(!isEnabled(item));

		if (item.action() > 0) {
			jfxItem.setOnAction(new NativeAction(item.action(), item.target()));
		}

		if (item.keyEquivalent() != null) {
			jfxItem.setAccelerator(getKeyCombination(item));
		}

		return jfxItem;
	}

	private boolean isEnabled(NSMenuItem item) {
		return OS.objc_msgSend_bool(item.id, sel_isEnabled);
	}

	private KeyCodeCombination getKeyCombination(NSMenuItem item) {
		KeyCode keyCode = KeyCode.getKeyCode(item.keyEquivalent().getString()
				.toUpperCase());
		if (keyCode == null) {
			return null;
		}

		return new KeyCodeCombination(keyCode, getModifiers(item));
	}

	private Modifier[] getModifiers(NSMenuItem item) {
		ArrayList<Modifier> modifiers = new ArrayList<Modifier>();
		if ((item.keyEquivalentModifierMask() & OS.NSAlternateKeyMask) > 0) {
			modifiers.add(KeyCombination.ALT_DOWN);
		}
		if ((item.keyEquivalentModifierMask() & OS.NSShiftKeyMask) > 0) {
			modifiers.add(KeyCombination.SHIFT_DOWN);
		}
		if ((item.keyEquivalentModifierMask() & OS.NSCommandKeyMask) > 0) {
			modifiers.add(KeyCombination.META_DOWN);
		}
		if ((item.keyEquivalentModifierMask() & OS.NSControlKeyMask) > 0) {
			modifiers.add(KeyCombination.CONTROL_DOWN);
		}
		Modifier[] modifierArray = modifiers.toArray(new Modifier[modifiers
				.size()]);
		return modifierArray;
	}

}
