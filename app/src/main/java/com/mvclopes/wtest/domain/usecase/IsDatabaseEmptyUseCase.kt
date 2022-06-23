package com.mvclopes.wtest.domain.usecase

import com.mvclopes.wtest.domain.repository.PostalCodeRepository
import kotlinx.coroutines.flow.Flow

class IsDatabaseEmptyUseCase(private val repository: PostalCodeRepository) {

    operator fun invoke(): Flow<Boolean> {
        return repository.isDatabaseEmpty()
    }
}