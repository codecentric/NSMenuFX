package de.jangassen.platform;

import javafx.scene.control.Menu;

public interface NativeAdapter {
  void setApplicationMenu(Menu menu);

  void setDocIconMenu(Menu menu);

  void hide();

  void hideOtherApplications();

  void showAllWindows();

  void quit();

  void setForceQuitOnCmdQ(boolean forceQuit);
}
