//
//  NSObjectUtils.h
//  NSMenuFX
//
//  Created by Jan Gassen on 13/03/16.
//  Copyright © 2016 Jan Gassen. All rights reserved.
//

#ifndef NSObjectUtils_h
#define NSObjectUtils_h

#import <AppKit/AppKit.h>
#import <JavaVM/jni.h>

@interface NSObjectUtils : NSObject

@property (nonatomic, readonly) const char* className;

-(id)init:(const char*)name;

-(jobject)createJObject:(JNIEnv*)env obj:(id)obj;

-(id)getId:(JNIEnv*)env obj:(jobject)obj;

-(void)updateId:(JNIEnv*)env obj:(jobject)obj newId:(id)newId;

-(void)releaseObject:(JNIEnv*)env obj:(jobject) obj;

@end

#endif /* NSObjectUtils_h */
