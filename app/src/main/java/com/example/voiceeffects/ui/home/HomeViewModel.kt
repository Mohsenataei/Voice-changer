package com.example.voiceeffects.ui.home

import android.content.Context
import android.media.AudioRecord
import android.media.AudioTrack
import android.os.Environment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.voiceeffects.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.*
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val context: Context
) : ViewModel() {
    val isRecording = MutableLiveData<Boolean>().apply { value = false }
    val file: File = File(Environment.getExternalStorageDirectory(), "effects1.pcm")
    val arrayString = MutableLiveData<MutableList<String>>().apply {
        value = mutableListOf()
    }
    lateinit var audioTrack: AudioTrack


    init {
        initFilterArray()
    }

    fun playRecord(filter: String) {

        CoroutineScope(Dispatchers.IO).launch {
            var frequency = 0
            val shortSizeInBytes = Short.SIZE_BITS / Byte.SIZE_BITS
            val bufferSizeInBytes = (file.length().div(shortSizeInBytes)).toInt()
            val audioData = ShortArray(bufferSizeInBytes)
            val inputStream = FileInputStream(file)
            val bufferedInputStream = BufferedInputStream(inputStream)
            val dataInputStream = DataInputStream(bufferedInputStream)

            var index = -1
            while (dataInputStream.available() > 0) {
                audioData[++index] = dataInputStream.readShort()
            }

            dataInputStream.close()

            when (filter) {
                getString(R.string.ghost) -> frequency = 5000
                getString(R.string.Slow_Motion) -> frequency = 6050
                getString(R.string.robot) -> frequency = 8500
                getString(R.string.normal) -> frequency = 11025
                getString(R.string.chipmunk) -> frequency = 16000
                getString(R.string.funny) -> frequency = 22050
                getString(R.string.bee) -> frequency = 41000
                getString(R.string.elephant) -> frequency = 30000
            }
            audioTrack = AudioTrack(3, frequency, 2, 2, bufferSizeInBytes, 1)
            audioTrack.play()
            audioTrack.write(audioData, 0, bufferSizeInBytes)
        }
    }

    private fun initFilterArray() {
        arrayString.value?.apply {
            add(getString(R.string.ghost))
            add(getString(R.string.Slow_Motion))
            add(getString(R.string.robot))
            add(getString(R.string.normal))
            add(getString(R.string.chipmunk))
            add(getString(R.string.funny))
            add(getString(R.string.bee))
            add(getString(R.string.elephant))
        }
    }

    fun stopRecording() {
        isRecording.value = false
    }

    private fun getString(resId: Int) = context.getString(resId)

}