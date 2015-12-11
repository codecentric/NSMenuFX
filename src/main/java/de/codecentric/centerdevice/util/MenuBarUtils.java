package de.codecentric.centerdevice.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MenuBarUtils {
	public static MenuBar createMenuBar(List<Menu> menus) {
		MenuBar bar = new MenuBar();
		bar.setUseSystemMenuBar(true);
		bar.getMenus().addAll(menus);
		return bar;
	}

	public static void removeExistingMenuBar(Pane pane) {
		ObservableList<Node> children = pane.getChildren();
		children.removeAll(children.stream().filter(node -> node instanceof MenuBar).collect(Collectors.toList()));
	}

	public static void setMenuBar(Stage stage, MenuBar menuBar) {
		Parent parent = stage.getScene().getRoot();
		if (parent instanceof Pane) {
			setMenuBar((Pane) parent, menuBar);
		}
	}

	public static void setMenuBar(Pane pane, MenuBar menuBar) {
		replaceMenuBar(pane, createMenuBar(extractSubMenus(menuBar)));
	}

	private static void replaceMenuBar(Pane pane, MenuBar createMenuBar) {
		removeExistingMenuBar(pane);
		pane.getChildren().add(createMenuBar);
	}

	private static List<Menu> extractSubMenus(MenuBar bar) {
		if (bar.getMenus().size() <= 1) {
			return new ArrayList<>();
		}
		return bar.getMenus().subList(1, bar.getMenus().size());
	}
}
