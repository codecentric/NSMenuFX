//
//  NSApplicationDelegateFX.m
//  NSMenuFX
//
//  Created by Jan Gassen on 14/03/16.
//  Copyright © 2016 Jan Gassen. All rights reserved.
//

#import "NSApplicationDelegateFX.h"
#import <Foundation/Foundation.h>

@implementation NSApplicationDelegateFX

@synthesize dockMenu;


-(id)init {
    return [self initWithDockMenu:nil];
}

-(id)initWithDockMenu:(NSMenu*)menu {
    if (self = [super init])  {
        self.dockMenu = menu;
    }
    return self;
}

- (void)applicationDidUpdate:(NSNotification *)aNotification {
    // Use to update window menu if not done automatically
}

- (NSMenu *)applicationDockMenu:(NSApplication *)sender {
    return dockMenu;
}

@end
