//
//  NSMenuFX.m
//  NSMenuFX
//
//  Created by Jan Gassen on 07/03/16.
//  Copyright © 2016 Jan Gassen. All rights reserved.
//
#import "NSMenuFX.h"
#import "NSObjectUtilsFactory.h"
#import "NSStringUtils.h"

@implementation NSMenuFX

static NSObjectUtils* utils = nil;

+(NSObjectUtils*) getUtils {
    if (utils == nil) {
        utils = [NSObjectUtilsFactory getInstance:JNI_NSMenu];
    }
    return utils;
}

+(NSMenu*) fromJObject:(JNIEnv*)env obj:(jobject)obj {
    return [[self getUtils] getId:env obj:obj];
}

@end

jobject Java_de_codecentric_centerdevice_cocoa_NSMenu_alloc(JNIEnv *env, jobject thisObj) {
    return [[NSMenuFX getUtils] createJObject:env obj:[NSMenu alloc]];
}

void Java_de_codecentric_centerdevice_cocoa_NSMenu_release(JNIEnv *env, jobject thisObj) {
    [[NSMenuFX getUtils] releaseObject:env obj:thisObj];
}

jobject Java_de_codecentric_centerdevice_cocoa_NSMenu_init(JNIEnv *env, jobject thisObj, jstring title) {
    NSObjectUtils* utils = [NSMenuFX getUtils];
    NSMenu* menu = [utils getId:env obj:thisObj];
    id result = [menu initWithTitle:[NSStringUtils convertJavaStringToCocoa:env javaString:title]];
    if (menu != result) {
        [utils updateId:env obj:thisObj newId:result];
    }
    return thisObj;
}

jstring Java_de_codecentric_centerdevice_cocoa_NSMenu_title(JNIEnv *env, jobject thisObj) {
    NSMenu* menu = [NSMenuFX fromJObject:env obj:thisObj];
    return [NSStringUtils convertNSStringToJava:env nsString:[menu title]];
}

void Java_de_codecentric_centerdevice_cocoa_NSMenu_setAutoenablesItems(JNIEnv *env, jobject thisObj, jboolean autoEnable) {
    return [[NSMenuFX fromJObject:env obj:thisObj] setAutoenablesItems:autoEnable];
}

void Java_de_codecentric_centerdevice_cocoa_NSMenu_setTitle(JNIEnv *env, jobject thisObj, jstring title) {
    return [[NSMenuFX fromJObject:env obj:thisObj] setTitle:[NSStringUtils convertJavaStringToCocoa:env javaString:title]];
}

jlong Java_de_codecentric_centerdevice_cocoa_NSMenu_numberOfItems(JNIEnv *env, jobject thisObj) {
    return [[NSMenuFX fromJObject:env obj:thisObj] numberOfItems];
}

void Java_de_codecentric_centerdevice_cocoa_NSMenu_removeAllItems(JNIEnv *env, jobject thisObj) {
    [[NSMenuFX fromJObject:env obj:thisObj] removeAllItems];
}

void Java_de_codecentric_centerdevice_cocoa_NSMenu_removeItem(JNIEnv *env, jobject thisObj, jobject menuItem) {
    NSMenuItem* nsMenuItem = [[NSObjectUtilsFactory getInstance:JNI_NSMenuItem] getId:env obj:menuItem];
    [[NSMenuFX fromJObject:env obj:thisObj] removeItem:nsMenuItem];
}

void Java_de_codecentric_centerdevice_cocoa_NSMenu_removeItemAtIndex(JNIEnv *env, jobject thisObj, jint index) {
    [[NSMenuFX fromJObject:env obj:thisObj] removeItemAtIndex:index];
}

void Java_de_codecentric_centerdevice_cocoa_NSMenu_addItem(JNIEnv *env, jobject thisObj, jobject item) {
    NSMenuItem* nsMenuItem = [[NSObjectUtilsFactory getInstance:JNI_NSMenuItem] getId:env obj:item];
    [[NSMenuFX fromJObject:env obj:thisObj] addItem:nsMenuItem];
}

void Java_de_codecentric_centerdevice_cocoa_NSMenu_insertItem(JNIEnv *env, jobject thisObj, jobject item, jint index) {
    NSMenuItem* nsMenuItem = [[NSObjectUtilsFactory getInstance:JNI_NSMenuItem] getId:env obj:item];
    [[NSMenuFX fromJObject:env obj:thisObj] insertItem:nsMenuItem atIndex:index];
}

jobject Java_de_codecentric_centerdevice_cocoa_NSMenu_itemAtIndex(JNIEnv *env, jobject thisObj, jint index) {
    NSObjectUtils* menuItemUtils = [NSObjectUtilsFactory getInstance:JNI_NSMenuItem];
    NSMenu* menu = [NSMenuFX fromJObject:env obj:thisObj];
    return [menuItemUtils createJObject:env obj:[menu itemAtIndex:index]];
}