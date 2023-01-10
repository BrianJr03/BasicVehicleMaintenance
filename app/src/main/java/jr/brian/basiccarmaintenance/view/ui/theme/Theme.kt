package jr.brian.basiccarmaintenance.view.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun BasicCarMaintenanceTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}