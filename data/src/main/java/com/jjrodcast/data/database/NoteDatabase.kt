package com.jjrodcast.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jjrodcast.data.BuildConfig
import com.jjrodcast.data.database.dao.NoteDao
import com.jjrodcast.data.entities.NoteEntity


@Database(entities = [NoteEntity::class], version = BuildConfig.DB_VERSION, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        // Singleton creation
        @Volatile
        private var instance: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).apply { instance = this }
            }
        }

        private fun buildDatabase(context: Context): NoteDatabase {
            return Room.databaseBuilder(
                context,
                NoteDatabase::class.java,
                BuildConfig.DATABASE_NAME
            ).build()
        }
    }
}