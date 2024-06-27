package com.nandaiqbalh.pawartos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.nandaiqbalh.pawartos.presentation.onboarding.OnBoardingScreen
import com.nandaiqbalh.pawartos.ui.theme.PawartosTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		installSplashScreen()
		WindowCompat.setDecorFitsSystemWindows(window, false)
		setContent {
			PawartosTheme {
				Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
					OnBoardingScreen()
				}
			}
		}
	}
}

