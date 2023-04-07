package com.jake5113.malddongkt.main.list.parking


import com.google.gson.annotations.SerializedName

data class ParkingItem(
    @SerializedName("주차장명")
    var name: String,
    @SerializedName("주차장지번주소")
    var lnmAdres: String,
    @SerializedName("연락처")
    var phoneNo: String ="",
    @SerializedName("요금정보")
    var price: String="",
    @SerializedName("위도")
    var latitude: String="",
    @SerializedName("경도")
    var longitude: String="",
    @SerializedName("지역중심좌표(X좌표)")
    var x: String="",
    @SerializedName("지역중심좌표(Y좌표)")
    var y: String="",
    @SerializedName("주차구획수")
    var enableNum: String="",
    @SerializedName("주차장관리번호")
    var managementNum : String="",
    @SerializedName("주차장구분")
    var isPublic: String="",
    @SerializedName("주차장도로명주소")
    var rnAdres: String="",
    @SerializedName("주차장유형")
    var type: String="",
    @SerializedName("지역구분")
    var region: String="",
    @SerializedName("지역구분_sub")
    var regionSub: String="",
    @SerializedName("지역코드")
    var regionCode: String="",
    @SerializedName("관리기관명")
    var manageName: String="",
    @SerializedName("수정일자")
    var modifyDate: String="",
    @SerializedName("운영요일")
    var operatingDays: String="",
    @SerializedName("평일운영시작시각")
    var startWeekday : String="",
    @SerializedName("평일운영종료시각")
    var endWeekday: String="",
    @SerializedName("토요일운영시작시각")
    var satStart: String="",
    @SerializedName("토요일운영종료시각")
    var satEnd: String="",
    @SerializedName("공휴일운영시작시각")
    var holidayStart: String="",
    @SerializedName("공휴일운영종료시각")
    var holidayEnd: String="",
){
    constructor(name: String, lnmAdres: String): this(name, lnmAdres, ""){
        this.name = name
        this.lnmAdres = lnmAdres
    }
}
