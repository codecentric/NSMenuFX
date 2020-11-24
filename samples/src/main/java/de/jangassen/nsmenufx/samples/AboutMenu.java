package de.jangassen.nsmenufx.samples;

import de.jangassen.MenuToolkit;
import de.jangassen.dialogs.about.AboutStageBuilder;
import de.jangassen.icns.IcnsParser;
import de.jangassen.icns.IcnsType;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Calendar;

public class AboutMenu extends Application {

  @Override public void start(Stage primaryStage) {
    primaryStage.setScene(new Scene(new StackPane()));
    primaryStage.show();

    MenuToolkit tk = MenuToolkit.toolkit();

    AboutStageBuilder aboutStageBuilder = AboutStageBuilder.start("About MyApp")
        .withAppName("MyApp").withCloseOnFocusLoss().withHtml("<i>Some descriptive text</i>")
        .withVersionString("Version 1.0b").withCopyright("Copyright \u00A9 " + Calendar
            .getInstance().get(Calendar.YEAR));


    try {
      IcnsParser parser = IcnsParser.forFile(AboutStageBuilder.DEFAULT_APP_ICON);
      aboutStageBuilder = aboutStageBuilder.withImage(new Image(parser.getIconStream(IcnsType.ic08)));
    } catch (IOException e) {
      // Too bad, cannot load dummy image
    }

    Menu applicationMenu = tk.createDefaultApplicationMenu("MyApp", aboutStageBuilder.build());

    MenuBar bar = new MenuBar();
    bar.getMenus().add(applicationMenu);
    tk.setMenuBar(bar);

    Menu menu = new Menu("test");
    MenuItem myItem = new MenuItem("Hallo welt");
    menu.getItems().add(myItem);
    tk.setDocIconMenu(menu);
  }
}
