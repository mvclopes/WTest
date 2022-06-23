package com.mvclopes.wtest.di

import com.mvclopes.wtest.data.datasource.PostalCodeDatasourceImpl
import com.mvclopes.wtest.data.datasource.local.db.PostalCodeDatabase
import com.mvclopes.wtest.data.datasource.local.db.getDataBase
import com.mvclopes.wtest.data.repository.PostalCodeRepositoryImpl
import com.mvclopes.wtest.domain.usecase.GetPostalCodesUseCase
import com.mvclopes.wtest.domain.usecase.InsertPostalCodeUseCase
import com.mvclopes.wtest.domain.usecase.IsDatabaseEmptyUseCase
import com.mvclopes.wtest.domain.usecase.ReadCsvFileUseCase
import com.mvclopes.wtest.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.Scope
import org.koin.dsl.module

val postalCodeModule = module {
    viewModel {
        HomeViewModel(
            readCsvFileUseCase = getReadCsvFileUseCase(),
            isDatabaseEmptyUseCase = isDatabaseEmptyUseCase(),
            getPostalCodesUseCase = getPostalCodesUseCase(),
            insertPostalCodeUseCase = getInsertPostalCodeUseCase(),
        )
    }
    single { getDataBase(context = get()) }
}

private fun Scope.getPostalCodeDAO() = get<PostalCodeDatabase>().postalCodeDAO()

private fun Scope.getPostalCodeDataSource() = PostalCodeDatasourceImpl(getPostalCodeDAO())

private fun Scope.getPostalCodeRepository() = PostalCodeRepositoryImpl(getPostalCodeDataSource())

private fun Scope.getReadCsvFileUseCase() = ReadCsvFileUseCase(context = get())

private fun Scope.getPostalCodesUseCase() = GetPostalCodesUseCase(repository = getPostalCodeRepository())

private fun Scope.isDatabaseEmptyUseCase() = IsDatabaseEmptyUseCase(repository = getPostalCodeRepository())

private fun Scope.getInsertPostalCodeUseCase() = InsertPostalCodeUseCase(repository = getPostalCodeRepository())
