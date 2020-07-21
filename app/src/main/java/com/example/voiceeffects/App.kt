package com.example.voiceeffects

import com.example.voiceeffects.di.AppComponent
import com.example.voiceeffects.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class App :DaggerApplication(){

    @Inject
    lateinit var appComponent: AppComponent
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = appComponent
    override fun onCreate() {
        AppInjector.initInjector(this)
        super.onCreate()
    }

}