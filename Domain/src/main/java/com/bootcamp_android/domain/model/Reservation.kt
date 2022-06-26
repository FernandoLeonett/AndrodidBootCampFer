package com.bootcamp_android.domain.model

import java.io.Serializable
import java.util.*

data class Reservation(
    val id: String,val authorizationCode: String,val startDate: Long,val endDate: Long,val parkingLot: Int
) : Serializable