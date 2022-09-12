package com.app.test.data.home.module

import com.app.test.data.home.HomeRepoImp
import com.app.test.data.home.remote.HomeApi
import com.app.test.domain.home.HomeRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    fun providesHomeRepo(homeApi: HomeApi):HomeRepo{
        return HomeRepoImp(homeApi)
    }

    @Provides
    fun providesHomeApi(retrofit:Retrofit):HomeApi{
        return retrofit.create(HomeApi::class.java)
    }

}