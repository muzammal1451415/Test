package com.app.test.domain.home.entity

import com.google.gson.annotations.SerializedName


data class NearByStationsResponse (

  @SerializedName("html_attributions" ) var htmlAttributions : ArrayList<String>  = arrayListOf(),
  @SerializedName("next_page_token"   ) var nextPageToken    : String?            = null,
  @SerializedName("results"           ) var results          : ArrayList<Results> = arrayListOf(),
  @SerializedName("status"            ) var status           : String?            = null

)


data class NearByStationRequest (
  @SerializedName("location") var location : String,
  @SerializedName("key") var apiKey : String,
  @SerializedName("keyword") var keywords : String,
  @SerializedName("radius") var radius : Int = 500
)