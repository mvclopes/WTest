package com.mvclopes.wtest.domain.usecase

import android.content.Context
import android.os.Environment
import android.util.Log
import com.mvclopes.wtest.data.datasource.local.entity.PostalCodeEntity
import com.mvclopes.wtest.domain.repository.PostalCodeRepository
import com.mvclopes.wtest.utils.COMMA_SEPARATOR
import com.mvclopes.wtest.utils.CSV_DIR_NAME
import com.mvclopes.wtest.utils.CSV_FILE_NAME
import java.io.BufferedReader
import java.io.DataInputStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

class ReadCsvFileUseCase(private val context: Context) {

    operator fun invoke(): List<PostalCodeEntity> {
        val postalCodeList = mutableListOf<PostalCodeEntity>()
        try {
            val fileInputStream = FileInputStream(context.getExternalFilesDir("$CSV_DIR_NAME/$CSV_FILE_NAME"))
            val iterator = BufferedReader(InputStreamReader(DataInputStream(fileInputStream)))
                .lineSequence()
                .iterator()

            // To ensure that the first line (headers) is not read
            iterator.next()

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
            Log.i("Tag_", "finished reading the .csv file")
            return postalCodeList
        } catch (e:Exception) {
            Log.i("TAG_", "exception: ${e.message}")
        }
        return postalCodeList
    }
}