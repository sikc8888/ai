package com.scalp.analyzer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.scalp.analyzer.security.ActivationManager
import com.scalp.analyzer.ui.activation.ActivationScreen
import com.scalp.analyzer.ui.customer.CustomerListScreen
import com.scalp.analyzer.ui.detection.DetectionScreen
import com.scalp.analyzer.ui.report.ComparisonReportScreen
import com.scalp.analyzer.ui.settings.SettingsScreen
import com.scalp.analyzer.ui.theme.ScalpAnalyzerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScalpAnalyzerTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    val isActivated = ActivationManager.isActivated()

                    NavHost(
                        navController = navController,
                        startDestination = if (isActivated) "customer_list" else "activation"
                    ) {
                        composable("activation") {
                            ActivationScreen(
                                onActivated = {
                                    navController.navigate("customer_list") {
                                        popUpTo("activation") { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable("customer_list") {
                            CustomerListScreen(
                                onCustomerSelected = { customerId ->
                                    navController.navigate("detection/$customerId")
                                },
                                onSettingsClick = {
                                    navController.navigate("settings")
                                }
                            )
                        }
                        composable("detection/{customerId}") { backStackEntry ->
                            val customerId = backStackEntry.arguments?.getString("customerId") ?: ""
                            DetectionScreen(
                                customerId = customerId,
                                onReportClick = { recordId ->
                                    navController.navigate("report/$recordId")
                                },
                                onBack = { navController.popBackStack() }
                            )
                        }
                        composable("report/{recordId}") { backStackEntry ->
                            val recordId = backStackEntry.arguments?.getString("recordId") ?: ""
                            ComparisonReportScreen(
                                recordId = recordId,
                                onBack = { navController.popBackStack() }
                            )
                        }
                        composable("settings") {
                            SettingsScreen(
                                onBack = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}
