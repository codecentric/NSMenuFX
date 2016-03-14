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

static NSObjectUtils* utils = nil;

+(NSObjectUtils*) getUtils {
    if (utils == nil) {
        utils = [[NSObjectUtils alloc] init:"de/codecentric/centerdevice/cocoa/NSMenuItem"];
    }
    return utils;
}

+(NSMenuItem*) fromJObject:(JNIEnv*)env obj:(jobject)obj {
    return [[self getUtils] getId:env obj:obj];
}

+(void)handleClickAction:(id)sender {
    NSLog(@"Sender: %@", sender);
}

@end

jobject Java_de_codecentric_centerdevice_cocoa_NSMenuItem_alloc(JNIEnv *env, jobject thisObj) {
    NSMenuItem* item = [NSMenuItem alloc];
    [item setJObject:thisObj];
    return [[NSMenuItemFX getUtils] createJObject:env obj:item];
}

void Java_de_codecentric_centerdevice_cocoa_NSMenuItem_release(JNIEnv *env, jobject thisObj) {
    [[NSMenuItemFX getUtils] releaseObject:env obj:thisObj];
}

jobject Java_de_codecentric_centerdevice_cocoa_NSMenuItem_init(JNIEnv *env, jobject thisObj, jstring title, jstring keyEquivalent) {
    NSObjectUtils* utils = [NSMenuItemFX getUtils];
    NSMenuItem* item = [utils getId:env obj:thisObj];

    NSString* nsTitle = [NSStringUtils convertJavaStringToCocoa:env javaString:title];
    NSString* nsKeyEquivalent = [NSStringUtils convertJavaStringToCocoa:env javaString:keyEquivalent];
    
    id result = [item initWithTitle:nsTitle action:@selector(handleClickAction:) keyEquivalent:nsKeyEquivalent];
    if (result != item) {
        [utils updateId:env obj:thisObj newId:result];
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
    NSObjectUtils* menuUtils = [[NSObjectUtils alloc] init:"de/codecentric/centerdevice/cocoa/NSMenu"];
    NSMenuItem* item = [NSMenuItemFX fromJObject:env obj:thisObj];
    return [menuUtils createJObject:env obj:[item submenu]];
}

void Java_de_codecentric_centerdevice_cocoa_NSMenuItem_setSubmenu(JNIEnv *env, jobject thisObj, jobject subMenu) {
    NSObjectUtils* menuUtils = [[NSObjectUtils alloc] init:"de/codecentric/centerdevice/cocoa/NSMenu"];
    NSMenuItem* item = [NSMenuItemFX fromJObject:env obj:thisObj];
    [item setSubmenu:[menuUtils getId:env obj:subMenu]];
}
