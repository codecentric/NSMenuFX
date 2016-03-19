//
//  NSApplicationDelegateFX.h
//  NSMenuFX
//
//  Created by Jan Gassen on 14/03/16.
//  Copyright © 2016 Jan Gassen. All rights reserved.
//

#ifndef NSApplicationDelegateFX_h
#define NSApplicationDelegateFX_h

#import <AppKit/AppKit.h>

@interface NSApplicationDelegateFX: NSObject<NSApplicationDelegate>

@property NSMenu* dockMenu;

- (id)init;

- (id)initWithDockMenu:(NSMenu*)menu;

- (void)applicationDidUpdate:(NSNotification *)aNotification;

- (NSMenu *)applicationDockMenu:(NSApplication *)sender;

@end

#endif /* NSApplicationDelegateFX_h */
