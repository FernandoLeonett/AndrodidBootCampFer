package com.bootcamp_android.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Reservations")
data class Reservation(@PrimaryKey val id :String)
