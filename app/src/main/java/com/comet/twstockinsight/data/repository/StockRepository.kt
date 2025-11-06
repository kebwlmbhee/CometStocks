package com.comet.twstockinsight.data.repository

import com.comet.twstockinsight.data.model.StockAverage
import com.comet.twstockinsight.data.model.StockBwi
import com.comet.twstockinsight.data.model.StockDetail
import com.comet.twstockinsight.data.network.StockApiClient

class StockRepository {
    suspend fun getStockBwi(): List<StockBwi> {
        return StockApiClient.api.getStockBwi()
    }

    suspend fun getStockAverage(): List<StockAverage> {
        return StockApiClient.api.getStockAverage()
    }

    suspend fun getStockDetail(): List<StockDetail> {
        return StockApiClient.api.getStockDetail()
    }
}