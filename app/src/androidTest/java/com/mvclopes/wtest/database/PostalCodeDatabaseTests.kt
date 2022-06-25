package com.mvclopes.wtest.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mvclopes.wtest.data.datasource.local.dao.PostalCodeDao
import com.mvclopes.wtest.data.datasource.local.db.PostalCodeDatabase
import com.mvclopes.wtest.data.datasource.local.entity.PostalCodeEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class PostalCodeDatabaseTests {

    private lateinit var database: PostalCodeDatabase
    private lateinit var postalCodeDao: PostalCodeDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, PostalCodeDatabase::class.java
        ).build()
        postalCodeDao = database.postalCodeDAO()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    fun insertPostalCode_should_insert_new_object_in_database_correctly() = runBlocking {
        // Given
        val postalCodeEntity = getPostalCodeEntity()

        // When
        postalCodeDao.insertPostalCode(postalCodeEntity)
        val result = postalCodeDao.getAll()

        // Then
        assertTrue(result.contains(postalCodeEntity))
    }

    @Test
    fun isDatabaseEmpty_should_returns_content_when_database_is_not_empty() = runBlocking {
        // Given
        insertNewPostalCode()

        // When
        val result = postalCodeDao.isDatabaseEmpty()

        // Then
        assertNotNull(result)
    }

    @Test
    fun isDatabaseEmpty_should_not_return_content_when_database_is_empty() = runBlocking {
        // When
        val result = postalCodeDao.isDatabaseEmpty()

        // Then
        assertNull(result)
    }

    @Test
    fun searchByPostalCodeNumber_should_returns_content_when_is_used_valid_search_term() = runBlocking {
        // Given
        insertPostalCodeList()
        val searchTerm = "%3750-%"

        // When
        val result = postalCodeDao.searchByPostalCodeNumber(searchTerm)

        // Then
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun searchByPostalCodeNumber_should_not_return_content_when_is_used_invalid_search_term() = runBlocking {
        // Given
        insertPostalCodeList()
        val searchTerm = "%3750-7%"

        // When
        val result = postalCodeDao.searchByPostalCodeNumber(searchTerm)

        // Then
        assertTrue(result.isEmpty())
    }

    @Test
    fun searchByPostalDesignation_should_returns_content_when_is_used_valid_search_term() = runBlocking {
        // Given
        insertPostalCodeList()
        val searchTerm = "%ag%"

        // When
        val result = postalCodeDao.searchByPostalDesignation(searchTerm)

        // Then
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun searchByPostalDesignation_should_not_return_content_when_is_used_invalid_search_term() = runBlocking {
        // Given
        insertPostalCodeList()
        val searchTerm = "%porto%"

        // When
        val result = postalCodeDao.searchByPostalDesignation(searchTerm)

        // Then
        assertTrue(result.isEmpty())
    }

    @Test
    fun searchByDesignationAndCodeNumber_should_returns_content_when_is_used_valid_search_term() = runBlocking {
        // Given
        insertPostalCodeList()
        val postalCodeNumber = "%3750%"
        val postalDesignation = "%ag%"

        // When
        val result = postalCodeDao
            .searchByDesignationAndCodeNumber(postalCodeNumber, postalDesignation)

        // Then
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun searchByDesignationAndCodeNumber_should_not_return_content_when_is_used_invalid_search_term() = runBlocking {
        // Given
        insertPostalCodeList()
        val postalCodeNumber = "%3750%"
        val postalDesignation = "%porto%"

        // When
        val result = postalCodeDao
            .searchByDesignationAndCodeNumber(postalCodeNumber, postalDesignation)

        // Then
        assertTrue(result.isEmpty())
    }

    private suspend fun insertNewPostalCode() = postalCodeDao.insertPostalCode(getPostalCodeEntity())

    private suspend fun insertPostalCodeList() = postalCodeDao.insertAll(getPostalCodeList())

    private fun getPostalCodeList() = listOf(
        PostalCodeEntity(
            id = 1,
            localityCode = "249",
            localityName = "Alcafaz",
            postalCodeNumber = "3750-011",
            postalDesignation = "AGADÃO"
        ),
        PostalCodeEntity(
            id = 2,
            localityCode = "258",
            localityName = "Aguada de Baixo",
            postalCodeNumber = "3750-996",
            postalDesignation = "AGUADA DE BAIXO"
        ),
        PostalCodeEntity(
            id = 3,
            localityCode = "283",
            localityName = "Póvoa do Vale do Trigo",
            postalCodeNumber = "3750-364",
            postalDesignation = "BELAZAIMA DO CHÃO"
        )
    )

    private fun getPostalCodeEntity() = PostalCodeEntity(
        id = 1,
        localityCode = "249",
        localityName = "Alcafaz",
        postalCodeNumber = "3750-011",
        postalDesignation = "AGADÃO"
    )
}
