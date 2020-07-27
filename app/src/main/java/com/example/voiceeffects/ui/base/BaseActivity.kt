package com.example.voiceeffects.ui.base

import com.example.voiceeffects.di.DaggerViewModelFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

open class BaseActivity : DaggerAppCompatActivity(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any?>
    override fun androidInjector(): AndroidInjector<Any?>? = androidInjector

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory
}