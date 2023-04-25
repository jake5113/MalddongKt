package com.jake5113.malddongkt.database

import android.content.Context

class FavoriteDB(val context: Context){

    val database = context.openOrCreateDatabase("Favorite", Context.MODE_PRIVATE, null)

    // DB에 저장 유무로 좋아요 상태 판별
    fun checkIsLike(name: String, address: String): Boolean {
        // DB에서 불러오기
        val cursor = database.rawQuery("SELECT * FROM toilet", null) ?: return false
        cursor.moveToFirst()

        for (i in 0 until cursor.count) {
            if ((name == cursor.getString(0)) && (address == cursor.getString(1))) {
                return true
            }
            cursor.moveToNext()
        }
        return false
    }

    fun insertDB(name: String, address: String){
        database.execSQL("INSERT INTO toilet (toiletNm, lnmAdres) VALUES ('${name}', '${address}')")
    }

    fun deleteDB(name:String){
        database.execSQL("DELETE FROM toilet WHERE toiletNm = '${name}'")
    }
}