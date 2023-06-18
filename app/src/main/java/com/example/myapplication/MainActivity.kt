package com.example.myapplication

import android.R.attr.button
import android.content.Context
import android.icu.lang.UCharacter.BidiPairedBracketType.OPEN
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.marginLeft
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.databinding.ActivityMainBinding
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
        setContentView(binding.root)

        //초기화
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        // 드로우 뷰 화면 가득 채우기
        binding.navigationView.layoutParams.width = resources.displayMetrics.widthPixels
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
        /*   binding.ivMenu.setOnClickListener {
               val backStackCount = navHostFragment.childFragmentManager.backStackEntryCount
               if (backStackCount == 0) {
                   navController.navigate(R.id.action_blankFragment_to_myPageFragment)
               }
           }*/
//        val navHeader = binding.navigationView.inflateHeaderView(R.layout.nav_header_main);
//        val navHeader = binding.navigationView.getHeaderView(0)
//        val ivMenu = navHeader.findViewById<ImageView>(R.id.iv_menu);



        binding.ivMenu.setOnClickListener {
            if (!binding.drawerLayout.isDrawerOpen(GravityCompat.END)) {
                binding.drawerLayout.openDrawer(GravityCompat.END);
            }
        }

        // 하드웨어 뒤로가기 콜백
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                //메뉴 열려있으면 닫기
                if (binding.drawerLayout.isDrawerOpen(GravityCompat.END)) {
                    binding.drawerLayout.closeDrawer(GravityCompat.END)
                    return
                }

                //이전 프래그먼트 개수
                val backStackCount = navHostFragment.childFragmentManager.backStackEntryCount
                if (backStackCount >= 1) {
                    val result = navController.navigateUp()
                } else {
                    finish()
                }

            }
        }
        // 하드웨어 뒤로가기 콜백 등록
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        // 로그인 뷰 모델 데이터 관찰
        loginViewModel.mutableLiveDataLoginResponse.observe(this, Observer {

        })

    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
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

