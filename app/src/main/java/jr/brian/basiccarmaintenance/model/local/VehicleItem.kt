package jr.brian.basiccarmaintenance.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehicle_items")
data class VehicleItem(
    @PrimaryKey val associatedVehicle: String,
    val title: String,
    val usagePercentage: String = "0f",
    val lightColorStr: String,
    val mediumColorStr: String,
    val darkColorStr: String,
)