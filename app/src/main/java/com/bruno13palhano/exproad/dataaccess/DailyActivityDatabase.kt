package com.bruno13palhano.exproad.dataaccess

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bruno13palhano.exproad.dao.DailyActivityDao
import com.bruno13palhano.exproad.model.DailyActivity

@Database(entities = [DailyActivity::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DailyActivityDatabase : RoomDatabase(){
    abstract val dailyActivityDao: DailyActivityDao

    companion object {
        @Volatile
        private var INSTANCE: DailyActivityDatabase? = null

        fun getInstance(context: Context) : DailyActivityDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        DailyActivityDatabase::class.java,
                        "daily_activity_db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}