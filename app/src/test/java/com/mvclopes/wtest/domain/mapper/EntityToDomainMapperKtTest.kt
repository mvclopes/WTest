package com.mvclopes.wtest.domain.mapper

import com.mvclopes.wtest.stub.getOnePostalCodeEntityStub
import com.mvclopes.wtest.stub.getPostalCodesEntityStub
import org.junit.Assert.assertNotSame
import org.junit.Test

class EntityToDomainMapperKtTest {

    @Test
    fun `list extension function should returns a list of domain object when it trigger`() {
        // Given
        val objectEntityList = getPostalCodesEntityStub()

        // When
        val result = objectEntityList.toDomain()

        // Then
        assertNotSame(objectEntityList, result)
    }

    @Test
    fun `entity extension function should returns a domain object when it trigger`() {
        // Given
        val objectEntity = getOnePostalCodeEntityStub()

        // When
        val result = objectEntity.toDomain()

        // Then
        assertNotSame(objectEntity, result)
    }
}