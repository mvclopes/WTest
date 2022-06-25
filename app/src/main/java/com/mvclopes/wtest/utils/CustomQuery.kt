package com.mvclopes.wtest.utils

sealed class CustomQuery {
    object NumberQuery: CustomQuery()
    object TextQuery: CustomQuery()
    object TextAndNumberQuery: CustomQuery()
}
