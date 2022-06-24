package com.mvclopes.wtest.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_cod_postal")
data class PostalCodeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "cod_localidade") val localityCode: String,
    @ColumnInfo(name = "nome_localidade") val localityName: String,
    @ColumnInfo(name = "num_cod_postal") val postalCodeNumber: String,
    @ColumnInfo(name = "desig_postal") val postalDesignation: String
)
