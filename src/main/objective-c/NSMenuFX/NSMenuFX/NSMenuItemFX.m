//
//  NSMenuItemFX.m
//  NSMenuFX
//
//  Created by Jan Gassen on 12/03/16.
//  Copyright © 2016 Jan Gassen. All rights reserved.
//
#import "NSMenuItemFX.h"
#import "NSObject+JObject.h"
#import <Foundation/Foundation.h>

@implementation NSMenuItemFX

+(void)handleClickAction:(id)sender {

}

@end

NSString *convertJavaStringToCocoa(JNIEnv *env, jstring javaString) {
    const char	*nativeString = (*env)->GetStringUTFChars(env, javaString, JNI_FALSE);
    return [NSString stringWithUTF8String:nativeString];
}

jobject createJObject(JNIEnv *env, const char* name, id item) {
    jclass klass = (*env)->FindClass(env, name);
    jmethodID constructor = (*env)->GetMethodID(env, klass, "<init>", "(J)V");
    return (*env)->NewObject(env, klass, constructor, (long) item);
}

id getId(JNIEnv* env, jobject obj) {
    jclass klass = (*env)->FindClass(env, "de/codecentric/centerdevice/cocoa/NSMenuItem");
    jmethodID getId = (*env)->GetMethodID(env, klass, "getId", "()J");
    return (id) (*env)->CallLongMethod(env, obj, getId);
}

#ifdef __cplusplus
extern "C" {
#endif
    jobject Java_de_codecentric_centerdevice_cocoa_NSMenuItem_alloc(JNIEnv *env, jobject thisObj) {
        NSMenuItem* item = [NSMenuItem alloc];
        [item setJObject:thisObj];
        return createJObject(env, "de/codecentric/centerdevice/cocoa/NSMenuItem", item);
    }
    
    JNIEXPORT jobject JNICALL Java_de_codecentric_centerdevice_cocoa_NSMenuItem_init(JNIEnv *env, jobject thisObj, jstring title, jstring keyEquivalent) {
        NSMenuItem* val = getId(env, thisObj);
        
        NSString* nsTitle = convertJavaStringToCocoa(env, title);
        NSString* nsKeyEquivalent = convertJavaStringToCocoa(env, title);
        
        [val initWithTitle:nsTitle action:@selector(handleClickAction:) keyEquivalent:nsKeyEquivalent];
        
        [nsTitle release];
        [nsKeyEquivalent release];
        
        return thisObj;
    }
#ifdef __cplusplus
}
#endif