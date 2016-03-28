//
//  NSMenuItemFX.h
//  NSMenuFX
//
//  Created by Jan Gassen on 12/03/16.
//  Copyright © 2016 Jan Gassen. All rights reserved.
//

#ifndef NSMenuItemFX_h
#define NSMenuItemFX_h

#import <AppKit/AppKit.h>
#import <JavaVM/jni.h>

@interface NSMenuItemFX : NSObject

+(id) fromJObject:(JNIEnv*)env obj:(jobject)obj;

+(void)handleClickAction:(id)sender;

@end

#ifdef __cplusplus
extern "C" {
#endif
    JNIEXPORT jobject JNICALL Java_de_codecentric_centerdevice_cocoa_NSMenuItem_alloc(JNIEnv *env, jobject thisObj);
    
    JNIEXPORT void JNICALL Java_de_codecentric_centerdevice_cocoa_NSMenuItem_release(JNIEnv *env, jobject thisObj);
    
    JNIEXPORT jobject JNICALL Java_de_codecentric_centerdevice_cocoa_NSMenuItem_init(JNIEnv *env, jobject thisObj, jstring title, jobject eventHandler, jstring keyEquivalent);
    
    JNIEXPORT jstring JNICALL Java_de_codecentric_centerdevice_cocoa_NSMenuItem_title(JNIEnv *env, jobject thisObj);

    JNIEXPORT jboolean JNICALL Java_de_codecentric_centerdevice_cocoa_NSMenuItem_hasSubmenu(JNIEnv *env, jobject thisObj);
    
    JNIEXPORT jobject JNICALL Java_de_codecentric_centerdevice_cocoa_NSMenuItem_submenu(JNIEnv *env, jobject thisObj);
    
    JNIEXPORT void JNICALL Java_de_codecentric_centerdevice_cocoa_NSMenuItem_setSubmenu(JNIEnv *env, jobject thisObj, jobject subMenu);

    JNIEXPORT void JNICALL Java_de_codecentric_centerdevice_cocoa_NSMenuItem_setTitle(JNIEnv *env, jobject thisObj, jstring title);
#ifdef __cplusplus
}
#endif

#endif /* NSMenuItemFX_h */
