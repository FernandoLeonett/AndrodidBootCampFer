package com.bootcamp_android.data.services.response

import com.google.gson.annotations.SerializedName

class DeleteResponse (
    @SerializedName("deleteReservation")
    val reservationId: String,
    val parkingId: String)
