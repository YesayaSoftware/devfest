package software.yesaya.devfest.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import software.yesaya.devfest.data.db.dao.PostDao
import software.yesaya.devfest.data.db.modal.PostEntry

@Database(
    entities = [
        PostEntry::class
    ],
    version = 1
)

abstract class DevFestDatabase : RoomDatabase() {
    abstract val postDao: PostDao

    companion object {
        @Volatile
        private var INSTANCE: DevFestDatabase? = null

        fun getInstance(context: Context): DevFestDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DevFestDatabase::class.java,
                        "dev_fest_database"
                    ).fallbackToDestructiveMigration().build()
                }

                return instance
            }
        }
    }
}