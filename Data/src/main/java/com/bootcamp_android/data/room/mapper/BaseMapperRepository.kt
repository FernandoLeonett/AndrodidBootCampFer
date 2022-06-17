package com.bootcamp_android.data.room.mapper



interface BaseMapperRepository<E, D, F> {

    fun transformFromRoomToDomain(type: E): D

    fun transformFromDomainToRoom(type: D): E

    fun transformFromRepositoryToDomain(type: F): D

    fun transformFromRepositoryToRoom(type: F): E

}