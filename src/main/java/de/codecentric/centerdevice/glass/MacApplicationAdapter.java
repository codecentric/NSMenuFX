package de.codecentric.centerdevice.glass;

import de.jangassen.jfa.appkit.NSApplication;
import javafx.application.Platform;

public class MacApplicationAdapter {

	private NSApplication nativeAdapter = NSApplication.sharedApplication();


	private boolean forceQuitOnCmdQ = true;

	public MacApplicationAdapter() {
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
		if (forceQuitOnCmdQ) {
			Platform.exit();
		}
	}

	public void setForceQuitOnCmdQ(boolean forceQuit) {
		this.forceQuitOnCmdQ = forceQuit;
	}

}
