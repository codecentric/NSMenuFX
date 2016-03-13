//
//  NSStringUtils.h
//  NSMenuFX
//
//  Created by Jan Gassen on 13/03/16.
//  Copyright © 2016 Jan Gassen. All rights reserved.
//

#ifndef NSStringUtils_h
#define NSStringUtils_h

#import <AppKit/AppKit.h>
#import <JavaVM/jni.h>

@interface NSStringUtils : NSObject

+(NSString*) convertJavaStringToCocoa:(JNIEnv*)env javaString:(jstring) javaString;

+(jstring) convertNSStringToJava:(JNIEnv*)env nsString:(NSString*) nsString;

@end

#endif /* NSStringUtils_h */
