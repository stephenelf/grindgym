package com.stephenelf.grindgym.di

import com.stephenelf.grindgym.data.remote.GymApi
import com.stephenelf.grindgym.data.repository.GymRepositoryImpl
import com.stephenelf.grindgym.domain.repository.GymRepository
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