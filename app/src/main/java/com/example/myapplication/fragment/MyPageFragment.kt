package com.example.myapplication.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMyPageBinding
import com.example.myapplication.util.AndroidUtils
import com.example.myapplication.view_model.LoginViewModel

// 참고: https://developer.android.com/kotlin/ktx?hl=ko#fragment
class MyPageFragment : Fragment() {


    private lateinit var callback: OnBackPressedCallback

    lateinit var binding: FragmentMyPageBinding
    // private lateinit var loginViewModel: LoginViewModel

    // activity 의 LoginViewModel 공유
    // private val activityViewModel: LoginViewModel by activityViewModels {
    // object : ViewModelProvider.Factory {
    //         override fun <T : ViewModel> create(modelClass: Class<T>): T =
    //             LoginViewModel() as T
    //     }
    // }
    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // DataBinding 설정
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_page, container, false)

        // DataBinding ViewModel 데이터 연결
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            //dataBinding 변수 대입
            this.loginViewModel = loginViewModel
        }

        binding.btnLogin.setOnClickListener {
            val navHostFragment =
                requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            navHostFragment.navController.navigate(R.id.action_myPageFragment_to_loginPageFragment)
        }

        binding.btnBack.setOnClickListener {
            callback.handleOnBackPressed()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.invalidateAll()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d(TAG, "")
                (requireActivity() as MainActivity).onBackPressedCallback.handleOnBackPressed()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    companion object {
        private val TAG: String? = MyPageFragment::class.java.simpleName

    }

}