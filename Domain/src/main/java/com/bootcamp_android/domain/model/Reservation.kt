package com.bootcamp_android.domain.model

import java.io.Serializable

data class Reservation(
    val id: String,
    val authorizationCode: String,
    val starDateTimeInMillis: Long,
    val endDateTimeInMillis: Long,
    val parkingLot: Int
) : Serializable