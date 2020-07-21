package com.example.voiceeffects.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.voiceeffects.di.DaggerViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment : Fragment(){
    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }
}