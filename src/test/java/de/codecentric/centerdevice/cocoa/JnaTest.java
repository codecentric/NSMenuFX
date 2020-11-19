package de.codecentric.centerdevice.cocoa;

import de.jangassen.jfa.appkit.NSApplication;
import de.jangassen.jfa.appkit.NSMenu;
import org.junit.Test;

public class JnaTest {
    @Test
    public void test() {
        NSApplication sharedApplication = NSApplication.sharedApplication();

        NSMenu nsMenu = sharedApplication.mainMenu();

        System.out.println(sharedApplication);
        System.out.println(nsMenu);
    }

}
