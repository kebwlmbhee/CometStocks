package com.comet.twstockinsight

import androidx.lifecycle.ViewModel
import com.comet.twstockinsight.data.repository.StockRepository

class MainViewModel : ViewModel() {
    private val stockRepo = StockRepository()

    suspend fun fetchStockBwi() = stockRepo.getStockBwi()
    suspend fun fetchStockAverage() = stockRepo.getStockAverage()
    suspend fun fetchStockDetail() = stockRepo.getStockDetail()
}