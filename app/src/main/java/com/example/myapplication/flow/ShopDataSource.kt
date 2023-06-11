package com.example.myapplication.flow

import com.example.myapplication.data.ArticleHeadline
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// 참고 : https://developer.android.com/kotlin/flow?hl=ko
// 상점 데이터  60 초 마다 갱신
class ShopDataSource(val newsApi: NewsApi) {

    companion object {
        private val INTERVAL_REFRESH: Long = 60000
    }

    fun latestNews(): Flow<List<ArticleHeadline>> = flow {
        while (true) {
            val newItem = newsApi.fetchLatestNews()
            emit(newItem)
            delay(INTERVAL_REFRESH)
        }
    }
}

// 네트워크 요청은 suspend 될 수 있다.
interface NewsApi {
    suspend fun fetchLatestNews(): List<ArticleHeadline>
}