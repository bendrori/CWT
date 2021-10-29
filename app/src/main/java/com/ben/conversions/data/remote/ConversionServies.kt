package com.ben.conversions.data.remote

import com.ben.conversions.data.utils.Constants.ConversionPath
import retrofit2.Response
import retrofit2.http.GET


interface ConversionService {

    @GET(ConversionPath)
    suspend fun getConversions(): Response<String>

}