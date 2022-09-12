package com.app.test.domain.home.entity

import com.google.gson.annotations.SerializedName


data class Photos (

  @SerializedName("height"            ) var height           : Int?              = null,
  @SerializedName("html_attributions" ) var htmlAttributions : ArrayList<String> = arrayListOf(),
  @SerializedName("photo_reference"   ) var photoReference   : String?           = null,
  @SerializedName("width"             ) var width            : Int?              = null

)