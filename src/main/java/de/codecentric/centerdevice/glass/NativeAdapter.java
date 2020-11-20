package de.codecentric.centerdevice.glass;

import javafx.scene.control.Menu;

public interface NativeAdapter {
  void setApplicationMenu(Menu menu);

  void hide();

  void hideOtherApplications();

  void unhideAllApplications();

  void quit();

  void setForceQuitOnCmdQ(boolean forceQuit);
}
