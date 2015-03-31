package de.codecentric.centerdevice.platform.osx.action;

import org.eclipse.swt.internal.cocoa.OS;

public class ShowAllAction extends NativeAction {

  public ShowAllAction() {
    super(OS.sel_makeKeyAndOrderFront_, null);
  }
}
