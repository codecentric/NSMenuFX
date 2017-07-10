package de.codecentric.centerdevice.util;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.sun.javafx.stage.StageHelper;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

public class StageUtils {
	
	private static ObservableList<Stage> stages;
	
	private static ObservableList<Window> windows;
	
	public static void bringAllToFront() {
		Optional<Stage> focusedStage = getFocusedStage();
		getStages().forEach(stage -> stage.toFront());
		focusedStage.ifPresent(stage -> stage.toFront());
	}

	public static void zoomFocusedStage() {
		getFocusedStage().ifPresent(stage -> {
			ObservableList<Screen> screens = Screen.getScreensForRectangle(stage.getX(), stage.getY(), stage.getWidth(),
					stage.getHeight());

			if (screens.size() == 1) {
				StageUtils.setStageSize(stage, screens.get(0).getBounds());
			}
		});
	}

	public static void minimizeFocusedStage() {
		getFocusedStage().ifPresent(stage -> stage.setIconified(true));
	}

	public static void closeCurrentStage() {
		getFocusedStage().ifPresent(stage -> stage.close());
	}

	public static void focusNextStage() {
		ObservableList<Stage> stages = getStages();
		int currentStageIndex = getFocusedStageIndex(stages);
		if (currentStageIndex < stages.size() - 1) {
			stages.get(currentStageIndex + 1).toFront();
		} else if (stages.size() > 0) {
			stages.get(0).toFront();
		}
	}

	@SuppressWarnings("unchecked")
	public static ObservableList<Stage> getStages() {
		if (stages == null) {
			// Java 9
			try {
				windows = (ObservableList<Window>)Window.class.getMethod("getWindows").invoke(null);
				stages = FXCollections.observableArrayList();
				windows.addListener(new ListChangeListener<Window>() {
					@Override
					public void onChanged(javafx.collections.ListChangeListener.Change<? extends Window> c) {
						updateStages();
					}
				});
				updateStages();
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
			}
			
			if (stages == null) {		
				// Java 8
				try {				
					stages = (ObservableList<Stage>)StageHelper.class.getMethod("getStages").invoke(null);
				} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
				}						
			}			
		}
				
		return stages;
	}

	private static void updateStages() {
		List<Stage> newStages = new LinkedList<>();
		for (Window w : windows) {
			if (w instanceof Stage) {
				newStages.add((Stage)w);
			}
		}
		stages.setAll(newStages);		
	}
	
	public static Optional<Stage> getFocusedStage() {
		return getStages().stream().filter(stage -> stage.isFocused()).findFirst();
	}

	public static int getFocusedStageIndex(List<Stage> stages) {
		for (int i = 0; i < stages.size(); i++) {
			if (stages.get(i).isFocused()) {
				return i;
			}
		}

		return -1;
	}

	public static void setStageSize(Stage stage, Rectangle2D screenBounds) {
		stage.setX(screenBounds.getMinX());
		stage.setY(screenBounds.getMinY());
		stage.setWidth(screenBounds.getMaxX() - screenBounds.getMinX());
		stage.setHeight(screenBounds.getMaxY() - screenBounds.getMinY());
	}
}
