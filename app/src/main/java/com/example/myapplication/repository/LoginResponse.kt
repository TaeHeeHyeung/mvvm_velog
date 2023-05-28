package com.example.myapplication.repository

class LoginResponse(
    var username: String?,
    var passwd: String?
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