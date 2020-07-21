package com.example.voiceeffects.ui.base

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.voiceeffects.di.DaggerViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseDialogFragment : DialogFragment() {
    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)

    }
}