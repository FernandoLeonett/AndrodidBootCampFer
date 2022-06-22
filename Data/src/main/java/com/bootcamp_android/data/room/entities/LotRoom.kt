package com.bootcamp_android.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LotTable")
data class LotRoom(
    @PrimaryKey var parkingLot: Int,
    )