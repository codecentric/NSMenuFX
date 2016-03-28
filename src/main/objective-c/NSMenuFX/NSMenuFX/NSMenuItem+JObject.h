//
//  NSObject+JObject.h
//  NSMenuFX
//
//  Created by Jan Gassen on 12/03/16.
//  Copyright © 2016 Jan Gassen. All rights reserved.
//

#ifndef NSObject_JObject_h
#define NSObject_JObject_h
#import <AppKit/AppKit.h>
#import <JavaVM/jni.h>

@interface NSMenuItem (NSMenuItemWithJObject)

-(jobject) jObject;

-(JNIEnv*) jniEnv;

-(void) setJObject:(JNIEnv*)env jObject:(jobject)jobject;

-(void) unsetJObject:(JNIEnv*)env;

@end

#endif /* NSObject_JObject_h */
