package com.stephenelf.grindgym.domain.di

import com.stephenelf.grindgym.data.api.GymApi
import com.stephenelf.grindgym.domain.repository.GymRepositoryImpl
import com.stephenelf.grindgym.data.repository.GymRepository
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