package de.codecentric.centerdevice.platform.osx.action;

import org.eclipse.swt.internal.cocoa.OS;

public class HideAllAction extends NativeAction {

  public HideAllAction() {
    super(OS.sel_hide_, null);
  }

}
