package com.bootcamp_android.data.services.request



data class ReservationResponse(
    val authorizationCode: String,
    val startDate: Long,
    val endDate: Long,
    val parkingLot: Int,
)