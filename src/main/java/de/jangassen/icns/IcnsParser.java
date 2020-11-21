package de.jangassen.icns;

import java.io.*;
import java.util.HashMap;

public class IcnsParser {
  private static final int MAGIC = 0x69636e73;
  private static final int HEADER_SIZE = 8;

  private final File icnsFile;

  private int length = 0;
  private long offset = 0;

  private final HashMap<String, IcnsIcon> iconMap = new HashMap<>();

  protected IcnsParser(File icnsFile) {
    this.icnsFile = icnsFile;
  }

  public static IcnsParser forFile(String path) throws IOException {
    return forFile(new File(path));
  }

  public static IcnsParser forFile(File file) throws IOException {
    IcnsParser parser = new IcnsParser(file);
    parser.parse();
    return parser;
  }

  public void parse() throws IOException {
    try(InputStream fileInputStream = new FileInputStream(icnsFile)) {
      try(DataInputStream dataInputStream = new DataInputStream(fileInputStream)) {
        parseHeader(dataInputStream);
        parseIcons(dataInputStream);
      }
    }
  }

  public boolean hasIconType(IcnsType iconType) {
    return iconMap.containsKey(iconType.getOsType());
  }

  public InputStream getIconStream(IcnsType iconType) throws IOException {
    if (!iconMap.containsKey(iconType.getOsType())) {
      return null;
    }

    return createIconInputStream(iconType.getOsType());
  }

  private InputStream createIconInputStream(String osType) throws IOException {
    IcnsIcon icon = iconMap.get(osType);
    InputStream iconData = new FileInputStream(icnsFile);
    iconData.skip(icon.offset);
    return new IcnsInputStream(iconData, icon.length);
  }

  private void parseIcons(DataInputStream dataInputStream) throws IOException {
    while (offset < length) {
      IcnsIcon icon = readNextIcon(dataInputStream);
      if (icon.length <= 0) {
        break;
      }

      iconMap.put(icon.type, icon);
      offset += icon.length;
    }
  }

  private void parseHeader(DataInputStream stream) throws IOException {
    int magic = stream.readInt();
    if (magic != MAGIC) {
      throw new IllegalArgumentException("Provided file is not a valid icns file");
    }

    length = stream.readInt();
    offset += HEADER_SIZE;
  }

  private IcnsIcon readNextIcon(DataInputStream stream) throws IOException {
    byte[] type = new byte[4];
    stream.read(type);
    int length = stream.readInt();

    stream.skipBytes(length - HEADER_SIZE);

    return new IcnsIcon(new String(type), offset + HEADER_SIZE, length);
  }
}
