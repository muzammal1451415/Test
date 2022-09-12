package com.app.test.domain.home.usecase

import com.app.test.data.common.utils.DataState
import com.app.test.data.common.utils.NetworkHelper
import com.app.test.domain.home.HomeRepo
import com.app.test.domain.home.entity.NearByStationsResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NearbyChargingStationsUseCase@Inject constructor(private val repo: HomeRepo):
    NetworkHelper<NearbyChargingStationsUseCase.Params, NearByStationsResponse>(){

    data class Params(val location:String, val radius: Int)

    override suspend fun buildUseCase(parameter: Params): Flow<DataState<NearByStationsResponse>> {
        return repo.getNearByChargingStations(parameter.location,parameter.radius)
    }
}