package com.example.myapplication.repository

import android.text.TextUtils
import androidx.annotation.StringRes
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import com.example.myapplication.R
import com.example.myapplication.application.ApplicationTest

// 로그인 상태 변수
// 로그인 필요
const val LOGIN_NEED: Int = 0

// 로그인 실패
const val LOGIN_FAIL: Int = 1

// 로그인 성공
const val LOGIN_SUCCESS: Int = 2

// 아이디 존재 X
const val LOGIN_CHECK_ID: Int = 3

// 비밀번호 체크 X
const val LOGIN_CHECK_PASS: Int = 4

class LoginResponse(
    var checkState: Int?,
    var memberId: String?,
) : BaseObservable() {

    fun toStringCheckState(): String {
        return if (checkState == LOGIN_SUCCESS) {
            "loginSuccess"
        } else {
            "loginFail"
        }
    }

    @Bindable
    fun getWelcomeStr(): String {
        return if (!TextUtils.isEmpty(memberId)) {
            ApplicationTest.instance.resources.getString(
                R.string.success_login,
                memberId
            )
        } else {
            ApplicationTest.getResource().getString(R.string.need_login)
        }
    }

    /*fun setData(result: Result.Success<LoginResponse>?) {
        if (result == null) {
            username = ""
            passwd = ""
        } else {
            username = result.data.username
            passwd = result.data.passwd
        }
    }*/
}