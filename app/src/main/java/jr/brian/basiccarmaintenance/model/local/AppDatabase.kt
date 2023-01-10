package jr.brian.basiccarmaintenance.model.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [VehicleItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): VehicleItemsDao
}