package com.app.test.data.home

import com.app.test.BuildConfig
import com.app.test.data.common.utils.DataState
import com.app.test.data.home.remote.HomeApi
import com.app.test.domain.home.HomeRepo

import com.app.test.domain.home.entity.NearByStationsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepoImp (private val api: HomeApi): HomeRepo{

    override suspend fun getNearByChargingStations(
        location: String,
        radius: Int
    ): Flow<DataState<NearByStationsResponse>>  = flow {
        emit(DataState.Success(api.getNearByChargingStations("maps/api/place/nearbysearch/json?",location,BuildConfig.MapApiKey,"charging+station",radius)))
    }



}