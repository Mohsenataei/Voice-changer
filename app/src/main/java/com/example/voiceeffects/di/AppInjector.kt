package com.example.voiceeffects.di


import com.example.voiceeffects.App

class AppInjector {
    companion object {
        fun initInjector(app: App) {
            DaggerAppComponent.builder()
                .application(app)
                .appModule(AppModule(app))
                .build()
                .apply {
                    app.appComponent = this
                }.inject(app)

        }

    }
}