package com.mvclopes.wtest.domain.usecase

import com.mvclopes.wtest.domain.model.PostalCode
import com.mvclopes.wtest.domain.repository.PostalCodeRepository
import kotlinx.coroutines.flow.Flow

class GetPostalCodesUseCase(private val repository: PostalCodeRepository) {

    operator fun invoke(): Flow<List<PostalCode>> {
        return repository.getAll()
    }
}