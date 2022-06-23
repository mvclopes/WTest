package com.mvclopes.wtest.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.mvclopes.wtest.data.datasource.local.entity.PostalCodeEntity

@Dao
interface PostalCodeDao {

    @Query("SELECT * FROM PostalCodeEntity LIMIT 100")
    suspend fun getAll(): List<PostalCodeEntity>

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(postalCodeList: List<PostalCodeEntity>)

    @Insert(onConflict = REPLACE)
    suspend fun insertPostalCode(postalCode: PostalCodeEntity)

    @Query("SELECT * FROM PostalCodeEntity LIMIT 1")
    suspend fun isDatabaseEmpty(): PostalCodeEntity?
}