package com.example.voiceeffects.di

import android.content.Context
import com.example.voiceeffects.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module

class AppModule(val app: App) {

    @Singleton
    @Provides
    internal fun provideApplication(): App = app

    @Singleton
    @Provides
    internal fun provideContext(): Context = app.applicationContext
}