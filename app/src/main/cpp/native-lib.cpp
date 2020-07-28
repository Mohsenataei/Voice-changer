//
// Created by hami on 7/27/20.
//
#include <jni.h>
#include <string>

using namespace std;
extern "C" JNIEXPORT jstring JNICALL
Java_com_example_voiceeffects_ui_HomeActivity_stringFromJNI(
        JNIEnv *env,
        jobject) {
    std::string hello = "Hello from c++";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_voiceeffects_ui_HomeActivity_testFun(JNIEnv *env, jobject) {
        std::string hello = "Hello from c++";
        return env->NewStringUTF(hello.c_str());
}