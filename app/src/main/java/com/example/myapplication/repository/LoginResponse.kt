package com.example.myapplication.repository

// 로그인 상태 변수
const val LOGIN_NEED: Int = 0
const val LOGIN_FAIL: Int = 1
const val LOGIN_SUCCESS: Int = 2
const val LOGIN_CHECK_ID: Int = 3
const val LOGIN_CHECK_PASS: Int = 4

class LoginResponse(
    var loginState: Int = LOGIN_NEED,
    var memberId: String?,
) {


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