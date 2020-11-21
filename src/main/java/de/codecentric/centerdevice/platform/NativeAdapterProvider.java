package de.codecentric.centerdevice.platform;

import de.codecentric.centerdevice.platform.mac.MacNativeAdapter;

public class NativeAdapterProvider {
  private NativeAdapterProvider() {}

  public static NativeAdapter getNativeAdapter() {
    String os = System.getProperty("os.name");
    if (os.startsWith("Mac") && MacNativeAdapter.isAvailable()) {
      return new MacNativeAdapter();
    }

    return new DummyNativeAdapter();
  }
}
