package com.mvclopes.wtest.di

import com.mvclopes.wtest.data.datasource.PostalCodeDatasourceImpl
import com.mvclopes.wtest.data.datasource.local.db.getDataBase
import com.mvclopes.wtest.data.repository.PostalCodeRepositoryImpl
import com.mvclopes.wtest.domain.usecase.ReadCsvFileUseCase
import com.mvclopes.wtest.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.Scope
import org.koin.dsl.module

val postalCodeModule = module {
    viewModel {
        HomeViewModel(
            readCsvFileUseCase = getReadCsvFileUseCase(),
            repository = getPostalCodeRepository(),
        )
    }
}

private fun Scope.getPostalCodeDatabase() = getDataBase(context = get())

private fun Scope.getPostalCodeDAO() = getPostalCodeDatabase().postalCodeDAO()

private fun Scope.getPostalCodeDataSource() = PostalCodeDatasourceImpl(getPostalCodeDAO())

private fun Scope.getPostalCodeRepository() = PostalCodeRepositoryImpl(getPostalCodeDataSource())

private fun Scope.getReadCsvFileUseCase() = ReadCsvFileUseCase(context = get())
