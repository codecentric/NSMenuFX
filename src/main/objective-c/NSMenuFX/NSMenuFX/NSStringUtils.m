//
//  NSStringUtils.m
//  NSMenuFX
//
//  Created by Jan Gassen on 13/03/16.
//  Copyright © 2016 Jan Gassen. All rights reserved.
//

#import "NSStringUtils.h"

@implementation NSStringUtils

+(NSString*) convertJavaStringToCocoa:(JNIEnv*)env javaString:(jstring) javaString {
    const char	*nativeString = (*env)->GetStringUTFChars(env, javaString, JNI_FALSE);
    return [NSString stringWithUTF8String:nativeString];
}

+(jstring) convertNSStringToJava:(JNIEnv*)env nsString:(NSString*) nsString {
    return (*env)->NewStringUTF(env, [nsString UTF8String]);
}

@end