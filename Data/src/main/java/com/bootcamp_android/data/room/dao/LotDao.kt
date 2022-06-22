package com.bootcamp_android.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bootcamp_android.data.room.entities.LotRoom

@Dao
interface LotDao {

    @Query("SELECT * FROM LotTable")
    fun getLots(): List<LotRoom>

//    @Query("SELECT * FROM LotRoom Where parkingLot= :id")
//    fun getLotByID(id: Int): LotRoom

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLot(addReservation: LotRoom)

}