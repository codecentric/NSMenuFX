package de.jangassen.util;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class StageUtils {

  private StageUtils() {

  }

  private static ObservableList<Stage> stages;

  private static ObservableList<Window> windows;

  public static void bringAllToFront() {
    Optional<Stage> focusedStage = getFocusedStage();
    getStages().forEach(Stage::toFront);
    focusedStage.ifPresent(Stage::toFront);
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
    getFocusedStage().ifPresent(Stage::close);
  }

  public static void focusNextStage() {
    ObservableList<Stage> stages = getStages();
    int currentStageIndex = getFocusedStageIndex(stages);
    if (currentStageIndex < stages.size() - 1) {
      stages.get(currentStageIndex + 1).toFront();
    } else if (!stages.isEmpty()) {
      stages.get(0).toFront();
    }
  }

  public static ObservableList<Stage> getStages() {
    if (stages == null) {
      windows = Window.getWindows();
      stages = FXCollections.observableArrayList();
      windows.addListener((ListChangeListener<Window>) c -> updateStages());
      updateStages();
    }

    return stages;
  }

  private static void updateStages() {
    List<Stage> currentStages = windows.stream()
            .filter(Stage.class::isInstance)
            .map(Stage.class::cast)
            .collect(Collectors.toList());

    // Remove no-longer existing stages
    stages.removeIf(stage -> !currentStages.contains(stage));

    // Add any new stages
    currentStages.stream()
            .filter(currentStage -> !stages.contains(currentStage))
            .forEach(stages::add);
  }

  public static Optional<Stage> getFocusedStage() {
    return getStages().stream().filter(Window::isFocused).findFirst();
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
