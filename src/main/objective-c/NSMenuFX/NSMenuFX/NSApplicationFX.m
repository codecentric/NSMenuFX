//
//  NSApplicationFX.m
//  NSMenuFX
//
//  Created by Jan Gassen on 13/03/16.
//  Copyright © 2016 Jan Gassen. All rights reserved.
//

#import "NSApplicationFX.h"
#import "NSObjectUtils.h"
#import "NSObjectUtilsFactory.h"
#import <AppKit/AppKit.h>
#include <JavaVM/jni.h>

@implementation NSApplicationFX

static NSObjectUtils* utils = nil;

+(NSObjectUtils*) getUtils {
    if (utils == nil) {
        utils = [[NSObjectUtils alloc] init:"de/codecentric/centerdevice/cocoa/NSApplication"];
    }
    return utils;
}

+(NSApplication*) fromJObject:(JNIEnv*)env obj:(jobject)obj {
    return [[self getUtils] getId:env obj:obj];
}

@end

jobject Java_de_codecentric_centerdevice_cocoa_NSApplication_sharedApplication(JNIEnv *env, jobject thisObj) {
    return [[NSApplicationFX getUtils] createJObject:env obj:[NSApplication sharedApplication]];
}

void Java_de_codecentric_centerdevice_cocoa_NSApplication_hideOtherApplications(JNIEnv *env, jobject thisObj) {
    [[NSApplicationFX fromJObject:env obj:thisObj] hideOtherApplications:nil];
}

void Java_de_codecentric_centerdevice_cocoa_NSApplication_unhideAllApplications(JNIEnv *env, jobject thisObj) {
    [[NSApplicationFX fromJObject:env obj:thisObj] unhideAllApplications:nil];
}

void Java_de_codecentric_centerdevice_cocoa_NSApplication_hide(JNIEnv *env, jobject thisObj) {
    [[NSApplicationFX fromJObject:env obj:thisObj] hide:nil];
}

void Java_de_codecentric_centerdevice_cocoa_NSApplication_setWindowsMenu(JNIEnv *env, jobject thisObj, jobject menu) {
    NSObjectUtils* menuUtils = [NSObjectUtilsFactory getInstance:JNI_NSMenu];
    [[NSApplicationFX fromJObject:env obj:thisObj] setWindowsMenu:[menuUtils getId:env obj:menu]];
}

jobject Java_de_codecentric_centerdevice_cocoa_NSApplication_windowsMenu(JNIEnv *env, jobject thisObj) {
    NSObjectUtils* menuUtils = [NSObjectUtilsFactory getInstance:JNI_NSMenu];
    return [menuUtils createJObject:env obj:[[NSApplicationFX fromJObject:env obj:thisObj] windowsMenu]];
}

void Java_de_codecentric_centerdevice_cocoa_NSApplication_setHelpMenu(JNIEnv *env, jobject thisObj, jobject menu) {
    NSObjectUtils* menuUtils = [NSObjectUtilsFactory getInstance:JNI_NSMenu];
    [[NSApplicationFX fromJObject:env obj:thisObj] setHelpMenu:[menuUtils getId:env obj:menu]];
}

jobject Java_de_codecentric_centerdevice_cocoa_NSApplication_helpMenu(JNIEnv *env, jobject thisObj) {
    NSObjectUtils* menuUtils = [NSObjectUtilsFactory getInstance:JNI_NSMenu];
    return [menuUtils createJObject:env obj:[[NSApplicationFX fromJObject:env obj:thisObj] helpMenu]];
}

jobject Java_de_codecentric_centerdevice_cocoa_NSApplication_mainMenu(JNIEnv *env, jobject thisObj) {
    NSObjectUtils* menuUtils = [NSObjectUtilsFactory getInstance:JNI_NSMenu];
    NSMenu* menu = [[NSApplicationFX fromJObject:env obj:thisObj] mainMenu];
    return [menuUtils createJObject:env obj:menu];
}

void Java_de_codecentric_centerdevice_cocoa_NSApplication_setMainMenu(JNIEnv *env, jobject thisObj, jobject menu) {
    NSObjectUtils* menuUtils = [NSObjectUtilsFactory getInstance:JNI_NSMenu];
    [[NSApplicationFX fromJObject:env obj:thisObj] setMainMenu:[menuUtils getId:env obj:menu]];
}