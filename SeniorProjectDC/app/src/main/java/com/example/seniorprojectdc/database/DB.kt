import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.seniorprojectdc.database.DAO
import com.example.seniorprojectdc.service_classes.Insect

@Database(entities = [Insect::class], version = 3, exportSchema = false)
abstract class InsectDatabase : RoomDatabase() {
    abstract fun insectDao(): DAO

    companion object {
        @Volatile
        private var INSTANCE: InsectDatabase? = null

        fun getDatabase(context: Context): InsectDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    InsectDatabase::class.java,
                    "insect_database"
                ).fallbackToDestructiveMigration().build().also { INSTANCE = it }
            }
        }
    }
}
