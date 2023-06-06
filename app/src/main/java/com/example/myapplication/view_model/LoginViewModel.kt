package com.example.myapplication.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.repository.LOGIN_NEED
import com.example.myapplication.repository.LoginRepository
import com.example.myapplication.repository.LoginResponse
import com.example.myapplication.repository.Result
import com.example.myapplication.request.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val loginRepository: LoginRepository = LoginRepository()

    // 로그인 상태 변수를 가지고 있음
    val mutableLiveDataLoginResponse = MutableLiveData(
        LoginResponse(LOGIN_NEED, "")
    )

    // 로그인 실행
    fun login(userId: String?, passwd: String?) {
        // viewModel -> repository
        // 로그인 기능은 백그라운드 Thread 에서 동작
        viewModelScope.launch(Dispatchers.IO) {
            val result = try {
                loginRepository.makeLoginRequest(LoginRequest(userId, passwd))
            } catch (e: Exception) {
                Result.Error(Exception("Network request failed"))
            }

            // 데이터 변경
            when (result) {
                is Result.Success<LoginResponse> -> {
                    val data = result.data

                    mutableLiveDataLoginResponse.postValue(data) // mainThread
                }
                else -> mutableLiveDataLoginResponse.postValue(null)  // mainThread

            }
        }
    }
}