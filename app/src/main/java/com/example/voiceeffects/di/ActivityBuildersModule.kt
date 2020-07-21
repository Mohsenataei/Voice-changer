package com.example.voiceeffects.di

import com.example.voiceeffects.ui.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @PerActivity
    @ContributesAndroidInjector
    internal abstract fun contributeHomeActivity(): HomeActivity

}