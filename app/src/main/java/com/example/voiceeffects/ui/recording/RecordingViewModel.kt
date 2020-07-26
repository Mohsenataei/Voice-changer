package com.example.voiceeffects.ui.recording

import android.content.Context
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Environment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.io.*
import javax.inject.Inject

class RecordingViewModel @Inject constructor(
    val context: Context
) : ViewModel() {
    var isRecording = false
    lateinit var audioRecord: AudioRecord
    private var recorder: MediaRecorder? = null
    lateinit var dataOutputStream: DataOutputStream
    private var recordFileName: String =
        "${context.externalCacheDir!!.absolutePath}/audiorecordtest.3gp"


    fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }

        recorder = null
    }

    fun StartRecorder() {
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

