package de.codecentric.centerdevice.glass;

import com.sun.glass.ui.Application;
import de.codecentric.centerdevice.cocoa.NSApplication;
import javafx.application.Platform;

public class MacApplicationAdapter {
	private NSApplication nativeAdapter = NSApplication.sharedApplication();

	private Application app;

	public MacApplicationAdapter() throws ReflectiveOperationException {
		app = Application.GetApplication();
	}

	public void hide() {
		nativeAdapter.hide();
	}

	public void hideOtherApplications() {
		nativeAdapter.hideOtherApplications();
	}

	public void unhideAllApplications() {
		nativeAdapter.unhideAllApplications();
	}

	public void quit() {
		Application.EventHandler eh = app.getEventHandler();
		if (eh != null) {
			eh.handleQuitAction(Application.GetApplication(), System.nanoTime());
		}
		Platform.exit();
	}
}
