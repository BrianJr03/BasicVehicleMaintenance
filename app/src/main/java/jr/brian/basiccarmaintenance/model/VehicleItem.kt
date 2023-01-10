package jr.brian.basiccarmaintenance.model

//import androidx.room.Entity

//@Entity(tableName = "vehicle_items")
data class VehicleItem(
    val title: String,
    val usagePercentage: Float = 0f,
    val lightColorStr: String,
    val mediumColorStr: String,
    val darkColorStr: String,
)