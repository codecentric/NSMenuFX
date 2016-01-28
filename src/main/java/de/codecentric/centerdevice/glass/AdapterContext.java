package de.codecentric.centerdevice.glass;

import com.sun.javafx.tk.Toolkit;

public class AdapterContext {

  private static AdapterContext instance;

  private TKSystemMenuAdapter systemMenuAdapter;
  private MacApplicationAdapter applicationAdapter;

  private AdapterContext(TKSystemMenuAdapter systemMenuAdapter, MacApplicationAdapter applicationAdapter) {
    this.systemMenuAdapter = systemMenuAdapter;
    this.applicationAdapter = applicationAdapter;
  }

  public static AdapterContext getContext() {
    if (instance == null) {
      instance = createContext();
    }

    return instance;
  }

  public MacApplicationAdapter getApplicationAdapter() {
    return applicationAdapter;
  }

  public TKSystemMenuAdapter getSystemMenuAdapter() {
    return systemMenuAdapter;
  }

  private static AdapterContext createContext() {
    if (!Toolkit.getToolkit().getSystemMenu().isSupported()) {
      return null;
    }

    try {
      return new AdapterContext(new TKSystemMenuAdapter(), new MacApplicationAdapter());
    } catch (ReflectiveOperationException e) {
      throw new GlassAdaptionException(e);
    }
  }
}
