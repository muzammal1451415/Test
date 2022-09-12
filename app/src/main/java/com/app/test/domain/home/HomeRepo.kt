package com.app.test.domain.home

import com.app.test.data.common.utils.DataState
import com.app.test.domain.home.entity.NearByStationsResponse
import kotlinx.coroutines.flow.Flow

interface HomeRepo {
    suspend fun getNearByChargingStations(location: String, radius: Int): Flow<DataState<NearByStationsResponse>>
}