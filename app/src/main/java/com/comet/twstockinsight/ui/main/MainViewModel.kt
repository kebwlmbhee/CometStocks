package com.comet.twstockinsight.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comet.twstockinsight.data.model.StockAverage
import com.comet.twstockinsight.data.model.StockBwi
import com.comet.twstockinsight.data.model.StockDetail
import com.comet.twstockinsight.data.model.StockWithCode
import com.comet.twstockinsight.data.repository.StockRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class SortOrder { ORIGINAL, ASC, DESC }
class MainViewModel : ViewModel() {
    companion object {
        private val TAG = MainViewModel::class.qualifiedName
    }

    private val _currentSortOrder = MutableStateFlow(SortOrder.ORIGINAL)
    val currentSortOrder = _currentSortOrder.asStateFlow()
    private val stockRepo = StockRepository()
    private val _stockDetailList = MutableStateFlow<List<StockDetail>?>(null)
    val stockDetailList = _stockDetailList.asStateFlow()
    private val _stockAverageList = MutableStateFlow<List<StockAverage>?>(null)
    val stockAverageList = _stockAverageList.asStateFlow()
    private val _stockBwiList = MutableStateFlow<List<StockBwi>?>(null)
    val stockBwiList = _stockBwiList.asStateFlow()

    // Only for preview
    fun setFakeData(): MainViewModel {
        return MainViewModel().apply {
            _stockDetailList.value = listOf(
                StockDetail(
                    code = "2330",
                    name = "台積電",
                    tradeVolume = "1200000",
                    tradeValue = "600000000",
                    openingPrice = "600",
                    highestPrice = "610",
                    lowestPrice = "595",
                    closingPrice = "605",
                    change = "+5",
                    transaction = "3000"
                ),
                StockDetail(
                    code = "2317",
                    name = "鴻海",
                    tradeVolume = "800000",
                    tradeValue = "320000000",
                    openingPrice = "400",
                    highestPrice = "405",
                    lowestPrice = "395",
                    closingPrice = "402",
                    change = "+2",
                    transaction = "2500"
                )
            )

            _stockAverageList.value = listOf(
                StockAverage(
                    code = "2330",
                    name = "台積電",
                    closingPrice = "605",
                    monthlyAveragePrice = "598"
                ),
                StockAverage(
                    code = "2317",
                    name = "鴻海",
                    closingPrice = "402",
                    monthlyAveragePrice = "395"
                )
            )

            _stockBwiList.value = listOf(
                StockBwi(
                    code = "2330",
                    name = "台積電",
                    peRatio = "25.3",
                    dividendYield = "2.5",
                    pbRatio = "5.2"
                ),
                StockBwi(
                    code = "2317",
                    name = "鴻海",
                    peRatio = "15.8",
                    dividendYield = "3.2",
                    pbRatio = "2.1"
                )
            )
        }
    }

    // async must be called from a CoroutineScope
    suspend fun fetchAllConcurrently() {
        while (true) {
            fetchOnce()
            delay(5000)
        }
    }

    private suspend fun fetchOnce() = coroutineScope {
        val bwi = async { stockRepo.getStockBwi() }
        val average = async { stockRepo.getStockAverage() }
        val detail = async { stockRepo.getStockDetail() }

        _stockBwiList.value = sortList(bwi.await())
        _stockAverageList.value = sortList(average.await())
        _stockDetailList.value = sortList(detail.await())
    }

    fun sortStockListByCode(sortOrder: SortOrder) {
        _currentSortOrder.value = if (currentSortOrder.value == sortOrder) {
            SortOrder.ORIGINAL
        } else {
            sortOrder
        }
        viewModelScope.launch {
            fetchOnce()
        }
    }

    private fun <T: StockWithCode> sortList(list: List<T>?): List<T>? {
        if (list == null) return null
        return when (currentSortOrder.value) {
            SortOrder.ORIGINAL -> list
            SortOrder.ASC -> list.sortedBy { it.code }
            SortOrder.DESC -> list.sortedByDescending { it.code }
        }
    }
}