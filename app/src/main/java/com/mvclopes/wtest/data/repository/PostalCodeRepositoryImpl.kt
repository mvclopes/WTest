package com.mvclopes.wtest.data.repository

import com.mvclopes.wtest.data.datasource.PostalCodeDatasource
import com.mvclopes.wtest.data.local.entity.PostalCodeEntity
import com.mvclopes.wtest.domain.mapper.toDomain
import com.mvclopes.wtest.domain.model.PostalCode
import com.mvclopes.wtest.domain.repository.PostalCodeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PostalCodeRepositoryImpl (
    private val datasource: PostalCodeDatasource
) : PostalCodeRepository {
    override fun getAll(): Flow<List<PostalCode>> {
        return datasource.getAll().map { it.toDomain() }
    }

    override fun insertPostalCode(postalCode: PostalCodeEntity): Flow<Unit> {
        return datasource.insertPostalCode(postalCode)
    }

    override fun insertAll(postalCodeList: List<PostalCodeEntity>): Flow<Unit> {
        return datasource.insertAll(postalCodeList)
    }

    override fun isDatabaseEmpty(): Flow<Boolean> {
        return datasource.isDatabaseEmpty()
    }

    override fun searchByPostalCodeNumber(postalCodeNumber: String): Flow<List<PostalCode>> {
        return datasource.searchByPostalCodeNumber(postalCodeNumber).map { it.toDomain() }
    }

    override fun searchByPostalDesignation(postalDesignation: String): Flow<List<PostalCode>> {
        return datasource.searchByPostalDesignation(postalDesignation).map { it.toDomain() }
    }

    override fun searchByDesignationAndCodeNumber(
        postalCodeNumber: String,
        postalDesignation: String
    ): Flow<List<PostalCode>> {
        return datasource
            .searchByDesignationAndCodeNumber(postalCodeNumber, postalDesignation)
            .map { it.toDomain() }
    }

}