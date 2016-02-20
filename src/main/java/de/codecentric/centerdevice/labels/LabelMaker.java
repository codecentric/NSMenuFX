package de.codecentric.centerdevice.labels;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Properties;

public class LabelMaker {
  public static final String PROPERTY_FILE_EXTENSION = ".properties";
  public static final String PROPERTY_FILE_PREFIX = "menu_labels_";

  private final Properties properties;
  private Locale locale;

  public LabelMaker(Locale locale) {
    this.locale = locale;
    properties = new Properties();

    loadLabelsForLocale(locale);
  }

  private void loadLabelsForLocale(Locale locale) {
    InputStream resource = getLabelResource(locale);
    if (resource != null) {
      try {
        loadLabels(resource);
      } catch (IOException e) {
        System.err.println("Unable to load properties: " + e.getMessage());
      }
    }
  }

  public void loadLabels(InputStream resource) throws IOException {
    properties.load(new InputStreamReader(resource, "UTF-8"));
  }

  private InputStream getLabelResource(Locale locale) {
    InputStream resource = LabelMaker.class.getClassLoader().getResourceAsStream(getResourceName(locale));
    if (resource == null && !locale.getLanguage().equals(Locale.ENGLISH.getLanguage())) {
      System.err.println(locale.getDisplayLanguage() + " menu labels not found. Falling back to english.");
      resource = LabelMaker.class.getClassLoader().getResourceAsStream(getResourceName(Locale.ENGLISH));
    }
    return resource;
  }

  private String getResourceName(Locale locale) {
    return PROPERTY_FILE_PREFIX + locale.getLanguage() + PROPERTY_FILE_EXTENSION;
  }

  public String getLabel(LabelName menuItemName, Object... args) {
    String property = properties.getProperty(menuItemName.getPropertyKey());
    if (property == null) {
      return getPlaceholderValue(menuItemName);
    }

    return String.format(locale, property, args);
  }

  private String getPlaceholderValue(LabelName name) {
    return "§" + name.getPropertyKey() + "§";
  }

}
