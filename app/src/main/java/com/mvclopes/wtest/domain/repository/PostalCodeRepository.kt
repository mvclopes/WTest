package com.mvclopes.wtest.domain.repository

import com.mvclopes.wtest.data.datasource.local.entity.PostalCodeEntity
import kotlinx.coroutines.flow.Flow

interface PostalCodeRepository {
    fun getAll(): Flow<List<PostalCodeEntity>>
    fun insertPostalCode(postalCode: PostalCodeEntity): Flow<Unit>
    fun insertAll(postalCodeList: List<PostalCodeEntity>): Flow<Unit>
}