package com.ben.conversions.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ben.conversions.data.local.entities.ConversionData

@Dao
interface ConversionDao {
    @Query("Select * from conversions")
    fun getConversions(): LiveData<List<ConversionData>>

    @Query("Delete from conversions")
    suspend fun deleteConversions()

    @Query("Select * from conversions where value like '%' || :search || '%' ")
    fun search(search: String): LiveData<List<ConversionData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConversion(item: ConversionData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: List<ConversionData>)

    @Update
    suspend fun updateConversion(item: ConversionData)

    @Delete
    suspend fun deleteConversion(item: ConversionData)
}