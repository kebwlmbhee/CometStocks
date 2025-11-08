package com.comet.twstockinsight.data.model

import com.google.gson.annotations.SerializedName


// exchangeReport/STOCK_DAY_AVG_ALL
data class StockAverage(
    @SerializedName("Code") val code: String,
    @SerializedName("Name") val name: String,
    @SerializedName("ClosingPrice") val closingPrice: String,
    @SerializedName("MonthlyAveragePrice") val monthlyAveragePrice: String)