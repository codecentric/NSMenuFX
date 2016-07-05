//
//  NSApplicationFX.h
//  NSMenuFX
//
//  Created by Jan Gassen on 13/03/16.
//  Copyright © 2016 Jan Gassen. All rights reserved.
//

#ifndef NSApplicationFX_h
#define NSApplicationFX_h

#import <Foundation/Foundation.h>
#import "NSObjectUtils.h"
#import <JavaVM/jni.h>

@interface NSApplicationFX : NSObject

+(NSObjectUtils*) getUtils;

+(NSApplication*) fromJObject:(JNIEnv*)env obj:(jobject)obj;

@end

#ifdef __cplusplus
extern "C" {
#endif
    JNIEXPORT void JNICALL Java_de_codecentric_centerdevice_cocoa_NSApplication_hideOtherApplications(JNIEnv *env, jobject thisObj);
    
    JNIEXPORT void JNICALL Java_de_codecentric_centerdevice_cocoa_NSApplication_unhideAllApplications(JNIEnv *env, jobject  thisObj);
    
    JNIEXPORT void JNICALL Java_de_codecentric_centerdevice_cocoa_NSApplication_hide(JNIEnv *env, jobject thisObj);
    
    JNIEXPORT void JNICALL Java_de_codecentric_centerdevice_cocoa_NSApplication_setWindowsMenu(JNIEnv *env, jobject thisObj, jobject menu);
    
    JNIEXPORT jobject JNICALL Java_de_codecentric_centerdevice_cocoa_NSApplication_windowsMenu(JNIEnv *env, jobject thisObj);
    
    JNIEXPORT void JNICALL Java_de_codecentric_centerdevice_cocoa_NSApplication_setHelpMenu(JNIEnv *env, jobject thisObj, jobject menu);
    
    JNIEXPORT jobject JNICALL Java_de_codecentric_centerdevice_cocoa_NSApplication_helpMenu(JNIEnv *env, jobject thisObj);
    
    JNIEXPORT jobject JNICALL Java_de_codecentric_centerdevice_cocoa_NSApplication_mainMenu(JNIEnv *env, jobject thisObj);
    
    JNIEXPORT void JNICALL Java_de_codecentric_centerdevice_cocoa_NSApplication_setMainMenu(JNIEnv *env, jobject thisObj, jobject menu);
    
#ifdef __cplusplus
}
#endif

#endif /* NSApplicationFX_h */
