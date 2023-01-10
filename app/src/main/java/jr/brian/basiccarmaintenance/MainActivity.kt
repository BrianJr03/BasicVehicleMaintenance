package jr.brian.basiccarmaintenance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import jr.brian.basiccarmaintenance.view.ui.pages.HomePage
import jr.brian.basiccarmaintenance.view.ui.theme.BasicCarMaintenanceTheme

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