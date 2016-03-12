//
//  NSMenuFX.m
//  NSMenuFX
//
//  Created by Jan Gassen on 07/03/16.
//  Copyright © 2016 Jan Gassen. All rights reserved.
//
#import "NSMenuFX.h"
#import <AppKit/AppKit.h>
#include <JavaVM/jni.h>

@implementation NSMenuFX

@end

#ifdef __cplusplus
extern "C" {
#endif

    JNIEXPORT void JNICALL Java_de_codecentric_centerdevice_platform_NativeAdapter_hideOtherApplications(JNIEnv *env, jobject   thisObj) {
        [[NSApplication sharedApplication] hideOtherApplications:nil];
    }
    
    JNIEXPORT void JNICALL Java_de_codecentric_centerdevice_platform_NativeAdapter_unhideAllApplications(JNIEnv *env, jobject   thisObj) {
        [[NSApplication sharedApplication] unhideAllApplications:nil];
        
    }
    
    JNIEXPORT void JNICALL Java_de_codecentric_centerdevice_platform_NativeAdapter_hide(JNIEnv *env, jobject thisObj) {
        [[NSApplication sharedApplication] hide:nil];
    }

#ifdef __cplusplus
}
#endif