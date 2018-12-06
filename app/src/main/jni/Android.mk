LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := Crash-lib
LOCAL_SRC_FILES := crash.cpp

include $(BUILD_SHARED_LIBRARY)
include $(LOCAL_PATH)/breakpad/Android.mk