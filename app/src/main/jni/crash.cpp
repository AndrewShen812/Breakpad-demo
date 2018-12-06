#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_shenyong_breakpaddemo_MainActivity_genCrash(
        JNIEnv *env,
        jobject /* this */) {
    int *a = NULL;
    *a = 1;
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
