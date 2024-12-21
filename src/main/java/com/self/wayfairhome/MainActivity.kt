package com.self.wayfairhome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import com.self.wayfairhome.ui.theme.WayfairHomeTheme
import com.self.wayfairhome.views.HomeProducts
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WayfairHomeTheme {
                HomeProducts(modifier = Modifier)
            }
        }
    }
}