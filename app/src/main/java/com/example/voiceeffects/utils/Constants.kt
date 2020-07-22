package com.example.voiceeffects.utils

object Constants {
    const val STORAGE_PERMISSION_REQUEST_CODE = 786

    const val WRITE_EXTERNAL_STORAGE = android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    const val READ_EXTERNAL_STORAGE = android.Manifest.permission.READ_EXTERNAL_STORAGE
    const val RECORD_AUDIO = android.Manifest.permission.RECORD_AUDIO
    const val MODIFY_AUDIO_SETTINGS = android.Manifest.permission.MODIFY_AUDIO_SETTINGS

    val readAndWritePermissions = arrayOf(
        WRITE_EXTERNAL_STORAGE,
        READ_EXTERNAL_STORAGE,
        RECORD_AUDIO,
        MODIFY_AUDIO_SETTINGS
    )



}