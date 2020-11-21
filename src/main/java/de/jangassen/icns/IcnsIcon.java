package de.jangassen.icns;

class IcnsIcon {
  public final String type;
  public final long offset;
  public final int length;

  public IcnsIcon(String type, long offset, int length) {
    this.type = type;
    this.offset = offset;
    this.length = length;
  }
}