package com.example.voiceeffects.ui.recording

import android.content.Context
import android.media.MediaRecorder
import androidx.lifecycle.ViewModel
import java.io.*
import javax.inject.Inject

class RecordingViewModel @Inject constructor(
    val context: Context
) : ViewModel() {
    private var recorder: MediaRecorder? = null
    private var recordFileName: String =
        "${context.externalCacheDir!!.absolutePath}/audiorecordtest.3gp"


    fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
    }

    fun startRecorder() {
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(recordFileName)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            try {
                prepare()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            start()
        }

    }
}

