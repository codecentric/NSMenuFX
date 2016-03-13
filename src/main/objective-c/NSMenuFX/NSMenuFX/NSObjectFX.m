//
//  NSObjectFX.m
//  NSMenuFX
//
//  Created by Jan Gassen on 13/03/16.
//  Copyright © 2016 Jan Gassen. All rights reserved.
//
#import "NSObjectFX.h"
#import "NSObjectUtils.h"
#import "NSStringUtils.h"
#import <Foundation/Foundation.h>

jstring Java_de_codecentric_centerdevice_cocoa_NSObject_toString(JNIEnv *env, jobject thisObj) {
    NSObjectUtils* utils = [[NSObjectUtils alloc] init:env name:"de/codecentric/centerdevice/cocoa/NSObject"];
    NSObject* obj = [utils getId:thisObj];
    return [NSStringUtils convertNSStringToJava:env nsString:[obj description]];
}