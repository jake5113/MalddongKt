package com.jake5113.malddongkt.main.list.touristspot

import ted.gun0912.clustering.clustering.TedClusterItem
import ted.gun0912.clustering.geometry.TedLatLng
import java.io.Serializable

data class TouristResponse(
    var info: MutableList<TouristSpotItem>,
    var info_cnt: Int,
    var result: String
)

data class TouristSpotItem(
    var title: String, // 이름
    var introduction: String = "-", // 소개
    var img_path: String, // 일반 이미지

    var isFavorite: Boolean = false, // 좋아요 유무

    var address: String, //	주소
    var latitude: String = "-", // 위도
    var longitude: String = "-", // 경도
    var phone_no: String = "-", // 전화번호
    var post_code: String = "-", // 우편번호
    var road_address: String = "-", // 도로명 주소
    var tag: String = "-", //태그
    var thumbnail_path: String = "-", // 	썸네일 이미지
) : Serializable, TedClusterItem {
    constructor(title: String, introduction: String, img_path: String, isFavorite: Boolean) : this(
        title, introduction, img_path, isFavorite, ""
    ) {
        this.title = title
        this.introduction = introduction
        this.img_path = img_path
        this.isFavorite = isFavorite
    }

    override fun getTedLatLng(): TedLatLng = TedLatLng(latitude.toDouble(), longitude.toDouble())
}