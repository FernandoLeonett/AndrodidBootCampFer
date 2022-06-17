package com.bootcamp_android.data.room.localdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bootcamp_android.data.room.dao.LotDao
import com.bootcamp_android.data.room.dao.ReservationDao
import com.bootcamp_android.data.room.entities.LotRoom
import com.bootcamp_android.data.room.entities.ReservationRoom

@Database(
    entities = [LotRoom::class, ReservationRoom::class],version = 1)
abstract class ParkingDataBase : RoomDatabase() {

    companion object {

        private const val DATABASE_NAME = "parking_database"
        private lateinit var instance: ParkingDataBase

        @Synchronized
        fun getInstance(context: Context): ParkingDataBase {
            if (!this::instance.isInitialized) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    ParkingDataBase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }

    }

    abstract fun getLotsDao(): LotDao
    abstract fun getReservationDao(): ReservationDao

}

