package de.codecentric.centerdevice.dialogs.about;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;

public class AboutStageBuilder {
  private final Stage stage;
  private Label version;
  private Label name;
  private Label copyright;
  private WebView credits;
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
    FXMLLoader loader = new FXMLLoader(AboutStageBuilder.class.getClassLoader().getResource("about.fxml"));
    Parent root = loader.load();

    AboutController controller = loader.<AboutController>getController();

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
    credits = createWebview();
    credits.getEngine().loadContent(html);

    return this;
  }

  private WebView createWebview() {
    WebView view = new WebView();
    view.setPrefHeight(150);
    return view;
  }

  public AboutStageBuilder withUrl(String url) {
    credits = createWebview();
    credits.getEngine().load(url);

    return this;
  }

  public AboutStageBuilder withCopyright(String copyright) {
    this.copyright = new Label(copyright);

    return this;
  }

  public AboutStageBuilder withImage(Image image) {
    this.image = new ImageView(image);
    this.image.setFitHeight(100);
    this.image.setFitWidth(100);

    return this;
  }

  public static AboutStageBuilder start(String title) {
    final Stage aboutStage = new Stage();
    aboutStage.setResizable(false);
    return new AboutStageBuilder(aboutStage).withTitle(title).withSize(300, 300);
  }
}
