package com.mvclopes.wtest.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvclopes.wtest.domain.model.PostalCode
import com.mvclopes.wtest.domain.repository.PostalCodeRepository
import com.mvclopes.wtest.domain.usecase.ReadCsvFileUseCase
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
    private val repository: PostalCodeRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
): ViewModel() {

    private var _postalCodeList = MutableLiveData<List<PostalCode>>()
    val postalCodeList: LiveData<List<PostalCode>>
        get() = _postalCodeList

    private var _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun isDatabaseEmpty() {
        viewModelScope.launch {
            repository.isDatabaseEmpty()
                .flowOn(dispatcher)
                .onStart { isLoading(true) }
                .catch { Log.i("TAG_ViewModel", "exception: ${it.message}") }
                .collect { handleIsDatabaseEmpty(it) }
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
                Log.i("TAG_ViewModel", "exception inserting data: ${e.message}")
            }
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            repository.getAll()
                .flowOn(dispatcher)
                .onCompletion { isLoading(false) }
                .catch { Log.i("TAG_ViewModel", "exception getting data: ${it.message}") }
                .collect {
                    _postalCodeList.value = it
                }
        }
    }

}
