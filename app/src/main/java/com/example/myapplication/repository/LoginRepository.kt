package com.example.myapplication.repository

import com.example.myapplication.request.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.*

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
    suspend fun makeLoginRequest(loginRequest: LoginRequest): Result<LoginResponse> {
        return withContext(Dispatchers.IO) {
            try {
                // 1~ 1.5
                val random = Random()
                val randomValue: Double = 1 + random.nextDouble() * 0.5
                Thread.sleep((1000 * randomValue).toLong())

                val responseData: LoginResponse
                val loginId = "hth0893"
                val passWd = "1q2w3e4r"
                responseData =
                    if (loginRequest.userId.equals(loginId) && loginRequest.passWd.equals(passWd)) {
                        LoginResponse(
                            LOGIN_SUCCESS,
                            "memberId",
                        )
                    } else {
                        LoginResponse(
                            LOGIN_FAIL,
                            "",
                        )
                    }
                Result.Success(responseData)
            } catch (e: Exception) {
                Result.Error(e);
            }
        }
    }


}