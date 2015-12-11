package de.codecentric.centerdevice.listener;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.stage.StageHelper;

import javafx.collections.ListChangeListener;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class WindowMenuUpdateListener implements ListChangeListener<Stage> {

	private Menu windowMenu;
	private List<MenuItem> createdMenuItems;

	public WindowMenuUpdateListener(Menu windowMenu) {
		this.windowMenu = windowMenu;
		createdMenuItems = new ArrayList<>();

		updateWindowMenuItems();
	}

	@Override
	public void onChanged(ListChangeListener.Change<? extends Stage> c) {
		updateWindowMenuItems();
	}

	public void setWindowMenu(Menu windowMenu) {
		this.windowMenu = windowMenu;
	}

	protected void updateWindowMenuItems() {
		windowMenu.getItems().removeAll(createdMenuItems);
		StageHelper.getStages().forEach(stage -> createWindowMenuItem(stage));
	}

	private void createWindowMenuItem(Stage stage) {
		MenuItem item = new MenuItem(stage.getTitle());
		item.setOnAction(event -> stage.toFront());
		createdMenuItems.add(item);
		windowMenu.getItems().add(item);
	}

}
