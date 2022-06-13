package com.bootcamp_android.data.services.response

import com.google.gson.annotations.SerializedName

data class ParkingLotResponse (
    @SerializedName("parkingLot")
    var parkingLot:Int)
