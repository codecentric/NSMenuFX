package de.jangassen.listener;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import de.jangassen.util.StageUtils;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class WindowMenuUpdateListener implements ListChangeListener<Stage> {

	private final WeakReference<Menu> windowMenu;
	private final Map<Stage, CheckMenuItem> createdMenuItems;
	private final ObservableList<Stage> stages;

	public WindowMenuUpdateListener(Menu windowMenu) {
		this.windowMenu = new WeakReference<>(windowMenu);
		createdMenuItems = new HashMap<>();

		addItemsToMenu(StageUtils.getStages());

		stages = FXCollections.observableArrayList(stage -> new Observable[] {stage.focusedProperty()});
		Bindings.bindContent(stages, StageUtils.getStages());

		stages.addListener((Change <? extends Stage> c) -> checkFocusedStage());
	}

	private void checkFocusedStage() {
		Optional<Stage> focusedStage = stages.stream().filter(Stage::isFocused).findFirst();
		createdMenuItems.forEach((key, value) -> value.setSelected(focusedStage.isPresent() && focusedStage.get().equals(key)));
	}

	@Override
	public void onChanged(ListChangeListener.Change<? extends Stage> c) {
		while (c.next()) {
			updateWindowMenuItems(c.getAddedSubList(), c.getRemoved());
		}
	}

	private void updateWindowMenuItems(List<? extends Stage> add, List<? extends Stage> remove) {
		removeItemsFromMenu(remove);
		addItemsToMenu(add);
	}

	private void addItemsToMenu(List<? extends Stage> add) {
		Menu menu = windowMenu.get();
		if (add != null && menu != null) {
			add.forEach(stage -> addWindowMenuItem(stage, menu));
		}
	}

	private void removeItemsFromMenu(List<? extends Stage> remove) {
		Menu menu = windowMenu.get();
		if (remove != null && menu != null) {
      remove.forEach(stage -> removeWindowMenuItem(stage, menu));
    }
	}

	private void removeWindowMenuItem(Stage stage, Menu menu) {
		MenuItem menuItem = createdMenuItems.get(stage);
		if (menuItem != null) {
      menu.getItems().remove(menuItem);
    }
	}

	private void addWindowMenuItem(Stage stage, Menu menu) {
		CheckMenuItem item = new CheckMenuItem(stage.getTitle());
		item.setOnAction(event -> stage.toFront());
		createdMenuItems.put(stage, item);
		menu.getItems().add(item);
	}

}
