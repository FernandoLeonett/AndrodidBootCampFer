package com.bootcamp_android.domain.model

data class Reservation(
    val authorizationCode: String,val startDateTime: Long,val enDateDateTime: Long,val parkingLot: Int
)
