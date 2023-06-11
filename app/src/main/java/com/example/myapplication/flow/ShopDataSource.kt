package com.example.myapplication.flow

import com.example.myapplication.data.ShopItem
import com.example.myapplication.request.ShopItemRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlin.time.Duration

// 참고 : https://developer.android.com/kotlin/flow?hl=ko
// 상점 데이터  60 초 마다 갱신
class ShopDataSource {
    companion object {
        private val INTERVAL_REFRESH: Long = 60000
    }


//    fun getShopData(): Flow<List<ShopItem>>{
//         while(true){
//             val showItem = ShopItemRequest.fetchLastItmeLists()
//             emit()
//             delay(INTERVAL_REFRESH)
//         }
//     }
}
//interface NewsApi {
//    suspend fun fetchLatestNews(): List<ArticleHeadline>
//}