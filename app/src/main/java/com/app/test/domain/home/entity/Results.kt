package com.app.test.domain.home.entity

import com.google.gson.annotations.SerializedName


data class Results (

  @SerializedName("business_status"       ) var businessStatus      : String?           = null,
  @SerializedName("geometry"              ) var geometry            : Geometry?         = Geometry(),
  @SerializedName("icon"                  ) var icon                : String?           = null,
  @SerializedName("icon_background_color" ) var iconBackgroundColor : String?           = null,
  @SerializedName("icon_mask_base_uri"    ) var iconMaskBaseUri     : String?           = null,
  @SerializedName("name"                  ) var name                : String?           = null,
  @SerializedName("place_id"              ) var placeId             : String?           = null,
  @SerializedName("rating"                ) var rating              : Float?              = null,
  @SerializedName("reference"             ) var reference           : String?           = null,
  @SerializedName("scope"                 ) var scope               : String?           = null,
  @SerializedName("types"                 ) var types               : ArrayList<String> = arrayListOf(),
  @SerializedName("user_ratings_total"    ) var userRatingsTotal    : Float?              = null,
  @SerializedName("vicinity"              ) var vicinity            : String?           = null

)