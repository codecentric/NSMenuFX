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
+(void)handleClickAction:(id)sender;
@end

#ifdef __cplusplus
extern "C" {
#endif
    JNIEXPORT jobject JNICALL Java_de_codecentric_centerdevice_cocoa_NSMenuItem_alloc(JNIEnv *env, jobject thisObj);
    
    JNIEXPORT jobject JNICALL Java_de_codecentric_centerdevice_cocoa_NSMenuItem_init(JNIEnv *env, jobject thisObj, jstring title, jstring keyEquivalent);

#ifdef __cplusplus
}
#endif

#endif /* NSMenuItemFX_h */
