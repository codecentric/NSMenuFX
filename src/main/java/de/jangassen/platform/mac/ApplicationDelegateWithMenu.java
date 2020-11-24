package de.jangassen.platform.mac;

import de.jangassen.jfa.FoundationProxy;
import de.jangassen.jfa.FoundationProxyHandler;
import de.jangassen.jfa.appkit.NSApplication;
import de.jangassen.jfa.appkit.NSApplicationDelegate;
import de.jangassen.jfa.appkit.NSMenu;

public class ApplicationDelegateWithMenu extends FoundationProxy {
  private NSMenu menu;

  public ApplicationDelegateWithMenu(NSApplicationDelegate target, NSMenu menu) {
    super(target, new FoundationProxyHandler());
    this.menu = menu;
  }

  public NSMenu applicationDockMenu(NSApplication application) {
    return menu;
  }
}
