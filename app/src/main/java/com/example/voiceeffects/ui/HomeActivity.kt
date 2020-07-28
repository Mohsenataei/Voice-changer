package com.example.voiceeffects.ui

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.voiceeffects.R
import com.example.voiceeffects.extensions.toast
import com.example.voiceeffects.ui.base.BaseActivity

class HomeActivity : BaseActivity() {

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }

    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        val t = testFun()
        toast("this is first method calling from jni $t")
    }

    private fun initView() {
        navController = findNavController(R.id.nav_host_fragment)
    }

    external fun stringFromJNI():String

    external fun testFun():String
}