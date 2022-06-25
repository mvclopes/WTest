package com.mvclopes.wtest.data.datasource

import com.mvclopes.wtest.data.datasource.local.dao.PostalCodeDao
import com.mvclopes.wtest.data.datasource.local.entity.PostalCodeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostalCodeDatasourceImpl (
    private val postalCodeDao: PostalCodeDao
) : PostalCodeDatasource {
    override fun getAll(): Flow<List<PostalCodeEntity>> {
        return flow { emit(postalCodeDao.getAll()) }
    }

    override fun insertPostalCode(postalCode: PostalCodeEntity): Flow<Unit> {
        return flow { emit(postalCodeDao.insertPostalCode(postalCode)) }
    }

    override fun insertAll(postalCodeList: List<PostalCodeEntity>): Flow<Unit> {
        return flow { emit(postalCodeDao.insertAll(postalCodeList)) }
    }

    override fun isDatabaseEmpty(): Flow<Boolean> {
        return flow { emit(postalCodeDao.isDatabaseEmpty() == null) }
    }

    override fun searchByPostalCodeNumber(postalCodeNumber: String): Flow<List<PostalCodeEntity>> {
        return flow { emit(postalCodeDao.searchByPostalCodeNumber(postalCodeNumber)) }
    }

    override fun searchByPostalDesignation(postalDesignation: String): Flow<List<PostalCodeEntity>> {
        return flow { emit(postalCodeDao.searchByPostalDesignation(postalDesignation)) }
    }

    override fun searchByDesignationAndCodeNumber(
        postalCodeNumber: String,
        postalDesignation: String
    ): Flow<List<PostalCodeEntity>> {
        return flow { emit(postalCodeDao.searchByDesignationAndCodeNumber(postalCodeNumber, postalDesignation)) }
    }

}