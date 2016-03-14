//
//  NSObjectUtils.m
//  NSMenuFX
//
//  Created by Jan Gassen on 13/03/16.
//  Copyright © 2016 Jan Gassen. All rights reserved.
//

#import "NSObjectUtils.h"

@implementation NSObjectUtils

@synthesize className;

-(id)init:(const char*)name {
    self = [super init];
    if (self) {
        className = name;
    }
    return self;
}

-(jobject) createJObject:(JNIEnv*)env obj:(id)obj {
    if (obj == nil) {
        return nil;
    }
    
    jclass klass = (*env)->FindClass(env, className);
    jmethodID constructor = (*env)->GetMethodID(env, klass, "<init>", "(J)V");
    return (*env)->NewObject(env, klass, constructor, (long) [obj retain]);
}

-(id)getId:(JNIEnv*)env obj:(jobject) obj {
    jclass klass = (*env)->FindClass(env, className);
    jmethodID getId = (*env)->GetMethodID(env, klass, "getId", "()J");
    return (id) (*env)->CallLongMethod(env, obj, getId);;
}

-(void)updateId:(JNIEnv*)env obj:(jobject)obj newId:(id)newId {
    jclass klass = (*env)->FindClass(env, className);
    jmethodID setId = (*env)->GetMethodID(env, klass, "setId", "(J)V");
    (*env)->CallVoidMethod(env, obj, setId, (long) newId);
}

-(void)releaseObject:(JNIEnv*)env obj:(jobject) obj {
    [[self getId:env obj:obj] release];
}

@end