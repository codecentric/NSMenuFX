package de.jangassen.dialogs.about;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AboutStageBuilder {

  public static final String DEFAULT_APP_ICON =
      "/System/Library/CoreServices/CoreTypes.bundle/Contents/Resources/GenericApplicationIcon.icns";
  public static final int DEFAULT_ICON_SIZE = 80;

  private final Stage stage;
  private Label version;
  private Label name;
  private Label copyright;
  private Node credits;
  private ImageView image;

  private AboutStageBuilder(Stage stage) {
    this.stage = stage;
  }

  public Stage build() {
    try {
      prepareStage();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return stage;
  }

  private void prepareStage() throws IOException {
    URL resource = AboutStageBuilder.class.getClassLoader().getResource("about.fxml");
    FXMLLoader loader = new FXMLLoader(resource);
    Parent root = loader.load();

    AboutController controller = loader.getController();

    if (image != null) {
      controller.getContent().getChildren().add(image);
    }

    if (name != null) {
      controller.getContent().getChildren().add(name);
    }

    if (version != null) {
      controller.getContent().getChildren().add(version);
    }

    if (credits != null) {
      controller.getContent().getChildren().add(credits);
    }

    if (copyright != null) {
      controller.getContent().getChildren().add(copyright);
    }

    stage.setScene(new Scene(root));
  }

  public AboutStageBuilder withCloseOnFocusLoss() {
    stage.focusedProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue) {
        stage.close();
      }
    });

    return this;
  }

  public AboutStageBuilder withSize(int with, int height) {
    stage.setMinWidth(with);
    stage.setMaxWidth(height);

    return this;
  }

  public AboutStageBuilder withTitle(String title) {
    stage.setTitle(title);

    return this;
  }

  public AboutStageBuilder withVersionString(String version) {
    this.version = new Label(version);
    this.version.getStyleClass().add("version");

    return this;
  }

  public AboutStageBuilder withAppName(String name) {
    this.name = new Label(name);
    this.name.getStyleClass().add("app_name");

    return this;
  }
  
  public AboutStageBuilder withHtml(String html) {
    WebView creditsView = createWebview();
    creditsView.getEngine().loadContent(html);
    setCredits(creditsView);

    return this;
  }

  private WebView createWebview() {
    WebView view = new WebView();
    view.setPrefHeight(140);
    return view;
  }

  private void setCredits(Node view) {
    BorderPane pane = new BorderPane();
    pane.setCenter(view);
    pane.getStyleClass().add("credits");
    this.credits = pane;
  }

  public AboutStageBuilder withUrl(String url) {
    WebView creditsView = createWebview();
    creditsView.getEngine().load(url);
    setCredits(creditsView);

    return this;
  }

  public AboutStageBuilder withCopyright(String copyright) {
    this.copyright = new Label(copyright);

    return this;
  }

  public AboutStageBuilder withImage(Image image) {
    return withImage(image, DEFAULT_ICON_SIZE, DEFAULT_ICON_SIZE);
  }

  public AboutStageBuilder withImage(Image image, double width, double height) {
    this.image = new ImageView(image);
    this.image.setFitWidth(width);
    this.image.setFitHeight(height);

    return this;
  }

  public static AboutStageBuilder start(String title) {
    final Stage aboutStage = new Stage();
    aboutStage.setResizable(false);
    return new AboutStageBuilder(aboutStage).withTitle(title).withSize(300, 300);
  }
}
