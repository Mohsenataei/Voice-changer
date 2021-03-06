# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)
set(OBOE_DIR ../../../../oboe)

add_subdirectory(${OBOE_DIR} ./oboe)

include_directories(${OBOE_DIR}/include)
include_directories(src/main/cpp/)
# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
        native-lib

        # Sets the library as a shared library.
        SHARED
        utils/logging.h
        audio/AAssetAudioDataSource.cpp
        audio/AAssetAudioDataSource.h
        AudioManagerJNI.cpp
        constants.h
        player/SampleBuffer.h

        #wav liberaries
        wav/AudioEncoding.cpp
        wav/WavChunkHeader.cpp
        wav/WavFmtChunkHeader.cpp
        wav/WavRIFFChunkHeader.cpp
        wav/WavStreamReader.cpp

        #stream
        stream/FileInputStream.cpp
        stream/InputStream.cpp
        stream/MemInputStream.cpp

        #player
        player/SampleSource.cpp
        player/SampleBuffer.cpp
        player/OneShotSampleSource.cpp
        player/SimpleMultiPlayer.cpp

        # Provides a relative path to your source file(s).
        native-lib.cpp)


# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
        native-lib

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})