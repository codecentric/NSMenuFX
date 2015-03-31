package de.codecentric.centerdevice.platform.osx.action;

import org.eclipse.swt.internal.cocoa.OS;

public class HideOthersAction extends NativeAction {

  public HideOthersAction() {
    super(OS.sel_hideOtherApplications_, null);
  }

}
