package jr.brian.basiccarmaintenance.model.local

//import androidx.room.Entity

//@Entity(tableName = "vehicle_items")
data class VehicleItem(
    val title: String,
    val usagePercentage: String = "0f",
    val lightColorStr: String,
    val mediumColorStr: String,
    val darkColorStr: String,
)