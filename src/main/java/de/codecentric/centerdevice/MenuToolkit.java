package de.codecentric.centerdevice;

import com.sun.javafx.scene.control.GlobalMenuAdapter;
import com.sun.javafx.stage.StageHelper;
import com.sun.javafx.tk.Toolkit;

import de.codecentric.centerdevice.glass.GlassAdaptionException;
import de.codecentric.centerdevice.glass.MacApplicationAdapter;
import de.codecentric.centerdevice.glass.TKSystemMenuAdapter;
import de.codecentric.centerdevice.labels.LabelMaker;
import de.codecentric.centerdevice.labels.LabelName;
import de.codecentric.centerdevice.listener.MenuBarSyncListener;
import de.codecentric.centerdevice.listener.WindowMenuUpdateListener;
import de.codecentric.centerdevice.util.MenuBarUtils;
import de.codecentric.centerdevice.util.StageUtils;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Locale;

public class MenuToolkit {
	private static final String APP_NAME = "Apple";

	private static MenuToolkit toolkit = null;

	private final TKSystemMenuAdapter systemMenuAdapter;
	private final MacApplicationAdapter applicationAdapter;

	private final LabelMaker labelMaker;

	private MenuToolkit(TKSystemMenuAdapter systemMenuAdapter, MacApplicationAdapter applicationAdapter,
			LabelMaker labelMaker) {
		this.systemMenuAdapter = systemMenuAdapter;
		this.applicationAdapter = applicationAdapter;
		this.labelMaker = labelMaker;
	}

	public static MenuToolkit toolkit() {
		if (toolkit == null) {
			toolkit = createToolkit(Locale.GERMAN);
		}
		return toolkit;
	}

	private static MenuToolkit createToolkit(Locale locale) {
		if (!Toolkit.getToolkit().getSystemMenu().isSupported()) {
			return null;
		}

		try {
			return new MenuToolkit(new TKSystemMenuAdapter(), new MacApplicationAdapter(), new LabelMaker(locale));
		} catch (ReflectiveOperationException e) {
			throw new GlassAdaptionException(e);
		}
	}

	public Menu createDefaultApplicationMenu(String appName) {
		return new Menu(APP_NAME, null, createHideMenuItem(appName), createHideOthersMenuItem(),
				createUnhideAllMenuItem(), new SeparatorMenuItem(), createQuitMenuItem(appName));
	}

	public MenuItem createQuitMenuItem(String appName) {
		MenuItem quit = new MenuItem(labelMaker.getLabel(LabelName.QUIT, appName));
		quit.setOnAction(event -> applicationAdapter.quit());
		quit.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.META_DOWN));
		return quit;
	}

	public MenuItem createUnhideAllMenuItem() {
		MenuItem unhideAll = new MenuItem(labelMaker.getLabel(LabelName.SHOW_ALL));
		unhideAll.setOnAction(event -> applicationAdapter.unhideAllApplications());
		return unhideAll;
	}

	public MenuItem createHideOthersMenuItem() {
		MenuItem hideOthers = new MenuItem(labelMaker.getLabel(LabelName.HIDE_OTHERS));
		hideOthers.setOnAction(event -> applicationAdapter.hideOtherApplications());
		hideOthers.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.META_DOWN, KeyCombination.ALT_DOWN));
		return hideOthers;
	}

	public MenuItem createHideMenuItem(String appName) {
		MenuItem hide = new MenuItem(labelMaker.getLabel(LabelName.HIDE, appName));
		hide.setOnAction(event -> applicationAdapter.hide());
		hide.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.META_DOWN));
		return hide;
	}

	public MenuItem createMinimizeMenuItem() {
		MenuItem menuItem = new MenuItem("Minimize");
		menuItem.setAccelerator(new KeyCodeCombination(KeyCode.M, KeyCombination.META_DOWN));
		menuItem.setOnAction(event -> StageUtils.minimizeFocusedStage());
		return menuItem;
	}

	public MenuItem createZoomMenuItem() {
		MenuItem menuItem = new MenuItem("Zoom");
		menuItem.setOnAction(event -> StageUtils.zoomFocusedStage());
		return menuItem;
	}

	public MenuItem createCloseWindowMenuItem() {
		MenuItem menuItem = new MenuItem("Close Window");
		menuItem.setAccelerator(new KeyCodeCombination(KeyCode.W, KeyCombination.META_DOWN));
		menuItem.setOnAction(event -> StageUtils.closeCurrentStage());
		return menuItem;
	}

	public MenuItem createBringAllToFrontItem() {
		MenuItem menuItem = new MenuItem("Bring All to Front");
		menuItem.setOnAction(event -> StageUtils.bringAllToFront());
		return menuItem;
	}

	public MenuItem createCycleWindowsItem() {
		MenuItem menuItem = new MenuItem("Cycle Through Windows");
		menuItem.setAccelerator(new KeyCodeCombination(KeyCode.BACK_QUOTE, KeyCombination.META_DOWN));
		menuItem.setOnAction(event -> StageUtils.focusNextStage());
		return menuItem;
	}

	public void setApplicationMenu(Menu menu) {
		try {
			systemMenuAdapter.setAppleMenu(GlobalMenuAdapter.adapt(menu));
		} catch (Throwable e) {
			throw new GlassAdaptionException(e);
		}
	}

	public void setGlobalMenuBar(MenuBar menuBar) {
		setMenuBar(menuBar);
		MenuBarSyncListener.register(menuBar);
	}

	public void unsetGlobalMenuBar() {
		MenuBarSyncListener.unregister();
	}

	public void setMenuBar(MenuBar menuBar) {
		StageHelper.getStages().forEach(stage -> setMenuBar(stage, menuBar));
	}

	public void setMenuBar(Stage stage, MenuBar menuBar) {
		Parent parent = stage.getScene().getRoot();
		if (parent instanceof Pane) {
			setMenuBar((Pane) parent, menuBar);
		}
	}

	public void setMenuBar(Pane pane, MenuBar menuBar) {
		setApplicationMenu(extractApplicationMenu(menuBar));
		MenuBarUtils.setMenuBar(pane, menuBar);
	}

	public void autoAddWindowMenuItems(Menu menu) {
		menu.getItems().add(new SeparatorMenuItem());
		StageHelper.getStages().addListener(new WindowMenuUpdateListener(menu));
	}

	protected Menu extractApplicationMenu(MenuBar menuBar) {
		return menuBar.getMenus().get(0);
	}
}
