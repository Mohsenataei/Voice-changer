package com.example.voiceeffects.ui.base

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.voiceeffects.di.DaggerViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

open class BaseDialogFragment : DialogFragment() {
    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }
}