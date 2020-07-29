//
// Created by hami on 7/29/20.
//

#ifndef VOICE_EFFECTS_LOGGING_H
#define VOICE_EFFECTS_LOGGING_H

#endif //VOICE_EFFECTS_LOGGING_H




#define APP_NAME "VoiceEffects"
#define LOGD(...) ((void)__android_log_print(ANDROID_LOG_DEBUG, APP_NAME, __VA_ARGS__))
#define LOGI(...) ((void)__android_log_print(ANDROID_LOG_INFO, APP_NAME, __VA_ARGS__))
#define LOGW(...) ((void)__android_log_print(ANDROID_LOG_WARN, APP_NAME, __VA_ARGS__))
#define LOGE(...) ((void)__android_log_print(ANDROID_LOG_ERROR, APP_NAME, __VA_ARGS__))