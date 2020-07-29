//
// Created by hami on 7/27/20.
//
#include <jni.h>
#include <string>

#include "stream/MemInputStream.h"
#include "wav/WavStreamReader.h"

#include "player/OneShotSampleSource.h"
#include "player/SimpleMultiPlayer.h"

static const char *TAG = "VoiceEffectsJNI";


// JNI functions are "C" calling convention
#ifdef __cplusplus
extern "C" {
#endif


using namespace iolib;
using namespace parselib;

static SimpleMultiPlayer sDTPlayer;


JNIEXPORT jstring JNICALL
Java_com_example_voiceeffects_ui_HomeActivity_stringFromJNI(
        JNIEnv *env,
        jobject) {
    std::string hello = "Hello from c++";
    return env->NewStringUTF(hello.c_str());
}


JNIEXPORT jstring JNICALL
Java_com_example_voiceeffects_ui_HomeActivity_testFun(JNIEnv *env, jobject) {
    std::string hello = "Hello from c++";
    return env->NewStringUTF(hello.c_str());
}


JNIEXPORT void JNICALL
Java_com_example_voiceeffects_ui_home_HomeFragment_setupAudioStreamNative(JNIEnv *env, jobject thiz,
                                                                          jint num_channels,
                                                                          jint sample_rate) {
// TODO figure out how to include and enable log in native then uncomment next line
//  __android_log_print(ANDROID_LOG_INFO, TAG, "%s", "init()");

// we know in this case that the sample buffers are all 1-channel, 41K
    sDTPlayer.setupAudioStream(num_channels, sample_rate);
}

JNIEXPORT void JNICALL
Java_com_example_voiceeffects_ui_home_HomeFragment_tearDownAudioStreamNative(JNIEnv *env,
                                                                             jobject thiz) {
    sDTPlayer.teardownAudioStream();
}
JNIEXPORT void JNICALL
Java_com_example_voiceeffects_ui_home_HomeFragment_loadWavAudioNative(JNIEnv *env, jobject thiz,
                                                                      jbyteArray wav_bytes,
                                                                      jint index, jfloat pan) {

    int len = env->GetArrayLength(wav_bytes);

    unsigned char *buf = new unsigned char[len];
    env->GetByteArrayRegion(wav_bytes, 0, len, reinterpret_cast<jbyte *>(buf));
    MemInputStream stream(buf, len);
    WavStreamReader reader(&stream);
    reader.parse();

    SampleBuffer *sampleBuffer = new SampleBuffer();
    sampleBuffer->loadSampleData(&reader);

    OneShotSampleSource *source = new OneShotSampleSource(sampleBuffer, pan);

    sDTPlayer.addSampleSource(source, sampleBuffer);
    delete[] buf;


}
JNIEXPORT void JNICALL
Java_com_example_voiceeffects_ui_home_HomeFragment_unloadWavAssetsNative(JNIEnv *env,
                                                                         jobject thiz) {
    sDTPlayer.unloadSampleData();
}

JNIEXPORT void JNICALL
Java_com_example_voiceeffects_ui_home_HomeFragment_trigger(JNIEnv *env, jobject thiz,
                                                           jint drum_index) {
    sDTPlayer.triggerDown(drum_index);
}

JNIEXPORT void JNICALL
Java_com_example_voiceeffects_ui_home_HomeFragment_setPan(JNIEnv *env, jobject thiz, jint index,
                                                          jfloat pan) {
    sDTPlayer.setGain(index, pan);
}

JNIEXPORT jfloat JNICALL
Java_com_example_voiceeffects_ui_home_HomeFragment_getPan(JNIEnv *env, jobject thiz, jint index) {
    sDTPlayer.getPan(index);
}

JNIEXPORT void JNICALL
Java_com_example_voiceeffects_ui_home_HomeFragment_setGain(JNIEnv *env, jobject thiz, jint index,
                                                           jfloat gain) {
    sDTPlayer.setGain(index, gain);
}

JNIEXPORT jfloat JNICALL
Java_com_example_voiceeffects_ui_home_HomeFragment_getGain(JNIEnv *env, jobject thiz, jint index) {
    sDTPlayer.getGain(index);
}


JNIEXPORT jboolean JNICALL
Java_com_example_voiceeffects_ui_home_HomeFragment_getOutputReset(JNIEnv *env, jobject thiz) {
    return sDTPlayer.getOutputReset();
}

JNIEXPORT void JNICALL
Java_com_example_voiceeffects_ui_home_HomeFragment_clearOutputReset(JNIEnv *env, jobject thiz) {
    sDTPlayer.clearOutputReset();
}
#ifdef __cplusplus
}
#endif