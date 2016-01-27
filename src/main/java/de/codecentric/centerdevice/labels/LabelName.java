package de.codecentric.centerdevice.labels;

public enum LabelName {
  HIDE("hide"), QUIT("quit"), ABOUT("about"), SHOW_ALL("show_all"), HIDE_OTHERS("hide_others");

  private String propertyKey;

  LabelName(String propertyKey) {
    this.propertyKey = propertyKey;
  }

  public String getPropertyKey() {
    return propertyKey;
  }
}
