package com.mvclopes.wtest.domain.usecase

import com.mvclopes.wtest.utils.CustomQuery

class VerifyCustomQueryUseCase {

    private val textRegex = "[a-zA-Z\\u00C0-\\u024F]+".toRegex()
    private val numberRegex = "[0-9-]+".toRegex()

    operator fun invoke(searchTerm: String): CustomQuery {
        return when {
            searchTerm.matches(textRegex) -> CustomQuery.TextQuery
            searchTerm.matches(numberRegex) -> CustomQuery.NumberQuery
            else -> CustomQuery.TextAndNumberQuery
        }
    }
}