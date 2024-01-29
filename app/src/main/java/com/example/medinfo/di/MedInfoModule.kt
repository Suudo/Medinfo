package com.example.medinfo.di

import com.example.medinfo.data.repository.PostRepository
import com.example.medinfo.data.remote.api.MedInfoApi
import com.example.medinfo.domain.repository.PostRepositoryImpl
import com.example.medinfo.domain.use_case.PostUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MedInfoModule {

    @Provides
    @Singleton
    fun provideRiftiumApi(): MedInfoApi =
        Retrofit.Builder()
            .baseUrl("https://github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()

    @Provides
    @Singleton
    fun providePostRepository(api: MedInfoApi): PostRepository {
        return PostRepositoryImpl(api)
    }

    fun providePostUseCase(repository: PostRepository): PostUseCase {
        return PostUseCase(repository)
    }

}