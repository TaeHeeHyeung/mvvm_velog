package com.example.myapplication.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMyPageBinding
import com.example.myapplication.util.AndroidUtils
import com.example.myapplication.view_model.LoginViewModel

// 참고: https://developer.android.com/kotlin/ktx?hl=ko#fragment
class MyPageFragment : Fragment() {


    private val TAG: String? = MyPageFragment::class.java.simpleName

    // private lateinit var loginViewModel: LoginViewModel
    // activity 의 LoginViewModel 공유
    private val activityViewModel: LoginViewModel by activityViewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T =
                LoginViewModel() as T
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // DataBinding 설정
//        val binding: FragmentLoginBeforeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_before, container, false)
        val binding: FragmentMyPageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_page, container, false)

        // DataBinding ViewModel 데이터 연결
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            //dataBinding 변수 대입
            this.loginViewModel = loginViewModel
        }

        activityViewModel.mutableLiveDataLoginResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, AndroidUtils.TEST_LOG + "")
        }
        // 로그인 실행
//        binding.btnLogin.setOnClickListener {
//            activityViewModel.login(binding.etLoginId.text?.toString(), binding.etLoginPass.text?.toString())
//        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}