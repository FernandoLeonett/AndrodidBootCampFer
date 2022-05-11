package com.bootcamp_android.parking_app.adapter

class Provider {

    companion object {
        val lots = lot_list()
        val reservations = reservationList()

        private fun lot_list() = listOf(

            DataModel.Lot("88","Miercoles, 25 May,2022","8:30 pm",true),

            DataModel.Lot("1","Monday, 25 May,2022","8:30 pm",false),
            DataModel.Lot("1","Monday, 25 May,2022","8:30 pm",false),
            DataModel.Lot("2","Monday, 25 May,2022","8:30 pm",false),
            DataModel.Lot("3","Monday, 25 May,2022","8:30 pm",false),
            DataModel.Lot("4","Monday, 25 May,2022","8:30 pm",false),
            DataModel.Lot("5","Monday, 25 May,2022","8:30 pm",true),
            DataModel.Lot("6","Monday, 25 May,2022","8:30 pm",true),
            DataModel.Lot("7","Monday, 25 May,2022","8:30 pm",true),
            DataModel.Lot("7","Monday, 25 May,2022","8:30 pm",true),
            DataModel.Lot("1","Monday, 25 May,2022","8:30 pm",true),
            DataModel.Lot("1","Monday, 25 May,2022","8:30 pm",true),
            DataModel.Lot("2","Monday, 25 May,2022","8:30 pm",true),
            DataModel.Lot("3","Monday, 25 May,2022","8:30 pm",false),
            DataModel.Lot("4","Monday, 25 May,2022","8:30 pm",false),
            DataModel.Lot("5","Monday, 25 May,2022","8:30 pm",false),
            DataModel.Lot("6","Monday, 25 May,2022","8:30 pm",false),
            DataModel.Lot("99","Monday, 25 May,2022","8:30 pm",false),

            )

        private fun reservationList() = listOf<DataModel.Reservation>(
            DataModel.Reservation(
                "Monday 25, May 2022","Monday 25, May 2022","7:30 am","8:30 am"
            ),
            DataModel.Reservation(
                "Monday 25, May 2022","Monday 25, May 2022","7:30 am","8:30 am"
            ),
            DataModel.Reservation(
                "Monday 25, May 2022","Monday 25, May 2022","7:30 am","8:30 am"
            ),
            DataModel.Reservation(
                "Monday 25, May 2022","Monday 25, May 2022","7:30 am","8:30 am"
            ),
            DataModel.Reservation(
                "Monday 25, May 2022","Monday 25, May 2022","7:30 am","8:30 am"
            ),
            DataModel.Reservation(
                "Monday 25, May 2022","Monday 25, May 2022","7:30 am","8:30 am"
            ),
            DataModel.Reservation(
                "Monday 25, May 2022","Monday 25, May 2022","7:30 am","8:30 am"
            ),
            DataModel.Reservation(
                "Monday 25, May 2022","Monday 25, May 2022","7:30 am","8:30 am"
            ),
            DataModel.Reservation(
                "Monday 25, May 2022","Monday 25, May 2022","7:30 am","8:30 am"
            ),
            DataModel.Reservation(
                "Monday 25, May 2022","Monday 25, May 2022","7:30 am","8:30 am"
            ),
        )
    }
}