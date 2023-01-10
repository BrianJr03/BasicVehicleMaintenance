package jr.brian.basiccarmaintenance.model.local

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
//import androidx.room.Entity

//@Entity(tableName = "vehicle_items")
data class VehicleItem(
    val title: String,
    val usagePercentage: Float = 0f,
    val circularProgressBar: @Composable () -> Unit,
    val lightColor: Color,
    val mediumColor: Color,
    val darkColor: Color,
)