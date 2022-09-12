package com.app.test.presentation.home

import com.app.test.domain.home.entity.Results

sealed class HomeStateModel {
    object Init: HomeStateModel()
    data class IsLoading(val isLoading:Boolean):HomeStateModel()
    data class StatusFailed(val message:String):HomeStateModel()
    data class NearByChargingPlacesSuccess(val videosResponse:List<Results>):HomeStateModel()
    data class GenericError(val messagge:String):HomeStateModel()
}