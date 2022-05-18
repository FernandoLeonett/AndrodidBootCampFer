package com.bootcamp_android.parking_app.adapter

data class Reservation(
    val startDate: String,
    val endDate: String,
    val startHour: String,
    val endHour: String
) : DataModel
