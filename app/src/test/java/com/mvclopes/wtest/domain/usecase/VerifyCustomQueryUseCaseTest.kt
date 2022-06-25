package com.mvclopes.wtest.domain.usecase

import com.mvclopes.wtest.utils.CustomQuery
import org.junit.Assert.assertTrue
import org.junit.Test

class VerifyCustomQueryUseCaseTest {

    private val verifyCustomQueryUseCase = VerifyCustomQueryUseCase()

    @Test
    fun `verifyCustomQuery should returns TextQuery when is passed only text in search term`() {
        // Given
        val searchTerm = "porto"

        // When
        val result = verifyCustomQueryUseCase(searchTerm)

        // Then
        assertTrue(result is CustomQuery.TextQuery)
    }

    @Test
    fun `verifyCustomQuery should returns NumberQuery when is passed only numbers in search term`() {
        // Given
        val searchTerm = "3750-011"

        // When
        val result = verifyCustomQueryUseCase(searchTerm)

        // Then
        assertTrue(result is CustomQuery.NumberQuery)
    }

    @Test
    fun `verifyCustomQuery should returns TextAndNumberQuery when is passed only numbers in search term`() {
        // Given
        val searchTerm = "3750-011 Porto"

        // When
        val result = verifyCustomQueryUseCase(searchTerm)

        // Then
        assertTrue(result is CustomQuery.TextAndNumberQuery)
    }
}