package com.example.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentLoginAfterBinding
import com.example.myapplication.view_model.LoginViewModel

//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

class LoginAfterFragment : Fragment() {
//    private var param1: String? = null
//    private var param2: String? = null

    private lateinit var viewModel: LoginViewModel

    // private lateinit var loginViewModel: LoginViewModel
    // activity 의 LoginViewModel 공유
    private val activityViewModel: LoginViewModel by activityViewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T =
                LoginViewModel() as T
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentLoginAfterBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_after, container, false)
        binding.apply {
            lifecycleOwner = this@LoginAfterFragment
            this.loginViewModel = loginViewModel;
        }

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        activityViewModel.mutableLiveDataLoginResponse.observe(viewLifecycleOwner) {
            binding.tvLoginState.text = getString(R.string.success_login, it.memberId.toString())
        }
        return binding.root
    }

    companion object {
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            MemeberFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
    }
}