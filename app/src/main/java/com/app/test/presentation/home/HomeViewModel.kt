package com.app.test.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.test.data.common.utils.DataState
import com.app.test.domain.home.usecase.NearbyChargingStationsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val nearByChargingStationUsecase: NearbyChargingStationsUseCase): ViewModel() {
    private val state = MutableStateFlow<HomeStateModel>(HomeStateModel.Init)
    val mState: StateFlow<HomeStateModel> get() = state

    private fun setLoading() {

        state.value = HomeStateModel.IsLoading(true)
    }

    private fun hideLoading() {

        state.value = HomeStateModel.IsLoading(false)
    }

    fun getNearByChargingStations(location:String,radius: Int){
        viewModelScope.launch {
            nearByChargingStationUsecase.execute(NearbyChargingStationsUseCase.Params(location,radius)).onStart {
                setLoading()
            }.collect{

                when(it){

                    is DataState.GenericError ->{
                        state.value = HomeStateModel.GenericError("JSON Data parsing error")
                    }
                    is DataState.Success -> {
                        val response = it.value
                        if(response.status != "OK"){
                            state.value = HomeStateModel.StatusFailed(response.status?:"Unknown Error")
                        }
                        else{
                            val data = it.value.results
                            state.value = HomeStateModel.NearByChargingPlacesSuccess(data)


                        }
                    }
                }

            }
        }
    }



}