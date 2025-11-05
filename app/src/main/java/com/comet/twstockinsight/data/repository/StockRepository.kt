package com.comet.twstockinsight.data.repository

import com.comet.twstockinsight.data.model.StockAverage
import com.comet.twstockinsight.data.model.StockBwi
import com.comet.twstockinsight.data.model.StockDetail
import com.comet.twstockinsight.data.network.StockApiClient
import retrofit2.Call

class StockRepository {
    suspend fun getStockBwi(): Call<List<StockBwi>> {
        return StockApiClient.api.getStockBwi()
    }

    suspend fun getStockAverage(): Call<List<StockAverage>> {
        return StockApiClient.api.getStockAverage()
    }

    suspend fun getStockDetail(): Call<List<StockDetail>> {
        return StockApiClient.api.getStockDetail()
    }
}