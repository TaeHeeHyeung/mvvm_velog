package com.example.myapplication.util

import android.app.Application
import android.content.Context
import androidx.annotation.StringRes

//@Singleton
class StringResourcesProvider(val context: Context) {
    fun getString(@StringRes stringResId: Int): String {
        return context.getString(stringResId)
    }
}