package com.plenart.newfilmofil.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.plenart.newfilmofil.data.MovieDao
import com.plenart.newfilmofil.models.MovieDetails

@Database(
    entities = [MovieDetails::class],
    version = 1,
    exportSchema = false
)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun getMovieDao(): MovieDao

    companion object {
        private const val databaseName = "moviesDB"

        @Volatile
        private var INSTANCE: MoviesDatabase? = null

        fun getDatabase(context: Context): MoviesDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): MoviesDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                MoviesDatabase::class.java,
                databaseName
            ).allowMainThreadQueries()
                .build()
        }

    }
}