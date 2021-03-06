package com.mvclopes.wtest.domain.mapper

import com.mvclopes.wtest.data.local.entity.PostalCodeEntity
import com.mvclopes.wtest.domain.model.PostalCode

fun PostalCodeEntity.toDomain() = PostalCode(
    localityCode = localityCode,
    localityName = localityName,
    postalCodeNumber = postalCodeNumber,
    postalDesignation = postalDesignation
)

fun List<PostalCodeEntity>.toDomain() : List<PostalCode> {
    return this.map { it.toDomain() }
}
