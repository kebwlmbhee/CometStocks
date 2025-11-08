package com.comet.twstockinsight.data.model

import com.google.gson.annotations.SerializedName

// exchangeReport/BWIBBU_ALL
data class StockBwi(
    @SerializedName("Code") val code: String,
    @SerializedName("Name") val name: String,
    @SerializedName("PEratio") val peRatio: String,
    @SerializedName("DividendYield") val dividendYield: String,
    @SerializedName("PBratio") val pbRatio: String) {
}