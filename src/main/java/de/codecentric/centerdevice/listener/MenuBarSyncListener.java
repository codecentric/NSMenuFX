package de.codecentric.centerdevice.listener;

import com.sun.javafx.stage.StageHelper;

import de.codecentric.centerdevice.util.MenuBarUtils;
import javafx.collections.ListChangeListener;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class MenuBarSyncListener implements ListChangeListener<Stage> {

	private static MenuBar MENU_BAR;
	private static MenuBarSyncListener instance = null;

	public static void register(MenuBar menuBar) {
		MENU_BAR = menuBar;

		if (instance == null) {
			instance = new MenuBarSyncListener();
			StageHelper.getStages().addListener(instance);
		}
	}

	public static void unregister() {
		if (instance != null) {
			StageHelper.getStages().removeListener(instance);
			instance = null;
		}
	}

	private MenuBarSyncListener() {
	}

	@Override
	public void onChanged(ListChangeListener.Change<? extends Stage> stageChanges) {
		while (stageChanges.next()) {
			stageChanges.getAddedSubList().forEach(stage -> MenuBarUtils.setMenuBar(stage, MENU_BAR));
		}
	}

}
