package com.bootcamp_android.domain.model

class Parking(private val id: Int,private val lots: List<Lot>) {

    private var countSize: Int = lots.size
}