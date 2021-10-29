package com.ben.conversions.data.di

import android.content.Context
import com.ben.conversions.data.local.AppDatabase
import com.ben.conversions.data.local.ConversionDao
import com.ben.conversions.data.remote.BaseRetrofitService
import com.ben.conversions.data.remote.ConversionRemoteDataSource
import com.ben.conversions.data.remote.ConversionService
import com.ben.conversions.data.repositories.ConversionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideConversionDao(db: AppDatabase) = db.ConversionDao()

    @Singleton
    @Provides
    fun provideConversionService() = BaseRetrofitService.create(ConversionService::class.java)

    @Singleton
    @Provides
    fun provideConversionRemoteDataSource(service: ConversionService) =
        ConversionRemoteDataSource(service)

    @Singleton
    @Provides
    fun provideConversionRepository(
        conversionRemote: ConversionRemoteDataSource,
        conversionLocal: ConversionDao,
        @ApplicationContext context: Context
    ) = ConversionRepository(conversionRemote, conversionLocal,context)

}