package de.codecentric.centerdevice.platform.osx.convert;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyCombination.ModifierValue;

import org.eclipse.swt.internal.Callback;
import org.eclipse.swt.internal.cocoa.NSMenu;
import org.eclipse.swt.internal.cocoa.NSMenuItem;
import org.eclipse.swt.internal.cocoa.NSObject;
import org.eclipse.swt.internal.cocoa.NSString;
import org.eclipse.swt.internal.cocoa.OS;
import org.eclipse.swt.internal.cocoa.SWTApplicationDelegate;
import org.eclipse.swt.internal.cocoa.id;

import de.codecentric.centerdevice.platform.osx.action.NativeAction;

public class ToCocoaConverter {

	private static final String SWT_APPLICATION_DELEGATE = "SWTApplicationDelegate";

	private static long counter = 0;
	private static long delegateClass = 0;

	private class MenuHookObject {

		private final EventHandler<ActionEvent> callback;

		public MenuHookObject(EventHandler<ActionEvent> callback) {
			this.callback = callback;
		}

		@SuppressWarnings("unused")
		public int actionProc(int id, int sel, int arg0) {
			return (int) actionProc((long) id, (long) sel, (long) arg0);
		}

		public long actionProc(long id, long sel, long arg0) {
			callback.handle(new ActionEvent());
			return 0;
		}
	}

	public NSMenuItem convert(MenuItem jfxItem) {
		if (jfxItem instanceof Menu) {
			return createSubMenu((Menu) jfxItem);
		} else {
			return convertMenuItem(jfxItem);
		}
	}

	NSMenu convertMenu(Menu jfxMenu) {
		NSMenu menu = (NSMenu) new NSMenu().alloc();
		menu.setTitle(NSString.stringWith(jfxMenu.getText()));

		for (MenuItem jfxMenuItem : jfxMenu.getItems()) {
			NSMenuItem nsMenuItem = convert(jfxMenuItem);
			menu.addItem(nsMenuItem);
			release(nsMenuItem);
		}

		return menu;
	}

	public void release(NSMenuItem nsMenuItem) {
		if (!nsMenuItem.isSeparatorItem()) {
			nsMenuItem.release();
		}
	}

	private NSMenuItem createSubMenu(Menu jfxMenu) {
		NSMenuItem menuItem = (NSMenuItem) (new NSMenuItem().alloc());
		menuItem.setTitle(NSString.stringWith(jfxMenu.getText()));
		NSMenu submenu = convertMenu(jfxMenu);
		menuItem.setSubmenu(submenu);
		submenu.release();
		return menuItem;
	}

	private NSMenuItem convertMenuItem(MenuItem jfxItem) {
		if (jfxItem instanceof SeparatorMenuItem) {
			return NSMenuItem.separatorItem();
		}

		NSMenuItem item = (NSMenuItem) (new NSMenuItem().alloc());
		item.setTitle(NSString.stringWith(jfxItem.getText()));
		EventHandler<ActionEvent> onAction = jfxItem.getOnAction();

		if (onAction != null) {
			addAction(item, onAction);
		}

		item.setEnabled(!jfxItem.isDisable());
		item.setHidden(!jfxItem.isVisible());

		KeyCombination accelerator = jfxItem.getAccelerator();
		if (accelerator != null && accelerator instanceof KeyCodeCombination) {
			KeyCodeCombination combination = (KeyCodeCombination) accelerator;
			item.setKeyEquivalent(NSString.stringWith(combination.getCode()
					.getName().toLowerCase()));
			item.setKeyEquivalentModifierMask(getModifierMask(combination));
		}

		return item;
	}

	private void addAction(NSMenuItem item, EventHandler<ActionEvent> onAction) {
		long selector;
		id target;

		if (onAction instanceof NativeAction) {
			selector = ((NativeAction) onAction).getSelector();
			target = ((NativeAction) onAction).getTarget();
		} else {
			selector = OS.sel_registerName(createSelectorName());
			target = getDelegateObject(selector, createCallback(onAction));
		}

		item.setAction(selector);
		item.setTarget(target);
	}

	private long getModifierMask(KeyCodeCombination combination) {
		long keyMask = 0;
		if (combination.getAlt() == ModifierValue.DOWN) {
			keyMask |= OS.NSAlternateKeyMask;
		}
		if (combination.getShift() == ModifierValue.DOWN) {
			keyMask |= OS.NSShiftKeyMask;
		}
		if (combination.getMeta() == ModifierValue.DOWN) {
			keyMask |= OS.NSCommandKeyMask;
		}
		if (combination.getControl() == ModifierValue.DOWN) {
			keyMask |= OS.NSControlKeyMask;
		}
		return keyMask;
	}

	private Callback createCallback(EventHandler<ActionEvent> handler) {
		return new Callback(new MenuHookObject(handler), "actionProc", 3);
	}

	private synchronized String createSelectorName() {
		ToCocoaConverter.counter++;
		return "cocoaMenuSelector" + (ToCocoaConverter.counter) + ":";
	}

	private NSObject getDelegateObject(long selector, Callback callback) {
		long delegateClass = getDelegateClass();

		OS.class_addMethod(delegateClass, selector, callback.getAddress(),
				"@:@");

		SWTApplicationDelegate delegate = new SWTApplicationDelegate();
		return delegate.alloc();
	}

	private long getDelegateClass() {
		if (delegateClass != 0) {
			return delegateClass;
		}

		delegateClass = OS.objc_lookUpClass(SWT_APPLICATION_DELEGATE);
		if (0 == delegateClass) {
			delegateClass = OS.objc_allocateClassPair(OS.class_NSApplication,
					SWT_APPLICATION_DELEGATE, 0);
			OS.objc_registerClassPair(delegateClass);
		}

		return delegateClass;
	}
}
