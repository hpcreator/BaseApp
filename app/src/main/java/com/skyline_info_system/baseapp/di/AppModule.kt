package com.skyline_info_system.baseapp.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    /*@Singleton
    @Provides
    fun provideMainRepository(apiService: ApiService, userDao: UserDao): MainRepository {
        return MainRepository(apiService, userDao)
    }*/
}