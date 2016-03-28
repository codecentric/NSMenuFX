//
//  NSMenuFX.h
//  NSMenuFX
//
//  Created by Jan Gassen on 07/03/16.
//  Copyright © 2016 Jan Gassen. All rights reserved.
//
#ifndef NSMenuFX_h
#define NSMenuFX_h

#import <AppKit/AppKit.h>
#import <JavaVM/jni.h>
#import "NSObjectUtils.h"

@interface NSMenuFX: NSObject

+(NSObjectUtils*) getUtils;

+(NSMenu*) fromJObject:(JNIEnv*)env obj:(jobject)obj;

@end

#ifdef __cplusplus
extern "C" {
#endif
    JNIEXPORT jobject JNICALL Java_de_codecentric_centerdevice_cocoa_NSMenu_alloc(JNIEnv *env, jobject thisObj);
    
    JNIEXPORT void JNICALL Java_de_codecentric_centerdevice_cocoa_NSMenu_release(JNIEnv *env, jobject thisObj);
    
    JNIEXPORT jobject JNICALL Java_de_codecentric_centerdevice_cocoa_NSMenu_init(JNIEnv *env, jobject thisObj, jstring title);
    
    JNIEXPORT void JNICALL Java_de_codecentric_centerdevice_cocoa_NSMenu_setAutoenablesItems(JNIEnv *env, jobject thisObj, jboolean autoEnable);
    
    JNIEXPORT void JNICALL Java_de_codecentric_centerdevice_cocoa_NSMenu_setTitle(JNIEnv *env, jobject thisObj, jstring title);
    
    JNIEXPORT jstring JNICALL Java_de_codecentric_centerdevice_cocoa_NSMenu_title(JNIEnv *env, jobject thisObj);
    
    JNIEXPORT jlong JNICALL Java_de_codecentric_centerdevice_cocoa_NSMenu_numberOfItems(JNIEnv *env, jobject thisObj);
    
    JNIEXPORT void JNICALL Java_de_codecentric_centerdevice_cocoa_NSMenu_removeAllItems(JNIEnv *env, jobject thisObj);
    
    JNIEXPORT void JNICALL Java_de_codecentric_centerdevice_cocoa_NSMenu_removeItem(JNIEnv *env, jobject thisObj, jobject menuItem);
    
    JNIEXPORT void JNICALL Java_de_codecentric_centerdevice_cocoa_NSMenu_removeItemAtIndex(JNIEnv *env, jobject thisObj, jint index);
    
    JNIEXPORT void JNICALL Java_de_codecentric_centerdevice_cocoa_NSMenu_addItem(JNIEnv *env, jobject thisObj, jobject item);
    
    JNIEXPORT void JNICALL Java_de_codecentric_centerdevice_cocoa_NSMenu_insertItem(JNIEnv *env, jobject thisObj, jobject item, jint index);
    
    JNIEXPORT jobject JNICALL Java_de_codecentric_centerdevice_cocoa_NSMenu_itemAtIndex(JNIEnv *env, jobject thisObj, jint index);
    
#ifdef __cplusplus
}
#endif


#endif