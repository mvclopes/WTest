package com.mvclopes.wtest.domain.usecase

import android.content.Context
import android.util.Log
import com.mvclopes.wtest.data.datasource.local.entity.PostalCodeEntity
import com.mvclopes.wtest.domain.repository.PostalCodeRepository
import com.mvclopes.wtest.utils.COMMA_SEPARATOR
import java.io.BufferedReader
import java.io.InputStreamReader

class ReadCsvFileUseCase(
    private val context: Context,
    private val repository: PostalCodeRepository
) {
    operator fun invoke() {
        try {
            val inputStream = context.assets.open("my-files.csv")
            val iterator = BufferedReader(InputStreamReader(inputStream)).lineSequence().iterator()
            iterator.next() // Para garantir que a primeira linha (cabeçalhos) não seja lida
            val postalCodeList = mutableListOf<PostalCodeEntity>()

            while (iterator.hasNext()) {
                val row = iterator.next().split(COMMA_SEPARATOR)
                val postalCodeEntity = PostalCodeEntity(
                    localityCode = row[2],
                    localityName = row[3],
                    postalCodeNumber = row[14],
                    postalCodeExtensionNumber = row[15],
                    postalDesignation = row[16],
                )
                postalCodeList.add(postalCodeEntity)
            }
            repository.insertAll(postalCodeList)
        } catch (e:Exception) {
            Log.i("TAG_CSV", "exception: ${e.message}")
        }
    }
}