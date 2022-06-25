package com.mvclopes.wtest.stub

import com.mvclopes.wtest.data.datasource.local.entity.PostalCodeEntity

fun getPostalCodesEntityStub() = listOf(
    PostalCodeEntity(
        localityCode = "locality code",
        localityName = "locality name",
        postalCodeNumber = "postal code number",
        postalDesignation = "postal code designation"
    )
)

fun getOnePostalCodeEntityStub() = PostalCodeEntity(
    localityCode = "locality code",
    localityName = "locality name",
    postalCodeNumber = "postal code number",
    postalDesignation = "postal code designation"
)

fun getPostalCodeNumberStub() = "3750-031"

fun getPostalDesignationStub() = "AGARÃO"