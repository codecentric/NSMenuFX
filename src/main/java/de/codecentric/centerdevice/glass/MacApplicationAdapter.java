package de.codecentric.centerdevice.glass;

import com.sun.glass.ui.Application;

import de.codecentric.centerdevice.util.ReflectionUtils;
import javafx.application.Platform;

public class MacApplicationAdapter {
    
	private Application app;

	private boolean forceQuitOnCmdQ = true;

	public MacApplicationAdapter() throws ReflectiveOperationException {
		app = Application.GetApplication();
	}

	public void hide() {
        try {
            ReflectionUtils.getAccessibleMethod(app, "_hide").invoke(app);
        } catch (Throwable e) {
            e.printStackTrace();
        }
	}

	public void hideOtherApplications() {
        try {
            ReflectionUtils.getAccessibleMethod(app, "_hideOtherApplications").invoke(app);
        } catch (Throwable e) {
            e.printStackTrace();
        }
	}

	public void unhideAllApplications() {
        try {
            ReflectionUtils.getAccessibleMethod(app, "_unhideAllApplications").invoke(app);
        } catch (Throwable e) {
            e.printStackTrace();
        }
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
