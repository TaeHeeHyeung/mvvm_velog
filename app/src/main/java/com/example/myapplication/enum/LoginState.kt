package com.example.myapplication.enum

// 로그인 상태 변수
enum class LoginState(val value: Int) {
    // 로그인 필요
    LOGIN_NEED(0),

    // 로그인 실패
    LOGIN_FAIL(1),

    // 로그인 성공
    LOGIN_SUCCESS(2),

    // 아이디 존재 X
    LOGIN_CHECK_ID(3),

    // 비밀번호 체크 X
    LOGIN_CHECK_PASS(4)
}