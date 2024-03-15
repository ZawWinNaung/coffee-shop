package com.example.coffeeshop.di

import com.example.coffeeshop.data.remote.repo.ApiServiceRepo
import com.example.coffeeshop.data.remote.repo.ApiServiceRepoImpl
import com.example.coffeeshop.data.remote.service.NetworkService
import com.example.coffeeshop.data.remote.service.NetworkServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiServiceRepoModule {

    @Binds
    internal abstract fun bindApiServiceRepo(apiServiceRepoImpl: ApiServiceRepoImpl): ApiServiceRepo

    @Binds
    internal abstract fun bindNetworkService(networkServiceImpl: NetworkServiceImpl): NetworkService
}