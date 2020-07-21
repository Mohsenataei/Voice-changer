package com.example.voiceeffects.di

import androidx.lifecycle.ViewModel
import com.example.voiceeffects.ui.home.HomeViewModel
import com.example.voiceeffects.ui.recording.RecordingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module

abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RecordingViewModel::class)
    abstract fun bindRecordingViewModel(viewModel: RecordingViewModel): ViewModel

}