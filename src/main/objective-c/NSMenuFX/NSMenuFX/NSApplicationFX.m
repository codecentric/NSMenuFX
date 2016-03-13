//
//  NSApplicationFX.m
//  NSMenuFX
//
//  Created by Jan Gassen on 13/03/16.
//  Copyright © 2016 Jan Gassen. All rights reserved.
//

#import "NSApplicationFX.h"
#import "NSObjectUtils.h"
#import <AppKit/AppKit.h>
#include <JavaVM/jni.h>
    
jobject Java_de_codecentric_centerdevice_cocoa_NSApplication_sharedApplication(JNIEnv *env, jobject thisObj) {
    NSObjectUtils* utils = [[NSObjectUtils alloc] init:env name:"de/codecentric/centerdevice/cocoa/NSApplication"];
    return [utils createJObject:[NSApplication sharedApplication]];
}

void Java_de_codecentric_centerdevice_cocoa_NSApplication_hideOtherApplications(JNIEnv *env, jobject thisObj) {
    NSObjectUtils* utils = [[NSObjectUtils alloc] init:env name:"de/codecentric/centerdevice/cocoa/NSApplication"];
    NSApplication* application = [utils getId:thisObj];
    [application hideOtherApplications:nil];
}

void Java_de_codecentric_centerdevice_cocoa_NSApplication_unhideAllApplications(JNIEnv *env, jobject thisObj) {
    NSObjectUtils* utils = [[NSObjectUtils alloc] init:env name:"de/codecentric/centerdevice/cocoa/NSApplication"];
    NSApplication* application = [utils getId:thisObj];
    [application unhideAllApplications:nil];
}

void Java_de_codecentric_centerdevice_cocoa_NSApplication_hide(JNIEnv *env, jobject thisObj) {
    NSObjectUtils* utils = [[NSObjectUtils alloc] init:env name:"de/codecentric/centerdevice/cocoa/NSApplication"];
    NSApplication* application = [utils getId:thisObj];
    [application hide:nil];
}

jobject Java_de_codecentric_centerdevice_cocoa_NSApplication_mainMenu(JNIEnv *env, jobject thisObj) {
    NSObjectUtils* appUtils = [[NSObjectUtils alloc] init:env name:"de/codecentric/centerdevice/cocoa/NSApplication"];
    NSObjectUtils* menuUtils = [[NSObjectUtils alloc] init:env name:"de/codecentric/centerdevice/cocoa/NSMenu"];
    NSApplication* application = [appUtils getId:thisObj];
    NSMenu* menu = [application mainMenu];
    return [menuUtils createJObject:menu];
}