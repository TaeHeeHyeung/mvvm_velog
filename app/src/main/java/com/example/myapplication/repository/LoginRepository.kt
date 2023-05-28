package com.example.myapplication.repository

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: java.lang.Exception) : Result<Nothing>()
}

class LoginRepository {
    constructor()
    /*
    constructor(responseParser: LoginResponseParser)
     Function that makes the network request, blocking the current thread
    suspend fun makeLoginRequest(jsonBody: String): Result<LoginResponse> {
        return withContext(Dispatchers.IO) {
            val url = URL(loginUrl)
            (url.openConnection() as? HttpURLConnection)?.run {
                requestMethod = "POST"
                setRequestProperty("Content-Type", "application/json; utf-8")
                setRequestProperty("Accept", "application/json")
                doOutput = true
                outputStream.write(jsonBody.toByteArray())
                Result.Success(responseParser.parse(inputStream))
            }
            Result.Error(Exception("Cannot open HttpURLConnection"))
        }
    }
     companion object {
        private const val loginUrl = "https://example.com/login"
    }
    */
    //suspend: 백그라운드 쓰레드 처리 가정
    suspend fun makeLoginRequest(jsonBody: String): Result<LoginResponse> {
        return withContext(Dispatchers.IO) {
            try {
                Thread.sleep(2000)
                val loginResponse = Gson().fromJson(jsonBody, LoginResponse::class.java)
                Result.Success(loginResponse)
            } catch (e: java.lang.Exception) {
                Result.Error(e);
            }
        }
    }


}