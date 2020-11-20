package de.codecentric.centerdevice;

import javafx.application.Application;
import javafx.scene.control.Menu;
import javafx.stage.Stage;


public class SampleApp extends Application {

  @Override
  public void start(Stage primaryStage) {
    primaryStage.show();

    Menu appMenu = MenuToolkit.toolkit().createDefaultApplicationMenu("My App");

    MenuToolkit.toolkit().setApplicationMenu(appMenu);
  }
}
