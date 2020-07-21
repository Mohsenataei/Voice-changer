package com.example.voiceeffects.di

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.voiceeffects.App
import com.example.voiceeffects.utils.SimpleActivityLifecycleCallbacks
import dagger.android.AndroidInjection
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import timber.log.Timber

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


            app.registerActivityLifecycleCallbacks(object : SimpleActivityLifecycleCallbacks(){
                override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
                    super.onActivityCreated(activity, bundle)
                    handleActivity(activity)
                }
            })
        }
        fun handleActivity(activity: Activity) {
            if (activity is HasAndroidInjector) {
                Timber.d("Activity created: %s", activity.javaClass.simpleName)
                AndroidInjection.inject(activity)
            }

            (activity as? FragmentActivity)?.supportFragmentManager?.registerFragmentLifecycleCallbacks(
                object : FragmentManager.FragmentLifecycleCallbacks() {

                    override fun onFragmentAttached(
                        fm: FragmentManager,
                        f: Fragment,
                        context: Context
                    ) {
                        if (f is Injectable) {
                            AndroidSupportInjection.inject(f)
                        }

                        f.childFragmentManager.registerFragmentLifecycleCallbacks(object :
                            FragmentManager.FragmentLifecycleCallbacks() {
                            override fun onFragmentAttached(
                                fm: FragmentManager, f: Fragment, context: Context
                            ) {
                                if (f is Injectable) {
                                    AndroidSupportInjection.inject(f)
                                }
                            }
                        }, true)
                    }
                },
                true
            )
        }

    }
}