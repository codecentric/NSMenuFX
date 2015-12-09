package de.codecentric.centerdevice.sample;

import de.codecentric.centerdevice.MenuToolkit;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Skeleton for Standard Macintosh Application
 * See: "OS X Human Interface Guidelines"
 */
public class StandardMacApp extends Application {
    static final String appName = "Standard";
    static final String mainWindowTitle = "Main";

    @Override
    public void start(Stage primaryStage) throws Exception {

        StackPane root = new StackPane();
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.requestFocus();
        primaryStage.setTitle(mainWindowTitle);
        primaryStage.show();

        MenuToolkit tk = MenuToolkit.toolkit();

        MenuBar bar = new MenuBar();

        // Application Menu
        // TBD: services menu
        Menu appMenu = new Menu(appName);   // Name for appMenu can't be set at Runtime
        MenuItem aboutItem = new MenuItem("About");
        MenuItem prefsItem = new MenuItem("Preferences...");
        appMenu.getItems().addAll(aboutItem,
                                    new SeparatorMenuItem(),
                                    prefsItem,
                                    new SeparatorMenuItem(),
                                    tk.createHideMenuItem(appName),
                                    tk.createHideOthersMenuItem(),
                                    tk.createUnhideAllMenuItem(),
                                    new SeparatorMenuItem(),
                                    tk.createQuitMenuItem(appName));

        // File Menu (items TBD)
        Menu fileMenu = new Menu("File");
        MenuItem newItem = new MenuItem("New...");
        fileMenu.getItems().addAll(newItem, new MenuItem("TBD"));

        // Edit (items TBD)
        Menu editMenu = new Menu("Edit");
        editMenu.getItems().addAll(new MenuItem("TBD"));

        // Format (items TBD)
        Menu formatMenu = new Menu("Format");
        formatMenu.getItems().addAll(new MenuItem("TBD"));

        // View Menu (items TBD)
        Menu viewMenu = new Menu("View");
        viewMenu.getItems().addAll(new MenuItem("TBD"));

        // Window Menu
        // TBD standard window menu items
        Menu windowMenu = new Menu("Window");
        MenuItem mainWinItem = new MenuItem(mainWindowTitle);
        windowMenu.getItems().addAll(new MenuItem("TBD"),
                                new SeparatorMenuItem(),
                                mainWinItem);

        // Help Menu (items TBD)
        Menu helpMenu = new Menu("Help");
        helpMenu.getItems().addAll(new MenuItem("TBD"));

        bar.getMenus().addAll(appMenu,
                                fileMenu,
                                editMenu,
                                formatMenu,
                                viewMenu,
                                windowMenu,
                                helpMenu);

        tk.setMenuBar(primaryStage, bar);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
