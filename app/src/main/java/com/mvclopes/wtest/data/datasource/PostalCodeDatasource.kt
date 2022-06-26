package com.mvclopes.wtest.data.datasource

import com.mvclopes.wtest.data.local.entity.PostalCodeEntity
import kotlinx.coroutines.flow.Flow

interface PostalCodeDatasource {
    fun getAll(): Flow<List<PostalCodeEntity>>
    fun insertPostalCode(postalCode: PostalCodeEntity): Flow<Unit>
    fun insertAll(postalCodeList: List<PostalCodeEntity>): Flow<Unit>
    fun isDatabaseEmpty(): Flow<Boolean>
    fun searchByPostalCodeNumber(postalCodeNumber: String): Flow<List<PostalCodeEntity>>
    fun searchByPostalDesignation(postalDesignation: String): Flow<List<PostalCodeEntity>>
    fun searchByDesignationAndCodeNumber(
        postalCodeNumber: String,
        postalDesignation: String
    ): Flow<List<PostalCodeEntity>>
}