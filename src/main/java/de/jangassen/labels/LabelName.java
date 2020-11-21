package de.jangassen.labels;

public enum LabelName {
  HIDE("hide"), QUIT("quit"), ABOUT("about"), SHOW_ALL("show_all"), HIDE_OTHERS("hide_others"), MINIMIZE(
      "minimize"), ZOOM("zoom"), CLOSE_WINDOW("close_window"), BRING_ALL_TO_FRONT(
      "bring_all_to_front"), CYCLE_THROUGH_WINDOWS("cycle_through_windows"), FILE("file"), WINDOW("window"), VIEW(
      "view"), HELP("help"), EDIT("edit");

  private final String propertyKey;

  LabelName(String propertyKey) {
    this.propertyKey = propertyKey;
  }

  public String getPropertyKey() {
    return propertyKey;
  }
}
