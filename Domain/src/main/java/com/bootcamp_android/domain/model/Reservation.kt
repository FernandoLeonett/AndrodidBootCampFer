package com.bootcamp_android.domain.model

import java.io.Serializable

data class Reservation(
    val id: String,
    val authorizationCode: String,
    val startDate: Long =-1,
    val endDate: Long =-1,
    val parkingLot: Int
) : Serializable