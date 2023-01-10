package jr.brian.basiccarmaintenance

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

data class VehicleItem(
    val title: String,
    val circularProgressBar: @Composable () -> Unit,
    val lightColor: Color,
    val mediumColor: Color,
    val darkColor: Color,
)