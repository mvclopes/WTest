package com.mvclopes.wtest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.mvclopes.wtest.data.local.entity.PostalCodeEntity

@Dao
interface PostalCodeDao {

    @Query("SELECT * FROM tb_cod_postal LIMIT 1000")
    suspend fun getAll(): List<PostalCodeEntity>

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(postalCodeList: List<PostalCodeEntity>)

    @Insert(onConflict = REPLACE)
    suspend fun insertPostalCode(postalCode: PostalCodeEntity)

    @Query("SELECT * FROM tb_cod_postal LIMIT 1")
    suspend fun isDatabaseEmpty(): PostalCodeEntity?

    @Query("SELECT * FROM tb_cod_postal WHERE num_cod_postal LIKE :postalCodeNumber")
    suspend fun searchByPostalCodeNumber(postalCodeNumber: String): List<PostalCodeEntity>

    @Query("SELECT * FROM tb_cod_postal WHERE desig_postal LIKE :postalDesignation")
    suspend fun searchByPostalDesignation(postalDesignation: String): List<PostalCodeEntity>

    @Query("SELECT * FROM tb_cod_postal WHERE num_cod_postal LIKE :postalCodeNumber AND desig_postal LIKE :postalDesignation")
    suspend fun searchByDesignationAndCodeNumber(
        postalCodeNumber: String,
        postalDesignation: String
    ): List<PostalCodeEntity>
}