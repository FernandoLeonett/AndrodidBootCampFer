package com.bootcamp_android.parking_app.adapter

data class Reservation(
    val start_date: String,
    val end_date: String,
    val star_hour: String,
    val end_hour: String
) : DataModel
