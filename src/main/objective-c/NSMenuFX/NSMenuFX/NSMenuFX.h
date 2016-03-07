//
//  NSMenuFX.h
//  NSMenuFX
//
//  Created by Jan Gassen on 07/03/16.
//  Copyright © 2016 Jan Gassen. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <JavaVM/jni.h>

@interface NSMenuFX : NSObject

- (void) someMethod;

@end

#ifdef __cplusplus
extern "C" {
#endif
    JNIEXPORT void JNICALL Java_de_codecentric_centerdevice_platform_NativeAdapter_hideOtherApplications(JavaVM *jvm, jobject thisObj);
    
    JNIEXPORT void JNICALL Java_de_codecentric_centerdevice_platform_NativeAdapter_unhideAllApplications(JavaVM *jvm, jobject   thisObj);
    
    JNIEXPORT void JNICALL Java_de_codecentric_centerdevice_platform_NativeAdapter_hide(JavaVM *jvm, jobject thisObj);
    
#ifdef __cplusplus
}
#endif