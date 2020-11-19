package de.codecentric.centerdevice.glass;

import com.sun.javafx.tk.Toolkit;

public class AdapterContext {

    private static AdapterContext instance;

    private MacApplicationAdapter applicationAdapter;

    private AdapterContext(MacApplicationAdapter applicationAdapter) {
        this.applicationAdapter = applicationAdapter;
    }

    public static AdapterContext getContext() {
        if (instance == null) {
            instance = createContext();
        }

        return instance;
    }

    public MacApplicationAdapter getApplicationAdapter() {
        return applicationAdapter;
    }

    private static AdapterContext createContext() {
        if (!Toolkit.getToolkit().getSystemMenu().isSupported()) {
            return null;
        }

        return new AdapterContext(new MacApplicationAdapter());
    }
}
