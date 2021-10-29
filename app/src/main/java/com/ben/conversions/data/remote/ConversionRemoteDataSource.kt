package com.ben.conversions.data.remote

import javax.inject.Inject

class ConversionRemoteDataSource @Inject constructor(
    private val service: ConversionService
) : BaseDataSource() {

    suspend fun getConversions() = getResult {
        service.getConversions()
    }

}