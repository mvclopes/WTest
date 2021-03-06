package com.mvclopes.wtest.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvclopes.wtest.domain.model.PostalCode
import com.mvclopes.wtest.domain.repository.PostalCodeRepository
import com.mvclopes.wtest.domain.usecase.ReadCsvFileUseCase
import com.mvclopes.wtest.domain.usecase.VerifyCustomQueryUseCase
import com.mvclopes.wtest.utils.CustomQuery
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val readCsvFileUseCase: ReadCsvFileUseCase,
    private val verifyCustomQueryUseCase: VerifyCustomQueryUseCase,
    private val repository: PostalCodeRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
): ViewModel() {

    private var _postalCodeList = MutableLiveData<List<PostalCode>>()
    val postalCodeList: LiveData<List<PostalCode>>
        get() = _postalCodeList

    private var _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private var _matchedPostalCode = MutableLiveData<List<PostalCode>>()
    val matchedPostalCode: LiveData<List<PostalCode>>
        get() = _matchedPostalCode

    private var _listToMonitoring = MutableLiveData<ListToMonitoring>(ListToMonitoring.None)
    val listToMonitoring: LiveData<ListToMonitoring>
        get() = _listToMonitoring

    fun searchByCustomQuery(searchTerm: String) {
        when (verifyCustomQueryUseCase(searchTerm)) {
            CustomQuery.NumberQuery -> postalCodeNumberQuery(searchTerm)
            CustomQuery.TextQuery -> postalDesignationQuery(searchTerm)
            CustomQuery.TextAndNumberQuery -> designationAndCodeNumberQuery(searchTerm)
        }
    }

    fun isDatabaseEmpty() {
        viewModelScope.launch {
            repository.isDatabaseEmpty()
                .flowOn(dispatcher)
                .onStart { isLoading(true) }
                .catch { handleError(it) }
                .collect { handleIsDatabaseEmpty(it) }
        }
    }

    private fun postalCodeNumberQuery(searchTerm: String) {
        viewModelScope.launch {
            repository.searchByPostalCodeNumber("%$searchTerm%")
                .flowOn(dispatcher)
                .catch { handleError(it) }
                .collect { handleOnSearch(it) }
        }
    }

    private fun postalDesignationQuery(searchTerm: String) {
        viewModelScope.launch {
            repository.searchByPostalDesignation("%$searchTerm%")
                .flowOn(dispatcher)
                .catch { handleError(it) }
                .collect { handleOnSearch(it) }
        }
    }

    private fun designationAndCodeNumberQuery(searchTerm: String) {
        val postalCodeNumber = "%${searchTerm.filter { it.isDigit() }}%"
        val postalDesignation = "%${searchTerm.replace(" ","").filter { it.isDigit().not() }}%"
        viewModelScope.launch {
            repository.searchByDesignationAndCodeNumber(postalCodeNumber, postalDesignation)
                .flowOn(dispatcher)
                .catch { handleError(it) }
                .collect { handleOnSearch(it) }
        }
    }

    private fun handleError(throwable: Throwable) {
        Log.i("TAG_", "exception: ${throwable.message}")
    }

    private fun handleOnSearch(postalCodes: List<PostalCode>) {
        if (!postalCodes.isNullOrEmpty()) {
            _matchedPostalCode.value = postalCodes
            _listToMonitoring.value = ListToMonitoring.Matched
        }
    }

    private fun isLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }

    private fun handleIsDatabaseEmpty(isEmpty: Boolean) {
        when (isEmpty) {
            true -> readCSV()
            else -> fetchData()
        }
    }

    private fun readCSV() {
        val postalCodeList = readCsvFileUseCase()
        viewModelScope.launch(dispatcher) {
            try {
                repository.insertAll(postalCodeList).collect { fetchData() }
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            repository.getAll()
                .flowOn(dispatcher)
                .onCompletion { isLoading(false) }
                .catch { handleError(it) }
                .collect {
                    _postalCodeList.value = it
                    _listToMonitoring.value = ListToMonitoring.PostalCode
                }
        }
    }

}

sealed class ListToMonitoring {
    object Matched: ListToMonitoring()
    object PostalCode: ListToMonitoring()
    object None: ListToMonitoring()
}

