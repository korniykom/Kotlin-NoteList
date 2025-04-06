package com.korniykom.notelist

import android.app.Application
import com.korniykom.notelist.di.AppComponent
import com.korniykom.notelist.di.AppModule
import com.korniykom.notelist.di.DaggerAppComponent

class NoteApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}