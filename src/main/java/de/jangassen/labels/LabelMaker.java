package de.jangassen.labels;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Properties;

public class LabelMaker {
  public static final String PROPERTY_FILE_EXTENSION = ".properties";
  public static final String PROPERTY_FILE_PREFIX = "menu_labels_";

  private final Properties properties;
  private final Locale locale;

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
    InputStream resource = LabelMaker.class.getClassLoader().getResourceAsStream(getBCP47ResourceName(locale));
    if (resource != null) {
      return resource;
    }

    resource = LabelMaker.class.getClassLoader().getResourceAsStream(getISO639ResourceName(locale));
    if (resource != null) {
      return resource;
    }

    return LabelMaker.class.getClassLoader().getResourceAsStream(getISO639ResourceName(Locale.ENGLISH));
  }

  private String getISO639ResourceName(Locale locale) {
    return getResourceName(locale.getLanguage());
  }

  private String getBCP47ResourceName(Locale locale) {
    return getResourceName(locale.toLanguageTag().replace('-', '_'));
  }

  public String getResourceName(String label) {
    return PROPERTY_FILE_PREFIX + label + PROPERTY_FILE_EXTENSION;
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
