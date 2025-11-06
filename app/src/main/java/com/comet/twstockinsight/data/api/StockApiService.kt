package com.comet.twstockinsight.data.api

import com.comet.twstockinsight.data.model.StockAverage
import com.comet.twstockinsight.data.model.StockBwi
import com.comet.twstockinsight.data.model.StockDetail
import retrofit2.http.GET

interface StockApiService {
    @GET("/exchangeReport/BWIBBU_ALL")
    fun getStockBwi(): List<StockBwi>

    @GET("/exchangeReport/STOCK_DAY_AVG_ALL")
    fun getStockAverage(): List<StockAverage>

    @GET("/exchangeReport/STOCK_DAY_ALL")
    fun getStockDetail(): List<StockDetail>
}