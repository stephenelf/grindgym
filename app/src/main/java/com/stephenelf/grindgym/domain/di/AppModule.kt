package com.stephenelf.gymder.domain.di

import com.stephenelf.gymder.data.api.GymApi
import com.stephenelf.gymder.domain.repository.GymRepositoryImpl
import com.stephenelf.gymder.data.repository.GymRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGymRepository(gymApi: GymApi): GymRepository {
        return GymRepositoryImpl(gymApi)

    }
}