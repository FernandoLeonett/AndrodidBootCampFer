package com.bootcamp_android.parking_app.adapter

class Provider {

    companion object {
        val lots = lot_list()
        val reservations = reservationList()

        private fun lot_list() = listOf(

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

        private fun reservationList() = listOf<Reservation>(
            Reservation(
                "Monday 25, May 2022","Monday 25, May 2022","7:30 am","8:30 am"
            ),
            Reservation(
                "Monday 25, May 2022","Monday 25, May 2022","7:30 am","8:30 am"
            ),
            Reservation(
                "Monday 25, May 2022","Monday 25, May 2022","7:30 am","8:30 am"
            ),
            Reservation(
                "Monday 25, May 2022","Monday 25, May 2022","7:30 am","8:30 am"
            ),
            Reservation(
                "Monday 25, May 2022","Monday 25, May 2022","7:30 am","8:30 am"
            ),
            Reservation(
                "Monday 25, May 2022","Monday 25, May 2022","7:30 am","8:30 am"
            ),
            Reservation(
                "Monday 25, May 2022","Monday 25, May 2022","7:30 am","8:30 am"
            ),
            Reservation(
                "Monday 25, May 2022","Monday 25, May 2022","7:30 am","8:30 am"
            ),
            Reservation(
                "Monday 25, May 2022","Monday 25, May 2022","7:30 am","8:30 am"
            ),
            Reservation(
                "Monday 25, May 2022","Monday 25, May 2022","7:30 am","8:30 am"
            ),
        )
    }
}