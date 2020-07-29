//
// Created by hami on 7/29/20.
//

#ifndef VOICE_EFFECTS_AASSETAUDIODATASOURCE_H
#define VOICE_EFFECTS_AASSETAUDIODATASOURCE_H

#endif //VOICE_EFFECTS_AASSETAUDIODATASOURCE_H


#include <stdio.h>
#include "../player/AudioSource.h"
#include <android/asset_manager.h>
#include <memory>

class AAssetAudioDataSource : public AudioSource {
public:
    int64_t getSize() const { return mBufferSize; }

    AudioProperties getProperties() const override { return mProperties; }

    const float* getData() const override {mBuffer.get();}

    static AAssetAudioDataSource* newFromCompressedAsset(
            AAssetManager &assetManager,
            const char *filename,
            AudioProperties targetProperties);

private:
    AAssetAudioDataSource(std::unique_ptr<float[]> data, size_t size,
    const AudioProperties properties)
    : mBuffer(std::move(data))
    , mBufferSize(size)
    , mProperties(properties) {
    }

    const std::unique_ptr<float[]> mBuffer;
    const int64_t mBufferSize;
    const AudioProperties mProperties;
};
