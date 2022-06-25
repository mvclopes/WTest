package com.mvclopes.wtest.data.datasource.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mvclopes.wtest.data.datasource.local.dao.PostalCodeDao
import com.mvclopes.wtest.data.datasource.local.entity.PostalCodeEntity

@Database(entities = [PostalCodeEntity::class], version = 1)
abstract class PostalCodeDatabase : RoomDatabase() {
    abstract fun postalCodeDAO(): PostalCodeDao
}

private lateinit var INSTANCE: PostalCodeDatabase

    fun getDataBase(context: Context): PostalCodeDatabase {
        synchronized(PostalCodeDatabase::class.java) {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    PostalCodeDatabase::class.java,
                    "postal_code_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
        return INSTANCE
    }
