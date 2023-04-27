package com.jake5113.malddongkt.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jake5113.malddongkt.main.list.toilet.ToiletItem

@Database(entities = [ToiletItem::class], version = 1)
abstract class ToiletDatabase : RoomDatabase() {
    abstract fun toiletDao(): ToiletDao

    companion object{
        //private var
    }
}