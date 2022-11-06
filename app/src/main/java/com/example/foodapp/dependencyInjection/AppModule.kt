package com.example.foodapp.dependencyInjection

import com.example.foodapp.data.dataSource.FoodDataSource
import com.example.foodapp.data.repository.FoodRepository
import com.example.foodapp.retrofit.ApiUtils
import com.example.foodapp.retrofit.FoodDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideFoodRepository(fds: FoodDataSource) : FoodRepository {
        return FoodRepository(fds)
    }
    @Provides
    @Singleton
    fun provideFoodDataSource(fdao: FoodDao) : FoodDataSource {
        return FoodDataSource(fdao)
    }
    @Provides
    @Singleton
    fun provideFoodDataDao() : FoodDao {
        return ApiUtils.getFoodDao()
    }

}