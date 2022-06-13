package com.bootcamp_android.data.services.response

import com.google.gson.annotations.SerializedName

data class ParkingLotListResponse(
    @SerializedName("lotList")
    var lotList:List<ParkingLotResponse>)