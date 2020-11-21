package de.jangassen.icns;

import java.io.IOException;
import java.io.InputStream;

class IcnsInputStream extends InputStream {
  private final InputStream other;
  private long remainingBytes;

  public IcnsInputStream(InputStream other, long bytes) {
    this.other = other;
    this.remainingBytes = bytes;
  }

  @Override public int read() throws IOException {
    if (remainingBytes <= 0) {
      return -1;
    }
    remainingBytes--;
    return other.read();
  }
}
