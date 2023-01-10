package jr.brian.basiccarmaintenance.view.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import jr.brian.basiccarmaintenance.model.MyDataStore
import jr.brian.basiccarmaintenance.model.VehicleItem
import jr.brian.basiccarmaintenance.util.*
import jr.brian.basiccarmaintenance.view.ui.theme.*

@Composable
fun HomePage() {
    val context = LocalContext.current
    val dataStore = MyDataStore(context)
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        val vehicles = listOf("Jeep Cherokee '14", "Vestar Mini")
        val avgMileage =
            dataStore.getDailyMileageAverage.collectAsState(0f)
        Column {
            AppHeader()
            CurrentVehicle(
                defaultVehicle = vehicles[0],
                dataStore = dataStore
            )
            VehicleItemsSection(
                dataStore = dataStore,
                vehicleItems = listOf(
                    VehicleItem(
                        title = "Motor Oil Usage",
                        usagePercentage = if (avgMileage.value == null) {
                            0f
                            // TODO - replace 24 with number of days passed since last save
                        } else (avgMileage.value!! * 24) / 3000,
                        lightColorStr = BlueViolet1.value.toString(),
                        mediumColorStr = BlueViolet2.value.toString(),
                        darkColorStr = BlueViolet3.value.toString()
                    ),
                    VehicleItem(
                        title = "Coolant Usage",
                        usagePercentage = if (avgMileage.value == null) {
                            0f
                        } else (avgMileage.value!! * 24) / 30000,
                        lightColorStr = LightGreen1.value.toString(),
                        mediumColorStr = LightGreen2.value.toString(),
                        darkColorStr = LightGreen3.value.toString()
                    ),
                    VehicleItem(
                        title = "Brake Fluid Usage",
                        usagePercentage = if (avgMileage.value == null) {
                            0f
                        } else (avgMileage.value!! * 24) / 30000,
                        lightColorStr = OrangeYellow1.value.toString(),
                        mediumColorStr = OrangeYellow2.value.toString(),
                        darkColorStr = OrangeYellow3.value.toString()
                    ),
                    VehicleItem(
                        title = "Power Steering Usage",
                        usagePercentage = if (avgMileage.value == null) {
                            0f
                        } else (avgMileage.value!! * 24) / 60000,
                        lightColorStr = Beige1.value.toString(),
                        mediumColorStr = Beige2.value.toString(),
                        darkColorStr = Beige3.value.toString()
                    ),
                    VehicleItem(
                        title = "Brake Pads Usage",
                        usagePercentage = if (avgMileage.value == null) {
                            0f
                        } else (avgMileage.value!! * 24) / 10000,
                        lightColorStr = OrangeYellow1.value.toString(),
                        mediumColorStr = OrangeYellow2.value.toString(),
                        darkColorStr = OrangeYellow3.value.toString()
                    )
                )
            )
        }
    }
}

@Composable
private fun AppHeader() {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Basic Vehicle Maintenance",
                style = MaterialTheme.typography.h2
            )
        }
    }
}
