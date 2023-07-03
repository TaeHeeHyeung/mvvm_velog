package com.example.myapplication.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Login
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel() {

    //    public var loginLiveData: MutableLiveData<Login> = MutableLiveData(Login("", ""))
    var loginLiveData: MutableLiveData<Login> = MutableLiveData(Login())


    // 로그인 상태 변수를 가지고 있음
//    val mutableLiveDataLoginResponse = MutableLiveData(
//        LoginResponse(, "")
//    )

    // 로그인 실행
    fun login(userId: String?, passwd: String?) {

        // viewModel -> repository
        // 로그인 기능은 백그라운드 Thread 에서 동작 (
        viewModelScope.launch(Dispatchers.Default) {
            val result = try {
//                makeLoginRequest(userId, passwd)
            } catch (e: Exception) {
//                Result.Error(Exception("Network request failed"))
            }
            if (userId != null) {
                loginLiveData.value?.id = userId
            }
            if (passwd != null) {
                loginLiveData.value?.passWd = passwd
            }
            loginLiveData.postValue(loginLiveData.value)
            // 데이터 변경
//            when (result) {
//                is Result.Success<LoginResponse> -> {
//                    val data = result.data
//
//                    mutableLiveDataLoginResponse.postValue(data) // mainThread
//                }
//                else -> mutableLiveDataLoginResponse.postValue(null)  // mainThread
//
//            }
        }
    }


}