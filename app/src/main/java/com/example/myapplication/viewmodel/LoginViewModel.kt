package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.repository.LoginRepository
import com.example.myapplication.repository.LoginResponse
import com.example.myapplication.repository.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
        
    private val loginRepository: LoginRepository = LoginRepository()
    // 로그인 상태 변수를 가지고 있음
    val mutableLoginResponse = MutableLiveData(LoginResponse("", ""))

    fun login(userName: String?, passwd: String?) {
        // viewModel -> repository
        // 로그인 기능은 백그라운드 Thread 에서 동작
        viewModelScope.launch(Dispatchers.IO) {
            val jsonBody = "{username: \"$userName\", passwd: \"$passwd\"}"
            val result = try {
                loginRepository.makeLoginRequest(jsonBody)
            } catch (e: Exception) {
                Result.Error(Exception("Network request failed"))
            }

            // 데이터 변경
            when (result) {
                is Result.Success<LoginResponse> -> {
                    val data = mutableLoginResponse.value
                    data?.passwd = passwd
                    data?.username = userName
                    mutableLoginResponse.postValue(data) // mainThread
                }
                else -> mutableLoginResponse.postValue(null)  // mainThread

            }
        }
    }
}