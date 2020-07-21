package com.example.voiceeffects.di

import com.example.voiceeffects.ui.home.HomeFragment
import com.example.voiceeffects.ui.recording.RecordingDialogFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule{
    @PerActivity
    @ContributesAndroidInjector
    internal abstract fun contributeHomeFragment(): HomeFragment


    @PerActivity
    @ContributesAndroidInjector
    internal abstract fun contributeRecordingDialogFragment():RecordingDialogFragment

}