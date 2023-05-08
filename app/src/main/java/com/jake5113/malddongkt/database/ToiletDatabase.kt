package com.jake5113.malddongkt.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ToiletItem::class], version = 1)
abstract class ToiletDatabase : RoomDatabase() {
    abstract fun toiletDao(): ToiletDao

    companion object{
        //private var
    }
}