package de.jangassen.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public final class MenuBarUtils {
	private MenuBarUtils() {}

	public static MenuBar createMenuBar(List<Menu> menus) {
		MenuBar bar = new MenuBar();
		bar.setUseSystemMenuBar(true);
		bar.getMenus().addAll(menus);
		return bar;
	}

	public static void removeExistingMenuBar(ObservableList<Node> children) {
		children.removeAll(children.stream().filter(MenuBar.class::isInstance).collect(Collectors.toList()));
	}

	public static void setMenuBar(Stage stage, MenuBar menuBar) {
		Scene scene = stage.getScene();
		if (scene != null) {
			ObservableList<Node> children = getChildren(scene.getRoot());
			if (children != null) {
				setMenuBar(children, menuBar);
			}
		}
	}

	private static ObservableList<Node> getChildren(Parent parent) {
		if (parent instanceof Pane) {
			return ((Pane) parent).getChildren();
		} else if (parent instanceof Group) {
			return ((Group) parent).getChildren();
		}

		return null;
	}

	public static void setMenuBar(Pane pane, MenuBar menuBar) {
		setMenuBar(pane.getChildren(), menuBar);
	}

	private static void setMenuBar(ObservableList<Node> children, MenuBar menuBar) {
		replaceMenuBar(children, createMenuBar(extractSubMenus(menuBar)));
	}

	private static void replaceMenuBar(ObservableList<Node> children, MenuBar createMenuBar) {
		removeExistingMenuBar(children);
		children.add(createMenuBar);
	}

	private static List<Menu> extractSubMenus(MenuBar bar) {
		if (bar.getMenus().size() <= 1) {
			return new ArrayList<>();
		}
		return bar.getMenus().subList(1, bar.getMenus().size());
	}
}
