package com.example.voiceeffects.ui

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.voiceeffects.R
import com.example.voiceeffects.ui.base.BaseActivity

class HomeActivity : BaseActivity() {


    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        navController = findNavController(R.id.nav_host_fragment)
    }
}