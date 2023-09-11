package com.eazy.stcbusiness.network_module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)

class ApplicationModule {
    @Provides
    fun provideApiRepository(apiRepositoryImpl: ApiImplementationWs): ApiRepository =
        apiRepositoryImpl

}