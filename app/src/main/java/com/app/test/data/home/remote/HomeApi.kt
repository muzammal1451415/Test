package com.app.test.data.home.remote
import com.app.test.domain.home.entity.NearByStationsResponse
import retrofit2.http.*

interface HomeApi {

    @GET
    suspend fun getNearByChargingStations(
        @Url url: String,
        @Query ("location") location: String,
        @Query ("key") apiKey: String,
        @Query ("keyword") keywords: String,
        @Query ("radius") radius :Int
    ): NearByStationsResponse


}