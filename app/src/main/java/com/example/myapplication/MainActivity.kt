package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.viewmodel.LoginViewModel


// 설계
// 1. 입력: 아이디, 비밀번호
// 2. 로그인 기능: 네트워크 연결처리 => suspend Thread
// 3.
class MainActivity : AppCompatActivity() {
    private val TAG: String? = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)
        // 회전 테스트 시 로그 가 어떤식으로 변하는가 확인하기 위한 코드 추가 예정
        if (savedInstanceState == null) {
            Log.d(TAG, "onCreate savedInstanceState == null")
        } else {
            Log.d(TAG, "onCreate savedInstanceState != null")
        }

        // 뷰 모델 선언
        val loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        // 뷰 모델 옵저버 선언
        loginViewModel.mutableLoginResponse.observe(this, Observer {
            if (it.username == "hth0893" && it.passwd == "1q2w3e4r") {
                Log.d(TAG, "successLogin")
                binding.tvIsLogin.text = "로그인 success"
            } else {
                binding.tvIsLogin.text = "로그인 fail"
            }
        })

        // val id = "hth0893"
        // val pass = "1q2w3e4r"

        // 로그인 기능 실행
        binding.btnLogin.setOnClickListener { view ->
            loginViewModel.login(binding.et1.text?.toString(), binding.et2.text?.toString())
        }
    }
}

