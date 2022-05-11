package com.bootcamp_android.parking_app.adapter

sealed class DataModel {

    data class Lot(val id: String,val date: String,val hour: String,var available: Boolean) :
        DataModel()

    data class Reservation(
        val start_date: String,
        val end_date: String,
        val star_hour: String,
        val end_hour: String
    ) : DataModel()
}
