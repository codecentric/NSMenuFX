//
//  NSObjectUtilsFactory.m
//  NSMenuFX
//
//  Created by Jan Gassen on 28/03/16.
//  Copyright © 2016 Jan Gassen. All rights reserved.
//

#import "NSObjectUtilsFactory.h"

@implementation NSObjectUtilsFactory

+(id)getInstance:(const char*)name {
    static NSDictionary *instances = nil;
    if (instances == nil) {
        instances = [[NSMutableDictionary alloc] init];
    }
    NSString *key = [NSString stringWithUTF8String:name];
    id instance = [instances objectForKey:key];
    if (instance == nil) {
        instance = [[NSObjectUtils alloc] init:name];
        [instances setValue:instance forKey:key];
    }
    
    return instance;
}

@end
