package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.repository.LOGIN_SUCCESS
import com.example.myapplication.util.AndroidUtils
import com.example.myapplication.view_model.LoginViewModel

// 공부: Fragment Activity ViewModel 공유 구현

// Material 아이콘 : https://material.io/icons/
// Nounproject : https://thenounproject.com/

// todo
//  구현 중: naviHostFragment
class MainActivity : AppCompatActivity() {
    private val TAG: String? = MainActivity::class.java.simpleName
    private lateinit var binding: ActivityMainBinding
    lateinit var loginViewModel: LoginViewModel

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
        // onDestroy 호출 후 재실행 시
        else {
            Log.d(TAG, AndroidUtils.TEST_LOG + "onCreate savedInstanceState exists")
        }

        // 뷰 모델 선언
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        // 뷰 모델, 데이터 바인딩 연결
        binding.apply {
            lifecycleOwner = this@MainActivity
            this.loginViewModel = loginViewModel;
        }


        //메뉴 선택
        binding.ivMenu.setOnClickListener {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            navController.navigate(R.id.action_blankFragment_to_myPageFragment)

        }

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
                val navController = navHostFragment.navController
                val result = navController.navigateUp()
                if (!result) {
                    finish()
                }

            }
        }
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        // 로그인 뷰 모델 데이터 관찰
        loginViewModel.mutableLiveDataLoginResponse.observe(this, Observer {
            Log.d(TAG, AndroidUtils.TEST_LOG + it.toStringCheckState())

            // 로그인 성공 => 프레그먼트 변경 (로그인 완료)
            if (it.checkState == LOGIN_SUCCESS) {
//                supportFragmentManager.commit {
//                    supportFragmentManager.findFragmentById(R.id.fragment_container_view)
//                    replace<LoginAfterFragment>(R.id.fragment_container_view)
//                }
            }
            // 로그인 실패 => 프래그먼트 변경 (로그인 실패)
            else {
//                supportFragmentManager.commit {
//                    replace<LoginBeforeFragment>(R.id.fragment_container_view)
//                }
            }
            //
        })

    }

    fun handleOnBackPressed(): Boolean {
        //Do your job here
        //use next line if you just need navigate up
        //NavHostFragment.findNavController(this).navigateUp();
        //Log.e(getClass().getSimpleName(), "handleOnBackPressed");
        return true
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

