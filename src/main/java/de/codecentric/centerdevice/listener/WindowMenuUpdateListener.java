package de.codecentric.centerdevice.listener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.stage.StageHelper;

import javafx.collections.ListChangeListener;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class WindowMenuUpdateListener implements ListChangeListener<Stage> {

	private final WeakReference<Menu> windowMenu;
	private final List<MenuItem> createdMenuItems;

	public WindowMenuUpdateListener(Menu windowMenu) {
		this.windowMenu = new WeakReference<Menu>(windowMenu);
		createdMenuItems = new ArrayList<>();

		updateWindowMenuItems();
	}

	@Override
	public void onChanged(ListChangeListener.Change<? extends Stage> c) {
		updateWindowMenuItems();
	}

	protected void updateWindowMenuItems() {
		Menu menu = windowMenu.get();
		if (menu != null) {
			menu.getItems().removeAll(createdMenuItems);
			StageHelper.getStages().forEach(stage -> addWindowMenuItem(stage));
		}
	}

	private void addWindowMenuItem(Stage stage) {
		Menu menu = windowMenu.get();
		if (menu != null) {
			addWindowMenuItem(stage, menu);
		}
	}

	private void addWindowMenuItem(Stage stage, Menu menu) {
		MenuItem item = new MenuItem(stage.getTitle());
		item.setOnAction(event -> stage.toFront());
		createdMenuItems.add(item);
		menu.getItems().add(item);
	}

}
