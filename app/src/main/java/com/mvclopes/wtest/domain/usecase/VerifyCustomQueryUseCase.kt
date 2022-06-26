package com.mvclopes.wtest.domain.usecase

import com.mvclopes.wtest.utils.CustomQuery
import com.mvclopes.wtest.utils.numberRegex
import com.mvclopes.wtest.utils.textRegex

class VerifyCustomQueryUseCase {

    operator fun invoke(searchTerm: String): CustomQuery {
        return when {
            searchTerm.matches(textRegex()) -> CustomQuery.TextQuery
            searchTerm.matches(numberRegex()) -> CustomQuery.NumberQuery
            else -> CustomQuery.TextAndNumberQuery
        }
    }
}