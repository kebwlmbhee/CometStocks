package com.comet.twstockinsight.data.api

import com.comet.twstockinsight.data.model.StockAverage
import com.comet.twstockinsight.data.model.StockBwi
import com.comet.twstockinsight.data.model.StockDetail
import retrofit2.Call
import retrofit2.http.GET

interface StockApiService {
    @GET("/exchangeReport/BWIBBU_ALL")
    fun getStockBwi(): Call<List<StockBwi>>

    @GET("/exchangeReport/STOCK_DAY_AVG_ALL")
    fun getStockAverage(): Call<List<StockAverage>>

    @GET("/exchangeReport/STOCK_DAY_ALL")
    fun getStockDetail(): Call<List<StockDetail>>
}