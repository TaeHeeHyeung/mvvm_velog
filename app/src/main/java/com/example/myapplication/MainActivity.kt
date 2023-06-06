package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.repository.LOGIN_SUCCESS
import com.example.myapplication.util.AndroidUtils
import com.example.myapplication.view_model.LoginViewModel


class MainActivity : AppCompatActivity() {
    private val TAG: String? = MainActivity::class.java.simpleName
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, AndroidUtils.TEST_LOG + "onCreate")
        
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val root = binding.root
        setContentView(root)

        //
        if (savedInstanceState == null) {
            Log.d(TAG, AndroidUtils.TEST_LOG + "onCreate savedInstanceState null")
        }
        // 실행
        // 1. 회전 시 onDestroy 호출 후
        // 2. 시스템이 앱을 종료 후 실행
        else {
            Log.d(TAG, AndroidUtils.TEST_LOG + "onCreate savedInstanceState exists")
        }

        // 뷰 모델 선언
        val loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.apply {
            lifecycleOwner= this@MainActivity
            this.loginViewModel = loginViewModel;
        }
        binding.loginBeforeInclude.apply {
            lifecycleOwner= this@MainActivity
            this.loginViewModel = loginViewModel;
        }
        binding.loginAfterInclude.apply {
            lifecycleOwner= this@MainActivity
            this.loginViewModel = loginViewModel;
        }
        // 로그인 기능 실행
        binding.loginBeforeInclude.btnLogin.setOnClickListener {
            loginViewModel.login(binding.loginBeforeInclude.etLoginId.text?.toString(), binding.loginBeforeInclude.etLoginPass.text?.toString())
        }
    }

    override fun onStart() {
        Log.d(TAG, AndroidUtils.TEST_LOG + "onStart")

        super.onStart()
    }

    override fun onPause() {
        Log.d(TAG, AndroidUtils.TEST_LOG + "onPause")

        super.onPause()
    }

    override fun onResume() {
        Log.d(TAG, AndroidUtils.TEST_LOG + "onResume")

        super.onResume()
    }

    override fun onDestroy() {
        Log.d(TAG, AndroidUtils.TEST_LOG + "onDestroy")

        super.onDestroy()
    }
}

