package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        UserProfileEntity::class,
        LessonProgressEntity::class,
        UnlockedBadgeEntity::class,
        UnlockedItemEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class QuestLearnDatabase : RoomDatabase() {

    abstract fun learningDao(): LearningDao

    companion object {
        @Volatile
        private var INSTANCE: QuestLearnDatabase? = null

        fun getDatabase(context: Context): QuestLearnDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuestLearnDatabase::class.java,
                    "questlearn_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
