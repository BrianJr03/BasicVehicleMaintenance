package jr.brian.basiccarmaintenance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import jr.brian.basiccarmaintenance.ui.pages.HomePage
import jr.brian.basiccarmaintenance.ui.theme.BasicCarMaintenanceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicCarMaintenanceTheme {
                HomePage()
            }
        }
    }
}