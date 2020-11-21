package de.jangassen.icns;

public enum IcnsType {
  ICON("ICON"),
  ICN("ICN#"),
  icm("icm#"),
  icm4("icm4"),
  icm8("icm8"),
  ics("ics#"),
  ics4("ics4"),
  ics8("ics8"),
  is32("is32"),
  s8mk("s8mk"),
  icl4("icl4"),
  icl8("icl8"),
  il32("il32"),
  l8mk("l8mk"),
  ich("ich#"),
  ich4("ich4"),
  ich8("ich8"),
  ih32("ih32"),
  h8mk("h8mk"),
  it32("it32"),
  t8mk("t8mk"),
  icp4("icp4"),
  icp5("icp5"),
  icp6("icp6"),
  ic07("ic07"),
  ic08("ic08"),
  ic09("ic09"),
  ic10("ic10"),
  ic11("ic11"),
  ic12("ic12"),
  ic13("ic13"),
  ic14("ic14");

  private final String osType;

  IcnsType(String osType) {
    this.osType = osType;
  }

  String getOsType() {
    return osType;
  }
}
