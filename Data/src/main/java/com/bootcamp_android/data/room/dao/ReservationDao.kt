package com.bootcamp_android.data.room.dao


import androidx.room.*
import com.bootcamp_android.data.room.entities.ReservationRoom


@Dao
interface ReservationDao {

    @Query("SELECT * FROM ReservationRoom")
    fun getReservations(): List<ReservationRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addReservation(addReservation: ReservationRoom)

    @Delete
    fun deleteReservation(deleteReservation: ReservationRoom)

}


