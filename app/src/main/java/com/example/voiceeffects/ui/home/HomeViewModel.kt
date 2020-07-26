package com.example.voiceeffects.ui.home

import android.content.Context
import android.media.AudioTrack
import android.media.MediaPlayer
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.voiceeffects.R
import com.example.voiceeffects.event.RequestPermissionEvent
import com.example.voiceeffects.extensions.checkSelfPermissions
import com.example.voiceeffects.utils.Constants
import com.example.voiceeffects.utils.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.*
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val context: Context
) : ViewModel() {

    lateinit var player: MediaPlayer
    private var recordFileName: String =
        "${context.externalCacheDir!!.absolutePath}/audiorecordtest.3gp"
    val isRecording = MutableLiveData<Boolean>().apply { value = false }
    val file: File = File(context.externalCacheDir!!.absolutePath, "audiorecordtest.3gp")
    val filters = MutableLiveData<MutableList<String>>().apply {
        value = mutableListOf()
    }
    private var audioTrack = AudioTrack(3, 11025, 2, 2, 2048, 1)

    val isPermissionGranted = MutableLiveData<Event<RequestPermissionEvent>>()


    init {
        initFilterArray()
        checkPermissions()
    }

    fun playRecord(filter: String) {
        CoroutineScope(Dispatchers.IO).launch {
            playRecordedAudio(filter)
        }
    }

    private fun initFilterArray() {
        filters.value?.apply {
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
    private fun getString(resId: Int) = context.getString(resId)

    private fun playRecordedAudio(filter: String){
        if (file.exists()) {
            audioTrack.stop()
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


    fun play(){
        player = MediaPlayer().apply {
            try {
                setDataSource(recordFileName)
                prepare()
                start()
            }catch (e: IOException){
                e.printStackTrace()
                Log.e("mediaPlayer","prepate failed. ${e.message}")
            }
        }
    }

    private fun checkPermissions(){
        isPermissionGranted.value = Event(RequestPermissionEvent(context.checkSelfPermissions(Constants.READ_AND_WRITE_PERMISSIONS)))
    }





}