package com.bootcamp_android.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ReservationTable")
data class ReservationRoom(

    @PrimaryKey
    val id: String,
    val authorizationCode: String,
    val starDateTimeInMillis: Long,
    val endDateTimeInMillis: Long,
    val parkingLot: Int

)