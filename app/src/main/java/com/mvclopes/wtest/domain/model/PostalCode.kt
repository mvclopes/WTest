package com.mvclopes.wtest.domain.model

data class PostalCode(
    val localityCode: String,
    val localityName: String,
    val postalCodeNumber: String,
    val postalCodeExtensionNumber: String,
    val postalDesignation: String
)
