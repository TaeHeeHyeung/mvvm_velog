//package com.example.myapplication.repository
//
//import java.io.InputStream
//import java.io.InputStreamReader
//
//
//class LoginResponseParser {
//    fun parse(inputStream: InputStream): LoginResponse {
//        val inputStreamReader = InputStreamReader(inputStream, "UTF-8")
//        val result: StringBuilder = StringBuilder()
//
//        var data: Int = inputStreamReader.read()
//        while (data != -1) {
//            result.append(data.toChar())
//            data = inputStreamReader.read()
//        }
//        val jsonStr = result.toString()
//        val resultTest = LoginResponse("","");
//        return resultTest
//    }
//
//}
