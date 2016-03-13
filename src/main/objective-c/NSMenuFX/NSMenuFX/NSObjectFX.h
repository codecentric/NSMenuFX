//
//  NSObjectFX.h
//  NSMenuFX
//
//  Created by Jan Gassen on 13/03/16.
//  Copyright © 2016 Jan Gassen. All rights reserved.
//

#ifndef NSObjectFX_h
#define NSObjectFX_h

#import <Foundation/Foundation.h>
#import <JavaVM/jni.h>

#ifdef __cplusplus
extern "C" {
#endif
    JNIEXPORT jstring JNICALL Java_de_codecentric_centerdevice_cocoa_NSObject_toString(JNIEnv *env, jobject thisObj);
#ifdef __cplusplus
}
#endif

#endif /* NSObjectFX_h */
