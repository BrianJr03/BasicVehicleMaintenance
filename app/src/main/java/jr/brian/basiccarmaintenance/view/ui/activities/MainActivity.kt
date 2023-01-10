package jr.brian.basiccarmaintenance.view.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import jr.brian.basiccarmaintenance.model.local.VehicleItemsDao
import jr.brian.basiccarmaintenance.view.ui.pages.HomePage
import jr.brian.basiccarmaintenance.view.ui.theme.BasicCarMaintenanceTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    var dao: VehicleItemsDao? = null
        @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicCarMaintenanceTheme {
                dao?.let { HomePage(dao = it) }
//                dao?.let {
//                    HomePage(it)
//                }
            }
        }
    }
}