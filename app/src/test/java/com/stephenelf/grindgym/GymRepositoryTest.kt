package com.stephenelf.grindgym

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.stephenelf.grindgym.data.api.GymApi
import com.stephenelf.grindgym.domain.repository.GymRepositoryImpl
import com.stephenelf.grindgym.data.repository.GymRepository
import com.stephenelf.grindgym.domain.di.LocalDateTimeAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@OptIn(ExperimentalCoroutinesApi::class)
class GymRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var gymApi: GymApi
    private lateinit var gymRepository: GymRepository

    @Before
    fun setUp() {
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.HEADERS
        }
        val okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .build()
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory())
            .add(LocalDateTimeAdapter())
            .build()
        gymApi = Retrofit.Builder()
            .baseUrl("https://data.townofcary.org/api/explore/v2.1/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build().create(GymApi::class.java)
        gymRepository = GymRepositoryImpl(gymApi)

    }

    @Test
    fun getgymFromRepository() = runTest {

        System.out.println("Testing repository")
        gymRepository.getGymList().collect{ gyms->
            System.out.println("Name: ${gyms.size}")
            Assert.assertEquals(gyms.size,100)
        }
    }

}