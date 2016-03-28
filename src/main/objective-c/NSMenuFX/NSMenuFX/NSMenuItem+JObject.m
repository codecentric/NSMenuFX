//
//  NSObject+JObject.m
//  NSMenuFX
//
//  Created by Jan Gassen on 12/03/16.
//  Copyright © 2016 Jan Gassen. All rights reserved.
//
#import "NSMenuItem+JObject.h"
#import <Foundation/Foundation.h>

@implementation NSMenuItem (NSMenuItemWithJObject)
JavaVM* javaVM = nil;
jobject obj = nil;
jint version;

-(jobject) jObject {
    return obj;
}

-(JNIEnv*) jniEnv {
    JNIEnv* env;
    if((*javaVM)->GetEnv(javaVM, (void **)&env, version) != JNI_OK) {
        return nil;
    }
    return env;
}

-(void) setJObject:(JNIEnv*)env jObject:(jobject)jobject {
    (*env)->GetJavaVM(env, &javaVM);
    version = (*env)->GetVersion(env);
    if (jobject != nil) {
        obj = (*env)->NewGlobalRef(env, jobject);
    }
}

-(void) unsetJObject:(JNIEnv*)env {
    if (obj != nil) {
        (*env)->DeleteGlobalRef(env, obj);
        obj = nil;
    }
}

@end
