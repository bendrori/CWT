package com.ben.conversions.data.repositories

import android.content.Context
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.ben.conversions.R
import com.ben.conversions.data.local.ConversionDao
import com.ben.conversions.data.local.entities.ConversionData
import com.ben.conversions.data.local.entities.mapToConversionDataList
import com.ben.conversions.data.remote.ConversionRemoteDataSource
import com.ben.conversions.data.utils.Resource
import javax.inject.Inject

class ConversionRepository @Inject constructor(
    private var conversionRemote: ConversionRemoteDataSource,
    private var conversionLocal: ConversionDao,
    private val context: Context
) {

    fun fetchConversions() = liveData {
        conversionLocal.deleteConversions()
        emit(Resource.loading())
        val source = conversionLocal.getConversions().map { Resource.success(it) }
        emitSource(source)

        val responseStatus = conversionRemote.getConversions()
        if (responseStatus.status == Resource.Status.SUCCESS) {
            responseStatus.data?.mapToConversionDataList()?.let {
                conversionLocal.insert(it)
            }
        } else if (responseStatus.status == Resource.Status.ERROR) {
            emit(Resource.error(context.getString(R.string.failed_to_download)))
            emitSource(source)
        }
    }

    fun searchConversions(text: String) = liveData {
        val source = conversionLocal.search(text).map {
            if (it.isNullOrEmpty()) Resource.error(
                data = listOf(ConversionData()),
                message = context.getString(R.string.failed_to_find)
            ) else Resource.success(it)
        }
        emitSource(source)
    }

}