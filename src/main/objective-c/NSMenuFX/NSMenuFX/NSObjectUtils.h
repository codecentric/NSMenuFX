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

@property (nonatomic, readonly) JNIEnv* env;
@property (nonatomic, readonly) const char* className;

-(id)init:(JNIEnv*)jniEnv name:(const char*)name;

-(jobject)createJObject:(id) item;

-(id)getId:(jobject)obj;

-(void)updateId:(jobject)obj newId:(id)newId;

-(void)releaseObject:(jobject) obj;

@end

#endif /* NSObjectUtils_h */
