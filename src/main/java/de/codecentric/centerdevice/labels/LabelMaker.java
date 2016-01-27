package de.codecentric.centerdevice.labels;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

public class LabelMaker {
  private final Properties properties;
  private Locale locale;

  public LabelMaker() {
    this(Locale.ENGLISH);
  }

  public LabelMaker(Locale locale) {
    this.locale = locale;
    InputStream resource = getLabelResource(locale);
    properties = new Properties();
    if (resource != null) {
      try {
        properties.load(resource);
      } catch (IOException e) {
        System.err.println("Unable to load properties: " + e.getMessage());
      }
    }
  }

  private InputStream getLabelResource(Locale locale) {
    InputStream resource = LabelMaker.class.getClassLoader().getResourceAsStream(getResourceName(locale));
    if (resource == null) {
      System.err.println(locale.getDisplayLanguage() + " not found. Falling back to english.");
      resource = LabelMaker.class.getClassLoader().getResourceAsStream(getResourceName(Locale.ENGLISH));
    }
    return resource;
  }

  private String getResourceName(Locale locale) {
    return "labels_" + locale.getLanguage() + ".properties";
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
