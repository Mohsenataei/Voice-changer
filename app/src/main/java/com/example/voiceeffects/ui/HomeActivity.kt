package com.example.voiceeffects.ui

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.voiceeffects.R
import com.example.voiceeffects.di.DaggerViewModelFactory
import com.example.voiceeffects.ui.home.HomeViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any?>
    override fun androidInjector(): AndroidInjector<Any?>? = androidInjector
    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory
    private val viewmodel by lazy { ViewModelProviders.of(this, viewModelFactory).get(
        HomeViewModel::class.java) }
    lateinit var navController: NavController
    private final val REQUEST_STORAGE_PERMISSIONS = 786
    private var fileAccessGranted = false



    private val permissions = arrayOf(
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.RECORD_AUDIO,
        android.Manifest.permission.MODIFY_AUDIO_SETTINGS
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestFilePermissions()
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
                permissions, REQUEST_STORAGE_PERMISSIONS
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