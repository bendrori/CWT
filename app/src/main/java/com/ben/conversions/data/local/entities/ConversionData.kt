package com.ben.conversions.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conversions")
data class ConversionData(@PrimaryKey var id: Long = -1L, var value: String = "")

fun String?.mapToConversionDataList() = this?.split("\n")?.mapIndexed { index, s ->
    ConversionData(index.toLong(), s)
}