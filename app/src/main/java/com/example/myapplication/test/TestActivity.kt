package com.example.myapplication.test

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.util.AndroidUtils

class TestActivity : AppCompatActivity() {
    private val TAG: String? = TestActivity::class.java.simpleName

    override fun onStart() {
        Log.d(TAG, AndroidUtils.TEST_LOG + "onStart")

        super.onStart()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, AndroidUtils.TEST_LOG + "onCreate")

        setContentView(R.layout.activity_test)
        // 에디트 텍스트 회전시 값 사라짐 테스트
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        Log.d(TAG, AndroidUtils.TEST_LOG + "onConfigurationChanged")
        super.onConfigurationChanged(newConfig)
    }

    override fun onDestroy() {
        Log.d(TAG, AndroidUtils.TEST_LOG + "onDestroy")
        super.onDestroy()
    }
}