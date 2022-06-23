package com.mvclopes.wtest.domain.repository

import com.mvclopes.wtest.data.datasource.local.entity.PostalCodeEntity
import com.mvclopes.wtest.domain.model.PostalCode
import kotlinx.coroutines.flow.Flow

interface PostalCodeRepository {
    fun getAll(): Flow<List<PostalCode>>
    fun insertPostalCode(postalCode: PostalCodeEntity): Flow<Unit>
    fun insertAll(postalCodeList: List<PostalCodeEntity>): Flow<Unit>
    fun isDatabaseEmpty(): Flow<Boolean>
}