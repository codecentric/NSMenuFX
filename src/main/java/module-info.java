module nsmenufx {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.base;
    requires com.sun.jna;
    requires jfa;

    exports de.codecentric.centerdevice;
    exports de.codecentric.centerdevice.labels;
    exports de.codecentric.centerdevice.dialogs.about;
    exports de.codecentric.centerdevice.icns;
}