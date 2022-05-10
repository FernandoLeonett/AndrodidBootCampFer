package com.bootcamp_android.parking_app.adapter

import com.bootcamp_android.parking_app.Lot

class Provider {


    companion object {
        val lots = list()

        private fun list() = listOf(



            Lot("88","Miercoles, 25 May,2022","8:30 pm",true),

            Lot("1","Monday, 25 May,2022","8:30 pm",false),
            Lot("1","Monday, 25 May,2022","8:30 pm",false),
            Lot("2","Monday, 25 May,2022","8:30 pm",false),
            Lot("3","Monday, 25 May,2022","8:30 pm",false),
            Lot("4","Monday, 25 May,2022","8:30 pm",false),
            Lot("5","Monday, 25 May,2022","8:30 pm",true),
            Lot("6","Monday, 25 May,2022","8:30 pm",true),
            Lot("7","Monday, 25 May,2022","8:30 pm",true),
            Lot("7","Monday, 25 May,2022","8:30 pm",true),
            Lot("1","Monday, 25 May,2022","8:30 pm",true),
            Lot("1","Monday, 25 May,2022","8:30 pm",true),
            Lot("2","Monday, 25 May,2022","8:30 pm",true),
            Lot("3","Monday, 25 May,2022","8:30 pm",false),
            Lot("4","Monday, 25 May,2022","8:30 pm",false),
            Lot("5","Monday, 25 May,2022","8:30 pm",false),
            Lot("6","Monday, 25 May,2022","8:30 pm",false),
            Lot("99","Monday, 25 May,2022","8:30 pm",false),

        )

    }


}