package com.example.voiceeffects.ui

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.voiceeffects.R
import com.example.voiceeffects.ui.base.BaseActivity
import com.example.voiceeffects.ui.home.HomeViewModel
import com.example.voiceeffects.utils.Constants

class HomeActivity : BaseActivity() {

    private val viewmodel by lazy { ViewModelProviders.of(this, viewModelFactory).get(
        HomeViewModel::class.java) }
    private lateinit var navController: NavController
    private var fileAccessGranted = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        navController = findNavController(R.id.nav_host_fragment)
        viewmodel.isRecording.observe(this, Observer {

        })
    }

    private fun requestFilePermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                Constants.readAndWritePermissions,Constants.STORAGE_PERMISSION_REQUEST_CODE
            )
        } else {
            fileAccessGranted = true
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        fileAccessGranted = grantResults.takeIf { it.isNotEmpty() }
            ?.map { it == PackageManager.PERMISSION_GRANTED }
            ?.firstOrNull { it.not() }
            ?.let { false }
            ?: true
    }
}