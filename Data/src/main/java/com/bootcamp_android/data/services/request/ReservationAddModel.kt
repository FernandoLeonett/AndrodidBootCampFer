package com.bootcamp_android.data.services.request



data class ReservationAddModel(
    val authorizationCode: String,
    val startDate: Long,
    val endDate: Long,
    val parkingLot: Int,
)