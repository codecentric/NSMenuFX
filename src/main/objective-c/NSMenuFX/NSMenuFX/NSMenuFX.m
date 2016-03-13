//
//  NSMenuFX.m
//  NSMenuFX
//
//  Created by Jan Gassen on 07/03/16.
//  Copyright © 2016 Jan Gassen. All rights reserved.
//
#import "NSMenuFX.h"
#import "NSObjectUtils.h"
#import "NSStringUtils.h"

@implementation NSMenuFX

+(NSObjectUtils*) getUtils:(JNIEnv*)env {
    return [[NSObjectUtils alloc] init:env name:"de/codecentric/centerdevice/cocoa/NSMenu"];
}

+(NSMenu*) fromJObject:(JNIEnv*)env obj:(jobject)obj {
    return [[self getUtils:env] getId:obj];
}

@end

jobject Java_de_codecentric_centerdevice_cocoa_NSMenu_alloc(JNIEnv *env, jobject thisObj) {
    return [[NSMenuFX getUtils:env] createJObject:[NSMenu alloc]];
}

void Java_de_codecentric_centerdevice_cocoa_NSMenu_release(JNIEnv *env, jobject thisObj) {
    [[NSMenuFX getUtils:env] releaseObject:thisObj];
}

jobject Java_de_codecentric_centerdevice_cocoa_NSMenu_init(JNIEnv *env, jobject thisObj, jstring title) {
    NSObjectUtils* utils = [NSMenuFX getUtils:env];
    NSMenu* menu = [utils getId:thisObj];
    id result = [menu initWithTitle:[NSStringUtils convertJavaStringToCocoa:env javaString:title]];
    if (menu != result) {
        [utils updateId:thisObj newId:result];
    }
    return thisObj;
}

jstring Java_de_codecentric_centerdevice_cocoa_NSMenu_title(JNIEnv *env, jobject thisObj) {
    NSMenu* menu = [NSMenuFX fromJObject:env obj:thisObj];
    return [NSStringUtils convertNSStringToJava:env nsString:[menu title]];
}

jlong Java_de_codecentric_centerdevice_cocoa_NSMenu_numberOfItems(JNIEnv *env, jobject thisObj) {
    return [[NSMenuFX fromJObject:env obj:thisObj] numberOfItems];
}

void Java_de_codecentric_centerdevice_cocoa_NSMenu_removeAllItems(JNIEnv *env, jobject thisObj) {
    [[NSMenuFX fromJObject:env obj:thisObj] removeAllItems];
}

void Java_de_codecentric_centerdevice_cocoa_NSMenu_addItem(JNIEnv *env, jobject thisObj, jobject item) {
    NSObjectUtils* menuItemUtils = [[NSObjectUtils alloc] init:env name:"de/codecentric/centerdevice/cocoa/NSMenuItem"];
    NSMenuItem* menuItem = [menuItemUtils getId:item];
    [[NSMenuFX fromJObject:env obj:thisObj] addItem:menuItem];
}

void Java_de_codecentric_centerdevice_cocoa_NSMenu_insertItem(JNIEnv *env, jobject thisObj, jobject item, jint index) {
    NSObjectUtils* menuItemUtils = [[NSObjectUtils alloc] init:env name:"de/codecentric/centerdevice/cocoa/NSMenuItem"];
    NSMenuItem* menuItem = [menuItemUtils getId:item];
    [[NSMenuFX fromJObject:env obj:thisObj] insertItem:menuItem atIndex:index];
}

jobject Java_de_codecentric_centerdevice_cocoa_NSMenu_itemAtIndex(JNIEnv *env, jobject thisObj, jint index) {
    NSObjectUtils* menuItemUtils = [[NSObjectUtils alloc] init:env name:"de/codecentric/centerdevice/cocoa/NSMenuItem"];
    NSMenu* menu = [NSMenuFX fromJObject:env obj:thisObj];
    return [menuItemUtils createJObject:[menu itemAtIndex:index]];
}