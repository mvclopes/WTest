package com.mvclopes.wtest.data.datasource


import com.mvclopes.wtest.data.local.dao.PostalCodeDao
import com.mvclopes.wtest.data.local.entity.PostalCodeEntity
import com.mvclopes.wtest.stub.getOnePostalCodeEntityStub
import com.mvclopes.wtest.stub.getPostalCodeNumberStub
import com.mvclopes.wtest.stub.getPostalCodesEntityStub
import com.mvclopes.wtest.stub.getPostalDesignationStub
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

@ExperimentalCoroutinesApi
class PostalCodeDatasourceImplTest {

    private val mockDao: PostalCodeDao = mockk(relaxed = true)
    private val target = PostalCodeDatasourceImpl(postalCodeDao = mockDao)

    @Test
    fun `getAll should returns a list of postal code when db returns success`() = runBlockingTest {
        // Given
        val expectedResult = getPostalCodesEntityStub()
        coEvery { mockDao.getAll() } returns expectedResult

        // When
        target.getAll().collect { result ->
            // Then
            assertEquals(expectedResult, result)
        }
    }

    @Test
    fun `isDatabaseEmpty should returns true when db is empty`() = runBlockingTest {
        // Given
        coEvery { mockDao.isDatabaseEmpty() } returns null

        // When
        target.isDatabaseEmpty().collect { result ->
            // Then
            assertTrue(result)
        }
    }

    @Test
    fun `isDatabaseEmpty should returns false when db is not empty`() = runBlockingTest {
        // Given
        coEvery { mockDao.isDatabaseEmpty() } returns getOnePostalCodeEntityStub()

        // When
        target.isDatabaseEmpty().collect { result ->
            // Then
            assertFalse(result)
        }
    }

    @Test
    fun `searchByPostalCodeNumber should returns a list of postal code when is used a valid search term`() = runBlockingTest {
        // Given
        val searchTerm = getPostalCodeNumberStub()
        val expectedResult = getPostalCodesEntityStub()
        coEvery { mockDao.searchByPostalCodeNumber(searchTerm) } returns expectedResult

        // When
        target.searchByPostalCodeNumber(searchTerm).collect { result ->
            // Then
            assertEquals(expectedResult, result)
            assertNotNull(result)
        }
    }

    @Test
    fun `searchByPostalCodeNumber should returns an empty list when is used an invalid search term`() = runBlockingTest {
        // Given
        val searchTerm = getPostalCodeNumberStub()
        val expectedResult = emptyList<PostalCodeEntity>()
        coEvery { mockDao.searchByPostalCodeNumber(searchTerm) } returns expectedResult

        // When
        target.searchByPostalCodeNumber(searchTerm).collect { result ->
            // Then
            assertEquals(expectedResult, result)
            assertTrue(result.isEmpty())
        }
    }

    @Test
    fun `searchByPostalDesignation should returns a list of postal code when is used a valid search term`() = runBlockingTest {
        // Given
        val searchTerm = getPostalDesignationStub()
        val expectedResult = getPostalCodesEntityStub()
        coEvery { mockDao.searchByPostalDesignation(searchTerm) } returns expectedResult

        // When
        target.searchByPostalDesignation(searchTerm).collect { result ->
            // Then
            assertEquals(expectedResult, result)
            assertNotNull(result)
        }
    }

    @Test
    fun `searchByPostalDesignation should returns an empty list when is used an invalid search term`() = runBlockingTest {
        // Given
        val searchTerm = getPostalDesignationStub()
        val expectedResult = emptyList<PostalCodeEntity>()
        coEvery { mockDao.searchByPostalDesignation(searchTerm) } returns expectedResult

        // When
        target.searchByPostalDesignation(searchTerm).collect { result ->
            // Then
            assertEquals(expectedResult, result)
            assertTrue(result.isEmpty())
        }
    }

    @Test
    fun `searchByDesignationAndCodeNumber should returns a list of postal code when is used a valid search terms`() = runBlockingTest {
        // Given
        val postalCodeNumberSearchTerm = getPostalCodeNumberStub()
        val postalDesignationSearchTerm = getPostalDesignationStub()
        val expectedResult = getPostalCodesEntityStub()
        coEvery {
            mockDao.searchByDesignationAndCodeNumber(
                postalCodeNumberSearchTerm,
                postalDesignationSearchTerm
            )
        } returns expectedResult

        // When
        target.searchByDesignationAndCodeNumber(
            postalCodeNumberSearchTerm,
            postalDesignationSearchTerm
        ).collect { result ->
            // Then
            assertEquals(expectedResult, result)
            assertNotNull(result)
        }
    }

    @Test
    fun `searchByDesignationAndCodeNumber should returns an empty list when is used an invalid search terms`() = runBlockingTest {
        // Given
        val postalCodeNumberSearchTerm = getPostalCodeNumberStub()
        val postalDesignationSearchTerm = getPostalDesignationStub()
        val expectedResult = getPostalCodesEntityStub()
        coEvery {
            mockDao.searchByDesignationAndCodeNumber(
                postalCodeNumberSearchTerm,
                postalDesignationSearchTerm
            )
        } returns expectedResult

        // When
        target.searchByDesignationAndCodeNumber(
            postalCodeNumberSearchTerm,
            postalDesignationSearchTerm
        ).collect { result ->
            // Then
            assertEquals(expectedResult, result)
            assertNotNull(result)
        }
    }
}