#include <jni.h>
#include <string>
#include "src/client/linux/handler/minidump_descriptor.h"
#include "src/client/linux/handler/exception_handler.h"

bool DumpCallback(const google_breakpad::MinidumpDescriptor &descriptor,
                  void *context,
                  bool succeeded) {
    return succeeded;
}

extern "C" JNIEXPORT void JNICALL
Java_com_shenyong_breakpaddemo_MainActivity_breakpadInit(
        JNIEnv *env,
        jobject /* this */, jstring dump_path) {
    const char *path = env->GetStringUTFChars(dump_path, 0);

    google_breakpad::MinidumpDescriptor descriptor(path);
    static google_breakpad::ExceptionHandler eh(descriptor, NULL, DumpCallback, NULL, true, -1);

    env->ReleaseStringUTFChars(dump_path, path);
}

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env;
    if (vm->GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK) {
        return JNI_ERR;
    }
    return JNI_VERSION_1_6;
}