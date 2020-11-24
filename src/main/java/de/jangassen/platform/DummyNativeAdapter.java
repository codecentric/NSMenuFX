package de.jangassen.platform;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.stage.Stage;
import javafx.stage.Window;

public class DummyNativeAdapter implements NativeAdapter {

  @Override
  public void setApplicationMenu(Menu menu) {
    // Only supported on macOS
  }

  @Override
  public void hide() {
    Window.getWindows().stream()
            .filter(Window::isFocused)
            .findFirst()
            .ifPresent(Window::hide);
  }

  @Override
  public void hideOtherApplications() {
    // Only supported on macOS
  }

  @Override
  public void showAllWindows() {
    Window.getWindows().stream()
            .filter(Stage.class::isInstance)
            .map(Stage.class::cast)
            .forEach(Stage::show);
  }

  @Override
  public void setDocIconMenu(Menu menu) {
    // Only supported on macOS
  }

  @Override
  public void quit() {
    Platform.exit();
  }

  @Override
  public void setForceQuitOnCmdQ(boolean forceQuit) {
    // Only supported on macOS
  }
}
