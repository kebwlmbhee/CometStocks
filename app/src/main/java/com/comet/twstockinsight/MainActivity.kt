package com.comet.twstockinsight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.comet.twstockinsight.ui.theme.TWStockInsightTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TWStockInsightTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StockInfoList(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun StockInfoList(name: String, modifier: Modifier = Modifier) {
    val items = (1..20).toList()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        contentPadding = PaddingValues(vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items) { item ->
            StockCard(item)
        }
    }
}

@Composable
fun StockCard(item: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        // shadow
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        StockTitle(item)
        StockPriceGrid(item)
        StockTransaction(item)
    }
}

@Composable
fun StockTransaction(item: Int, modifier: Modifier = Modifier) {
    val transaction = listOf(
        "成交筆數" to "100",
        "成交股價" to "200",
        "成交金額" to "300",
    )

    Row(Modifier.padding(horizontal = 16.dp)) {
        for (column in 0 until 3) {
            Text(
                text = "${transaction[column].first}: ${transaction[column].second}",
                fontSize = 12.sp,
                modifier = Modifier.padding(8.dp)
                    .weight(1f)
            )
        }
    }
}

@Composable
fun StockPriceGrid(item: Int, modifier: Modifier = Modifier) {
    val details = listOf(
        "開盤" to "623.0",
        "收盤" to "625.0",
        "最高" to "630.0",
        "最低" to "620.0",
        "漲跌價" to "18.2",
        "月平均" to "631",
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp)
    ) {
        for(row in 0 until 3) {
            Row {
                for (column in 0 until 2) {
                    val index = row * 2 + column
                    Text(
                        text = "${details[index].first}: ${details[index].second}",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun StockTitle(item: Int, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Code $item",
            fontSize = 12.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 8.dp)
        )
        Text(
            text = "Name $item",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StockInfoListPreview() {
    TWStockInsightTheme {
        StockInfoList("Android")
    }
}