package de.codecentric.centerdevice.glass;

public class GlassAdaptionException extends RuntimeException {

	private static final long serialVersionUID = 7315344041984700277L;

	public GlassAdaptionException(Throwable e) {
		super(getExceptionMessage(e), e);
	}

	private static String getExceptionMessage(Throwable e) {
		String description;
		if (e instanceof NoSuchFieldException) {
			description = "Unable to find field \"" + e.getMessage() + "\"";
		} else if (e instanceof NoSuchMethodException) {
			description = "Unable to find method \"" + e.getMessage() + "\"";
		} else {
			description = e.getMessage();
		}

		return description + " (" + getEnvDescription() + ")";
	}

	private static String getEnvDescription() {
		return "Using Java " + System.getProperty("java.version") + " on " + System.getProperty("os.name");
	}
}
