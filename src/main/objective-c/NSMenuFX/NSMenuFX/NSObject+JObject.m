//
//  NSObject+JObject.m
//  NSMenuFX
//
//  Created by Jan Gassen on 12/03/16.
//  Copyright © 2016 Jan Gassen. All rights reserved.
//
#import "NSObject+JObject.h"
#import <Foundation/Foundation.h>

@implementation NSMenuItem (NSMenuItemWithJObject)
jobject obj = nil;

- (void)setJObject:(jobject)newObj
{
    obj = newObj;
}

- (jobject)jObject
{
    return obj;
}
@end
