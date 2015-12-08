package de.codecentric.centerdevice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sun.javafx.scene.control.GlobalMenuAdapter;
import com.sun.javafx.tk.Toolkit;

import de.codecentric.centerdevice.glass.GlassAdaptionException;
import de.codecentric.centerdevice.glass.MacApplicationAdapter;
import de.codecentric.centerdevice.glass.TKSystemMenuAdapter;
import javafx.collections.ObservableList;
import javafx.scene.Node;
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

public class MenuToolkit {
	private static final String APPLE = "Apple";

	private static MenuToolkit toolkit = null;

	private final TKSystemMenuAdapter systemMenuAdapter;
	private final MacApplicationAdapter applicationAdapter;

	private MenuToolkit(TKSystemMenuAdapter tkSystemMenuAdapter, MacApplicationAdapter macApplicationAdapter) {
		systemMenuAdapter = tkSystemMenuAdapter;
		applicationAdapter = macApplicationAdapter;
	}

	public static MenuToolkit toolkit() {
		if (toolkit == null) {
			toolkit = createToolkit();
		}
		return toolkit;
	}

	private static MenuToolkit createToolkit() {
		if (!Toolkit.getToolkit().getSystemMenu().isSupported()) {
			return null;
		}

		try {
			return new MenuToolkit(new TKSystemMenuAdapter(), new MacApplicationAdapter());
		} catch (ReflectiveOperationException e) {
			throw new GlassAdaptionException(e);
		}
	}

	public Menu createDefaultAppleMenu(String appName) {
		return new Menu(APPLE, null, createHideMenuItem(appName), createHideOthersMenuItem(), createUnhideAllMenuItem(),
				new SeparatorMenuItem(), createQuitMenuItem(appName));
	}

	public MenuItem createQuitMenuItem(String appName) {
		MenuItem quit = new MenuItem("Quit " + appName);
		quit.setOnAction(event -> applicationAdapter.quit());
		quit.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.META_DOWN));
		return quit;
	}

	public MenuItem createUnhideAllMenuItem() {
		MenuItem unhideAll = new MenuItem("Show All");
		unhideAll.setOnAction(event -> applicationAdapter.unhideAllApplications());
		return unhideAll;
	}

	public MenuItem createHideOthersMenuItem() {
		MenuItem hideOthers = new MenuItem("Hide Others");
		hideOthers.setOnAction(event -> applicationAdapter.hideOtherApplications());
		hideOthers.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.META_DOWN, KeyCombination.ALT_DOWN));
		return hideOthers;
	}

	public MenuItem createHideMenuItem(String appName) {
		MenuItem hide = new MenuItem("Hide " + appName);
		hide.setOnAction(event -> applicationAdapter.hide());
		hide.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.META_DOWN));
		return hide;
	}

	public void setAppleMenu(Menu menu) {
		try {
			systemMenuAdapter.setAppleMenu(GlobalMenuAdapter.adapt(menu));
		} catch (Throwable e) {
			throw new GlassAdaptionException(e);
		}
	}

	public void setMenuBar(Stage stage, MenuBar menuBar) throws ReflectiveOperationException {
		Parent parent = stage.getScene().getRoot();
		if (parent instanceof Pane) {
			setMenuBar((Pane) parent, menuBar);
		}
	}

	public void setMenuBar(Pane pane, MenuBar menuBar) {
		removeExistingMenuBar(pane);
		pane.getChildren().add(createAppMenuBar(menuBar));
		setAppleMenu(extractAppleMenu(menuBar));
	}

	private MenuBar createAppMenuBar(MenuBar menuBar) {
		MenuBar bar = new MenuBar();
		bar.setUseSystemMenuBar(true);
		bar.getMenus().addAll(extractAppMenus(menuBar));
		return bar;
	}

	private void removeExistingMenuBar(Pane pane) {
		ObservableList<Node> children = pane.getChildren();
		children.removeAll(children.stream().filter(node -> node instanceof MenuBar).collect(Collectors.toList()));
	}

	private Menu extractAppleMenu(MenuBar menuBar) {
		return menuBar.getMenus().get(0);
	}

	private List<Menu> extractAppMenus(MenuBar bar) {
		if (bar.getMenus().size() <= 1) {
			return new ArrayList<>();
		}
		return bar.getMenus().subList(1, bar.getMenus().size());
	}

}
