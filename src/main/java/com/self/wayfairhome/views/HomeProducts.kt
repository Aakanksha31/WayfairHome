package com.self.wayfairhome.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.self.wayfairhome.R
import com.self.wayfairhome.model.OneOffEvent
import com.self.wayfairhome.utils.Loader
import com.self.wayfairhome.utils.showToast
import com.self.wayfairhome.viewmodel.HomeProduct
import com.self.wayfairhome.viewmodel.HomeProductsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeProducts(modifier: Modifier = Modifier) {
    val viewModel: HomeProductsViewModel = viewModel()

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.oneOffEvent, lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.oneOffEvent.collect {
                when (it) {
                    is OneOffEvent.ShowError -> {
                        context.showToast(it.message)
                    }
                }
            }
        }
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background)),
    ) { padding ->
        if (uiState.isLoading) {
            Loader(withBackground = false)
        } else {
            LazyColumn(modifier = Modifier.padding(padding)) {
                itemsIndexed(uiState.items,
                    key = { _, item ->
                        item.name
                    }) { index, item ->
                    HomeProductCard(product = item)
                }
            }
        }
    }
}

@Composable
fun HomeProductCard(product: HomeProduct, modifier: Modifier = Modifier) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = painterResource(id = R.drawable.ic_placeholder),
                contentDescription = null
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = product.name, color = Color.White, fontSize = 24.sp)
                Rating(rating = product.rating)
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product.tagline, color = Color.LightGray, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product.date, color = Color.LightGray, fontSize = 12.sp)
        }
    }

}

@Composable
fun Rating(rating: Double) {
    Row {
        Text(text = rating.toString(), color = Color.White, fontSize = 24.sp)
        Image(
            painter = painterResource(id = R.drawable.ic_rating),
            contentDescription = "rating"
        )
    }
}

@Preview
@Composable
fun ShowProduct() {
    HomeProductCard(
        HomeProduct(
            name = "Blue Table",
            tagline = "Interesting choice for interesting people. This table is just the right color for a modern dining room looking to make a splash with even the most discerning guests!",
            rating = 4.2,
            date = "1-14-2018"
        )
    )
}