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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentLoginAfterBinding
import com.example.myapplication.view_model.LoginViewModel

//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

class LoginFragment : Fragment() {
//    private var param1: String? = null
//    private var param2: String? = null

    private lateinit var callback: OnBackPressedCallback
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
        val binding: FragmentLoginAfterBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login_after, container, false)
        binding.apply {
            lifecycleOwner = this@LoginFragment
            this.loginViewModel = loginViewModel;
        }

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        activityViewModel.loginLiveData.observe(viewLifecycleOwner) {
            binding.tvLoginState.text = getString(R.string.success_login, it.id)
        }

        binding.btnBack.setOnClickListener {
            callback.handleOnBackPressed();
        }

        return binding.root
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

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }

    companion object {
        private val TAG: String = LoginFragment::class.java.simpleName

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