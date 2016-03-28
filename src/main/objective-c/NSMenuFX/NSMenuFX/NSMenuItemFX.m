//
//  NSMenuItemFX.m
//  NSMenuFX
//
//  Created by Jan Gassen on 12/03/16.
//  Copyright © 2016 Jan Gassen. All rights reserved.
//
#import "NSMenuItemFX.h"
#import "NSMenuItem+JObject.h"
#import "NSStringUtils.h"
#import "NSObjectUtilsFactory.h"
#import <Foundation/Foundation.h>

@implementation NSMenuItemFX

static NSObjectUtils* utils = nil;

+(NSObjectUtils*) getUtils {
    if (utils == nil) {
        utils = [NSObjectUtilsFactory getInstance:JNI_NSMenuItem];
    }
    return utils;
}

+(NSMenuItem*) fromJObject:(JNIEnv*)env obj:(jobject)obj {
    return [[self getUtils] getId:env obj:obj];
}

+ (void)click:(JNIEnv *)env obj:(jobject)obj {
    jclass klass = (*env)->GetObjectClass(env, obj);
    jmethodID clicked = (*env)->GetMethodID(env, klass, "handle", "(Ljavafx/event/Event;)V");
    // TODO: create action event    
    (*env)->CallVoidMethod(env, obj, clicked, nil);
}

+(void)handleClickAction:(id)sender {
    if ([sender isKindOfClass:[NSMenuItem class]]) {
        NSMenuItem* item = sender;
        JNIEnv* env = [item jniEnv];
        jobject obj = [item jObject];
        if (env != nil && obj != nil) {
            [self click:env obj:obj];
        }
    }
}

@end

jobject Java_de_codecentric_centerdevice_cocoa_NSMenuItem_alloc(JNIEnv *env, jobject thisObj) {
    NSMenuItem* item = [NSMenuItem alloc];
    return [[NSMenuItemFX getUtils] createJObject:env obj:item];
}

void Java_de_codecentric_centerdevice_cocoa_NSMenuItem_release(JNIEnv *env, jobject thisObj) {
    NSObjectUtils* utils = [NSMenuItemFX getUtils];
    NSMenuItem* item = [utils getId:env obj:thisObj];
    [item unsetJObject:env];
    [utils releaseObject:item];
}

jobject Java_de_codecentric_centerdevice_cocoa_NSMenuItem_init(JNIEnv *env, jobject thisObj, jstring title, jobject eventHandler, jstring keyEquivalent) {
    NSObjectUtils* utils = [NSMenuItemFX getUtils];
    NSMenuItem* item = [utils getId:env obj:thisObj];

    NSString* nsTitle = [NSStringUtils convertJavaStringToCocoa:env javaString:title];
    NSString* nsKeyEquivalent = [NSStringUtils convertJavaStringToCocoa:env javaString:keyEquivalent];
    
    [item setJObject:env jObject:eventHandler];
    
    id result = [item initWithTitle:nsTitle action:@selector(handleClickAction:) keyEquivalent:nsKeyEquivalent];
    [result setTarget:[NSMenuItemFX class]];
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
    NSObjectUtils* menuUtils = [NSObjectUtilsFactory getInstance:JNI_NSMenu];
    NSMenuItem* item = [NSMenuItemFX fromJObject:env obj:thisObj];
    return [menuUtils createJObject:env obj:[item submenu]];
}

void Java_de_codecentric_centerdevice_cocoa_NSMenuItem_setSubmenu(JNIEnv *env, jobject thisObj, jobject subMenu) {
    NSObjectUtils* menuUtils = [NSObjectUtilsFactory getInstance:JNI_NSMenu];
    NSMenuItem* item = [NSMenuItemFX fromJObject:env obj:thisObj];
    [item setSubmenu:[menuUtils getId:env obj:subMenu]];
}

void Java_de_codecentric_centerdevice_cocoa_NSMenuItem_setTitle(JNIEnv *env, jobject thisObj, jstring title) {
    NSString* nsTitle = [NSStringUtils convertJavaStringToCocoa:env javaString:title];
    [[NSMenuItemFX fromJObject:env obj:thisObj] setTitle:nsTitle];
}