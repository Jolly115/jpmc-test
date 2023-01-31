package com.app.a20230125_jollygupta_nycschools.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NycClass : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}