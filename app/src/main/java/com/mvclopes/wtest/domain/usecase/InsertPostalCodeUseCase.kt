package com.mvclopes.wtest.domain.usecase

import com.mvclopes.wtest.data.datasource.local.entity.PostalCodeEntity
import com.mvclopes.wtest.domain.repository.PostalCodeRepository

class InsertPostalCodeUseCase(private val repository: PostalCodeRepository) {

    operator fun invoke(postalCodeList: List<PostalCodeEntity>) {
        repository.insertAll(postalCodeList)
    }
}