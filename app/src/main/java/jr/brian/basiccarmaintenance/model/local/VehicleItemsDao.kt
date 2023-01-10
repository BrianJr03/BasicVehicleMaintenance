package jr.brian.basiccarmaintenance.model.local

import androidx.room.*

@Dao
interface VehicleItemsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavColor(vehicleItem: VehicleItem)

    @Query("SELECT * FROM vehicle_items")
    fun getVehicleItems(): List<VehicleItem>

//    @Delete
//    fun removeFavColor(color: VehicleItem)

//    @Query("DELETE FROM vehicle_items")
//    fun removeAllFavColors()
}