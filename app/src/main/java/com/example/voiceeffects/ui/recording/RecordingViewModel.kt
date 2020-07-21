package com.example.voiceeffects.ui.recording

import android.content.Context
import android.media.AudioRecord
import android.os.Environment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedOutputStream
import java.io.DataOutputStream
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class RecordingViewModel @Inject constructor(
    val context: Context
) : ViewModel() {
    var isRecording = false

    lateinit var audioRecord: AudioRecord
    lateinit var dataOutputStream: DataOutputStream
    fun startRecordingVoice() {
        val myFile = File(Environment.getExternalStorageDirectory().absolutePath, "effects1.pcm")
        myFile.createNewFile()
        val outPutStream = FileOutputStream(myFile)
        val bufferedOutPutStream = BufferedOutputStream(outPutStream)
        dataOutputStream = DataOutputStream(bufferedOutPutStream)
        val minBufferSize = AudioRecord.getMinBufferSize(11025, 2, 2)
        val audioData = ShortArray(minBufferSize)
        audioRecord = AudioRecord(1, 11025, 2, 2, minBufferSize)

        audioRecord.startRecording()

        while (isRecording) {
            val numberOfShorts = audioRecord.read(audioData, 0, minBufferSize)

            for (i in 0 until numberOfShorts) {
                dataOutputStream.writeShort(audioData[i].toInt())
            }
        }

        if (!isRecording){
            stopRecording()
        }

    }


    fun trueRecordFlag() {
        isRecording = true
    }

    fun falseRecordFlag() {
        CoroutineScope(Dispatchers.Main).launch {
            isRecording = true
        }
    }

    fun stopRecording() {
        audioRecord.stop()
        dataOutputStream.close()
    }
}

