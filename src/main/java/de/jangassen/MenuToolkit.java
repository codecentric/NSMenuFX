package de.jangassen;

import de.jangassen.dialogs.about.AboutStageBuilder;
import de.jangassen.platform.NativeAdapterProvider;
import de.jangassen.platform.NativeAdapter;
import de.jangassen.icns.IcnsParser;
import de.jangassen.icns.IcnsType;
import de.jangassen.labels.LabelMaker;
import de.jangassen.labels.LabelName;
import de.jangassen.listener.MenuBarSyncListener;
import de.jangassen.listener.WindowMenuUpdateListener;
import de.jangassen.util.MenuBarUtils;
import de.jangassen.util.StageUtils;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

public class MenuToolkit {
  private static final String APP_NAME = "Apple";

  private final NativeAdapter nativeAdapter;

  private final LabelMaker labelMaker;

  private MenuToolkit(NativeAdapter nativeAdapter, LabelMaker labelMaker) {
    this.nativeAdapter = nativeAdapter;
    this.labelMaker = labelMaker;
  }

  public static MenuToolkit toolkit() {
    return toolkit(Locale.ENGLISH);
  }

  public static MenuToolkit toolkit(Locale locale) {
    return toolkit(new LabelMaker(locale));
  }

  public static MenuToolkit toolkit(LabelMaker labelMaker) {
    return new MenuToolkit(NativeAdapterProvider.getNativeAdapter(), labelMaker);
  }

  public Menu createDefaultApplicationMenu(String appName) {
    return createDefaultApplicationMenu(appName, createDefaultAboutStage(appName));
  }

  public Menu createDefaultApplicationMenu(String appName, Stage aboutStage) {
    return new Menu(APP_NAME, null, createAboutMenuItem(appName, aboutStage), new SeparatorMenuItem(), createHideMenuItem(appName), createHideOthersMenuItem(),
            createUnhideAllMenuItem(), new SeparatorMenuItem(), createQuitMenuItem(appName));
  }

  public MenuItem createAboutMenuItem(String appName) {
    return createAboutMenuItem(appName, createDefaultAboutStage(appName));
  }

  private Stage createDefaultAboutStage(String appName) {
    AboutStageBuilder stageBuilder = AboutStageBuilder.start(labelMaker.getLabel(LabelName.ABOUT, appName))
            .withAppName(appName).withCloseOnFocusLoss().withCopyright("Copyright \u00A9 " + Calendar
                    .getInstance().get(Calendar.YEAR));

    try {
      IcnsParser parser = IcnsParser.forFile(AboutStageBuilder.DEFAULT_APP_ICON);
      stageBuilder = stageBuilder.withImage(new Image(parser.getIconStream(IcnsType.ic09)));
    } catch (IOException e) {
      // Too bad, cannot load dummy image
    }

    return stageBuilder.build();
  }

  public MenuItem createAboutMenuItem(String appName, Stage aboutStage) {
    MenuItem about = new MenuItem(labelMaker.getLabel(LabelName.ABOUT, appName));
    about.setOnAction(event -> aboutStage.show());
    return about;
  }

  public MenuItem createQuitMenuItem(String appName) {
    MenuItem quit = new MenuItem(labelMaker.getLabel(LabelName.QUIT, appName));
    quit.setOnAction(event -> nativeAdapter.quit());
    quit.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.META_DOWN));
    return quit;
  }

  public MenuItem createUnhideAllMenuItem() {
    MenuItem unhideAll = new MenuItem(labelMaker.getLabel(LabelName.SHOW_ALL));
    unhideAll.setOnAction(event -> nativeAdapter.showAllWindows());
    return unhideAll;
  }

  public MenuItem createHideOthersMenuItem() {
    MenuItem hideOthers = new MenuItem(labelMaker.getLabel(LabelName.HIDE_OTHERS));
    hideOthers.setOnAction(event -> nativeAdapter.hideOtherApplications());
    hideOthers.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.META_DOWN, KeyCombination.ALT_DOWN));
    return hideOthers;
  }

  public MenuItem createHideMenuItem(String appName) {
    MenuItem hide = new MenuItem(labelMaker.getLabel(LabelName.HIDE, appName));
    hide.setOnAction(event -> nativeAdapter.hide());
    hide.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.META_DOWN));
    return hide;
  }

  public MenuItem createMinimizeMenuItem() {
    MenuItem menuItem = new MenuItem(labelMaker.getLabel(LabelName.MINIMIZE));
    menuItem.setAccelerator(new KeyCodeCombination(KeyCode.M, KeyCombination.META_DOWN));
    menuItem.setOnAction(event -> StageUtils.minimizeFocusedStage());
    return menuItem;
  }

  public MenuItem createZoomMenuItem() {
    MenuItem menuItem = new MenuItem(labelMaker.getLabel(LabelName.ZOOM));
    menuItem.setOnAction(event -> StageUtils.zoomFocusedStage());
    return menuItem;
  }

  public MenuItem createCloseWindowMenuItem() {
    MenuItem menuItem = new MenuItem(labelMaker.getLabel(LabelName.CLOSE_WINDOW));
    menuItem.setAccelerator(new KeyCodeCombination(KeyCode.W, KeyCombination.META_DOWN));
    menuItem.setOnAction(event -> StageUtils.closeCurrentStage());
    return menuItem;
  }

  public MenuItem createBringAllToFrontItem() {
    MenuItem menuItem = new MenuItem(labelMaker.getLabel(LabelName.BRING_ALL_TO_FRONT));
    menuItem.setOnAction(event -> StageUtils.bringAllToFront());
    return menuItem;
  }

  public MenuItem createCycleWindowsItem() {
    MenuItem menuItem = new MenuItem(labelMaker.getLabel(LabelName.CYCLE_THROUGH_WINDOWS));
    menuItem.setAccelerator(new KeyCodeCombination(KeyCode.BACK_QUOTE, KeyCombination.META_DOWN));
    menuItem.setOnAction(event -> StageUtils.focusNextStage());
    return menuItem;
  }

  public void setApplicationMenu(Menu menu) {
    nativeAdapter.setApplicationMenu(menu);
  }

  public void setDocIconMenu(Menu menu) {
    nativeAdapter.setDocIconMenu(menu);
  }

  public void setGlobalMenuBar(MenuBar menuBar) {
    setMenuBar(menuBar);
    MenuBarSyncListener.register(menuBar);
  }

  public void unsetGlobalMenuBar() {
    MenuBarSyncListener.unregister();
  }

  public void setMenuBar(MenuBar menuBar) {
    StageUtils.getStages().forEach(stage -> setMenuBar(stage, menuBar));
  }

  public void setMenuBar(Stage stage, MenuBar menuBar) {
    Parent parent = stage.getScene().getRoot();
    if (parent instanceof Pane) {
      setMenuBar((Pane) parent, menuBar);
    }
  }

  public void setMenuBar(Pane pane, MenuBar menuBar) {
    setApplicationMenu(extractApplicationMenu(menuBar));
    MenuBarUtils.setMenuBar(pane, menuBar);
  }

  public void autoAddWindowMenuItems(Menu menu) {
    menu.getItems().add(new SeparatorMenuItem());
    StageUtils.getStages().addListener(new WindowMenuUpdateListener(menu));
  }

  public void setForceQuitOnCmdQ(boolean forceQuit) {
    nativeAdapter.setForceQuitOnCmdQ(forceQuit);
  }

  protected Menu extractApplicationMenu(MenuBar menuBar) {
    return menuBar.getMenus().get(0);
  }
}
