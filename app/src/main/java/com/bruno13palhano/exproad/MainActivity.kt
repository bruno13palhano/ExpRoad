package com.bruno13palhano.exproad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bruno13palhano.exproad.screen.NavigationContent
import com.bruno13palhano.exproad.ui.theme.ExpRoadTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController: NavHostController = rememberNavController()

            ExpRoadTheme {
                Surface {
                    NavigationContent(
                        navController = navController,
                        context = this,
                        owner = this
                    )
                }
            }
        }
    }
}