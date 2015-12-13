package de.codecentric.centerdevice.glass;

import java.lang.invoke.MethodHandle;

import com.sun.glass.ui.Application;

import de.codecentric.centerdevice.util.ReflectionUtils;

public class MacApplicationAdapter {
	private MethodHandle hide;
	private MethodHandle hideOtherApplications;
	private MethodHandle unhideAllApplications;

	private Application app;

	public MacApplicationAdapter() throws ReflectiveOperationException {
		app = Application.GetApplication();

		hide = ReflectionUtils.getHandle(app, "_hide");
		hideOtherApplications = ReflectionUtils.getHandle(app, "_hideOtherApplications");
		unhideAllApplications = ReflectionUtils.getHandle(app, "_unhideAllApplications");
	}

	public void hide() {
		ReflectionUtils.invokeQuietly(hide, app);
	}

	public void hideOtherApplications() {
		ReflectionUtils.invokeQuietly(hideOtherApplications, app);
	}

	public void unhideAllApplications() {
		ReflectionUtils.invokeQuietly(unhideAllApplications, app);
	}

	public void quit() {
		Application.EventHandler eh = app.getEventHandler();
		if (eh != null) {
			eh.handleQuitAction(Application.GetApplication(), System.nanoTime());
		}
	}
}
