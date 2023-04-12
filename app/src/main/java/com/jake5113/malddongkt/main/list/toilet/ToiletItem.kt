package com.jake5113.malddongkt.main.list.toilet

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class ToiletResponse(
    var response: Response
)

data class Response(
    var body: Body,
    var header: Header
)

data class Header(
    var resultCode: String,
    var resultMsg: String
)

data class Body(
    var items: Items,
    var numOfRows: Int,
    var pageNo: Int,
    var totalCount: Int
)

data class Items(
    var item: MutableList<ToiletItem>
)

data class ToiletItem (
    var toiletNm: String, // 화장실 명
    var lnmAdres: String, // 지번주소
    var photo: List<String>?, // 사진

    var laCrdnt: String = "-", // 위도
    var loCrdnt: String = "-", // 경도
    var emdNm: String = "-", //읍면동명
    var rnAdres: String = "-", // 도로명주소

    var opnTimeInfo: String = "-", // 개방 시간 정보
    var mngrInsttNm: String = "-", // 관리 기관 명
    var toiletPosesnSeNm: String = "-", // 화장실 소유 구분 명
    var telno: String = "-", // 전화번호
    var photoYn: String = "-", // 사진 유무

    var mwmnCmnuseToiletYn: String = "-", // 남녀공용화장실여부
    var diaperExhgTablYn: String = "-", // 기저귀교환탁자여부
    var etcCn: String = "-", // 기타 내용

    var maleClosetCnt: String = "-", // 남성 대변기 수
    var maleUrinalCnt: String = "-", // 남성 소변기 수
    var maleDspsnClosetCnt: String = "-", // 남성 장애인 대변기 수
    var maleDspsnUrinalCnt: String = "-", // 남성 장애인 소변기 수
    var maleChildClosetCnt: String = "-", // 남성 어린이 대변기 수
    var maleChildUrinalCnt: String = "-", // 남성 어린이 소변기 수

    var femaleClosetCnt: String = "-", // 여성 대변기 수
    var femaleChildClosetCnt: String = "-", // 여성 장애인 대변기 수
    var femaleDspsnClosetCnt: String = "-", // 여성 어린이 대변기 수
) : Serializable {
    constructor(
        toiletNm: String,
        lnmAdres: String,
        photo: List<String>?,
    ) : this(toiletNm, lnmAdres, photo, "") {
        this.toiletNm = toiletNm
        this.lnmAdres = lnmAdres
        this.photo = photo
    }
}