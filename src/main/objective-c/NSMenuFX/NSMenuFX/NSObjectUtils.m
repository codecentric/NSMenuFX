//
//  NSObjectUtils.m
//  NSMenuFX
//
//  Created by Jan Gassen on 13/03/16.
//  Copyright © 2016 Jan Gassen. All rights reserved.
//

#import "NSObjectUtils.h"

@implementation NSObjectUtils

@synthesize env;
@synthesize className;

-(id)init:(JNIEnv*)jniEnv name:(const char*)name {
    self = [super init];
    if (self) {
        env = jniEnv;
        className = name;
    }
    return self;
}

-(jobject) createJObject:(id) item {
    if (item == nil) {
        return nil;
    }
    
    jclass klass = (*env)->FindClass(env, className);
    jmethodID constructor = (*env)->GetMethodID(env, klass, "<init>", "(J)V");
    return (*env)->NewObject(env, klass, constructor, (long) [item retain]);
}

-(id)getId:(jobject) obj {
    jclass klass = (*env)->FindClass(env, className);
    jmethodID getId = (*env)->GetMethodID(env, klass, "getId", "()J");
    return (id) (*env)->CallLongMethod(env, obj, getId);;
}

-(void)updateId:(jobject)obj newId:(id)newId {
    jclass klass = (*env)->FindClass(env, className);
    jmethodID setId = (*env)->GetMethodID(env, klass, "setId", "(J)V");
    (*env)->CallVoidMethod(env, obj, setId, (long) newId);
}

-(void)releaseObject:(jobject) obj {
    [[self getId:obj] release];
}

@end