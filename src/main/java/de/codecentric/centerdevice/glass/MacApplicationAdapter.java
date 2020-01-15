package de.codecentric.centerdevice.glass;

import com.sun.glass.ui.Application;

import de.codecentric.centerdevice.util.ReflectionUtils;
import javafx.application.Platform;

public class MacApplicationAdapter {
    
	private Application app;

	private boolean forceQuitOnCmdQ = true;

	public MacApplicationAdapter() {
		app = Application.GetApplication();
	}

	public void hide() {
		ReflectionUtils.invokeQuietly(app, "_hide");
	}

	public void hideOtherApplications() {
		ReflectionUtils.invokeQuietly(app, "_hideOtherApplications");
	}

	public void unhideAllApplications() {
		ReflectionUtils.invokeQuietly(app, "_unhideAllApplications");
	}

	public void quit() {
		Application.EventHandler eh = app.getEventHandler();
		if (eh != null) {
			eh.handleQuitAction(Application.GetApplication(), System.nanoTime());
		}
		if (forceQuitOnCmdQ) {
			Platform.exit();
		}
	}

	public void setForceQuitOnCmdQ(boolean forceQuit) {
		this.forceQuitOnCmdQ = forceQuit;
	}
	
}
