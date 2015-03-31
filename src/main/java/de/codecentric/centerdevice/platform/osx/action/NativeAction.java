package de.codecentric.centerdevice.platform.osx.action;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import org.eclipse.swt.internal.cocoa.id;

public class NativeAction implements EventHandler<ActionEvent> {

  private final long selector;
  private final id target;

  public NativeAction(long selector, id target) {
    this.selector = selector;
    this.target = target;
  }

  @Override
  public void handle(ActionEvent event) {
    // Handled by native code using selector
  }

  public long getSelector() {
    return selector;
  }

  public id getTarget() {
    return target;
  }
}
