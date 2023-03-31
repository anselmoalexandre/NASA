package mz.co.bilheteira.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import mz.co.bilheteira.compose.ui.NasaApp
import mz.co.bilheteira.resources.ui.theme.NASATheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NASATheme {
                NasaApp(navController = navController)
            }
        }
    }
}