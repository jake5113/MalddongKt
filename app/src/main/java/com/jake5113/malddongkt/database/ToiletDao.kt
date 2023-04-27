package com.jake5113.malddongkt.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.jake5113.malddongkt.main.list.toilet.ToiletItem

interface ToiletDao {
    @Insert
    fun insertToiletData(toiletItem: ToiletItem)

    @Delete
    fun deleteToiletData(toiletItem: ToiletItem)

    @Query("SELECT * FROM ToiletItem")
    fun getAllToiletData(toiletItem: ToiletItem)

}