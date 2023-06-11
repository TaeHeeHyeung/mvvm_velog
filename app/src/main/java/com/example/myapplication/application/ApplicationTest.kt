package com.example.myapplication.application

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.example.myapplication.util.StringResourcesProvider

class ApplicationTest : Application() {

    companion object {


        lateinit var instance: ApplicationTest
            private set

        fun getResource(): Resources {
            return instance.resources
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}