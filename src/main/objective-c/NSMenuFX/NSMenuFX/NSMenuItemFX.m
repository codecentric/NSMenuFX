//
//  NSMenuItemFX.m
//  NSMenuFX
//
//  Created by Jan Gassen on 12/03/16.
//  Copyright © 2016 Jan Gassen. All rights reserved.
//
#import "NSMenuItemFX.h"
#import "NSObject+JObject.h"
#import "NSStringUtils.h"
#import <Foundation/Foundation.h>

@implementation NSMenuItemFX

+(NSObjectUtils*) getUtils:(JNIEnv*)env {
    return [[NSObjectUtils alloc] init:env name:"de/codecentric/centerdevice/cocoa/NSMenuItem"];
}

+(NSMenuItem*) fromJObject:(JNIEnv*)env obj:(jobject)obj {
    return [[self getUtils:env] getId:obj];
}

+(void)handleClickAction:(id)sender {
    NSLog(@"Sender: %@", sender);
}

@end

jobject Java_de_codecentric_centerdevice_cocoa_NSMenuItem_alloc(JNIEnv *env, jobject thisObj) {
    NSMenuItem* item = [NSMenuItem alloc];
    [item setJObject:thisObj];
    return [[NSMenuItemFX getUtils:env] createJObject:item];
}

void Java_de_codecentric_centerdevice_cocoa_NSMenuItem_release(JNIEnv *env, jobject thisObj) {
    [[NSMenuItemFX getUtils:env] releaseObject:thisObj];
}

jobject Java_de_codecentric_centerdevice_cocoa_NSMenuItem_init(JNIEnv *env, jobject thisObj, jstring title, jstring keyEquivalent) {
    NSObjectUtils* utils = [NSMenuItemFX getUtils:env];
    NSMenuItem* item = [utils getId:thisObj];

    NSString* nsTitle = [NSStringUtils convertJavaStringToCocoa:env javaString:title];
    NSString* nsKeyEquivalent = [NSStringUtils convertJavaStringToCocoa:env javaString:keyEquivalent];
    
    id result = [item initWithTitle:nsTitle action:@selector(handleClickAction:) keyEquivalent:nsKeyEquivalent];
    if (result != item) {
        [utils updateId:thisObj newId:result];
    }
            
    return thisObj;
}

jstring Java_de_codecentric_centerdevice_cocoa_NSMenuItem_title(JNIEnv *env, jobject thisObj) {
    NSMenuItem* item = [NSMenuItemFX fromJObject:env obj:thisObj];
    return [NSStringUtils convertNSStringToJava:env nsString:[item title]];
}

jboolean Java_de_codecentric_centerdevice_cocoa_NSMenuItem_hasSubmenu(JNIEnv *env, jobject thisObj) {
    return [[NSMenuItemFX fromJObject:env obj:thisObj] hasSubmenu];
}

jobject Java_de_codecentric_centerdevice_cocoa_NSMenuItem_submenu(JNIEnv *env, jobject thisObj) {
    NSObjectUtils* menuUtils = [[NSObjectUtils alloc] init:env name:"de/codecentric/centerdevice/cocoa/NSMenu"];
    NSMenuItem* item = [NSMenuItemFX fromJObject:env obj:thisObj];
    return [menuUtils createJObject:[item submenu]];
}

void Java_de_codecentric_centerdevice_cocoa_NSMenuItem_setSubmenu(JNIEnv *env, jobject thisObj, jobject subMenu) {
    NSObjectUtils* menuUtils = [[NSObjectUtils alloc] init:env name:"de/codecentric/centerdevice/cocoa/NSMenu"];
    NSMenuItem* item = [NSMenuItemFX fromJObject:env obj:thisObj];
    [item setSubmenu:[menuUtils getId:subMenu]];
}
