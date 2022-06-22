package com.mvclopes.wtest.data.repository

import com.mvclopes.wtest.data.datasource.PostalCodeDatasource
import com.mvclopes.wtest.data.datasource.local.entity.PostalCodeEntity
import com.mvclopes.wtest.domain.repository.PostalCodeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class PostalCodeRepositoryImpl (
    private val datasource: PostalCodeDatasource
) : PostalCodeRepository {
    override fun getAll(): Flow<List<PostalCodeEntity>> {
        return datasource.getAll()
    }

    override fun insertPostalCode(postalCode: PostalCodeEntity): Flow<Unit> {
        return datasource.insertPostalCode(postalCode)
    }

    override fun insertAll(postalCodeList: List<PostalCodeEntity>): Flow<Unit> {
        return datasource.insertAll(postalCodeList)
    }

}