# NSMenuFX

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/61379f5f801c464fb8cf5488d7c9f0c3)](https://www.codacy.com/app/0x4a616e/NSMenuFX?utm_source=github.com&utm_medium=referral&utm_content=codecentric/NSMenuFX&utm_campaign=badger)

A simple library to customize the OS X menu bar to give your JavaFX app
a more [native look and feel](https://developer.apple.com/library/mac/documentation/UserExperience/Conceptual/OSXHIGuidelines/MenuBarMenus.html).  

NSMenuFX helps you to

* Customize the auto-generated application menu of your JavaFX app

![Custom App Menu Screenshot](https://raw.githubusercontent.com/codecentric/NSMenuFX/master/Assets/Screenshots/AppMenu.png)

* Create common OS X menus like the Window menu

![Window Menu Screenshot](https://raw.githubusercontent.com/codecentric/NSMenuFX/master/Assets/Screenshots/WindowMenu.png)

* Quickly create an about menu

![About Menu Screenshot](https://raw.githubusercontent.com/codecentric/NSMenuFX/master/Assets/Screenshots/AboutStage.png)

* Automatically use the same menu bar for all stages

NSMenuFX is now completely written in JavaFX and does no longer use any
further dependencies.

## Maven

Add the following lines to the dependencies in your `pom.xml`

	<dependency>
    	<groupId>de.codecentric.centerdevice</groupId>
    	<artifactId>centerdevice-nsmenufx</artifactId>
    	<version>2.1.6</version>
    </dependency>
## Gradle

Add the following line to the dependencies in your `build.gradle`

	compile "de.codecentric.centerdevice:centerdevice-nsmenufx:2.1.6"

## Usage Examples

The following snippet shows a simple example on how to change a menu item in
the application menu:

    // Get the toolkit
    MenuToolkit tk = MenuToolkit.toolkit();

    // Create the default Application menu
	Menu defaultApplicationMenu = tk.createDefaultApplicationMenu("test");

	// Update the existing Application menu
	tk.setApplicationMenu(defaultApplicationMenu);

	// Since we now have a reference to the menu, we can rename items
	defaultApplicationMenu.getItems().get(1).setText("Hide all the otters");

To set a global menu bar that is used for all stages:

	// Create a new menu bar
	MenuBar bar = new MenuBar();

	// Add the default application menu
	bar.getMenus().add(tk.createDefaultApplicationMenu("test"));

	// Add some more Menus...

	// Use the menu bar for all stages including new ones
	tk.setGlobalMenuBar(bar);

To find more usage examples, have a look into the test classes.

## Known issues

NSMenuFX no longer supports changing the title of the application menu at
runtime. This has always been a bit "hacky" as it is not really supported
by OS X. As a result, the new name was no longer bold faced when it was
changed with previous versions of NSMenuFX.

To set the title of the application menu to the name of your application,
you need to bundle the application and set `CFBundleName` in `Info.plist`.
