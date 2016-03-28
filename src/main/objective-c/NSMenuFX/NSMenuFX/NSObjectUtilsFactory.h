//
//  NSObjectUtilsFactory.h
//  NSMenuFX
//
//  Created by Jan Gassen on 28/03/16.
//  Copyright © 2016 Jan Gassen. All rights reserved.
//

#ifndef NSObjectUtilsFactory_h
#define NSObjectUtilsFactory_h

#import "NSObjectUtils.h"

@interface NSObjectUtilsFactory : NSObjectUtils

+(id)getInstance:(const char*)name;

@end

static char* const JNI_NSMenuItem = "de/codecentric/centerdevice/cocoa/NSMenuItem";

static char* const JNI_NSMenu = "de/codecentric/centerdevice/cocoa/NSMenu";

#endif /* NSObjectUtilsFactory_h */
