package com.mvclopes.wtest.data.datasource

import com.mvclopes.wtest.data.datasource.local.entity.PostalCodeEntity
import kotlinx.coroutines.flow.Flow

interface PostalCodeDatasource {
    fun getAll(): Flow<List<PostalCodeEntity>>
    fun insertPostalCode(postalCode: PostalCodeEntity): Flow<Unit>
    fun insertAll(postalCodeList: List<PostalCodeEntity>): Flow<Unit>
    fun isDatabaseEmpty(): Flow<Boolean>
}