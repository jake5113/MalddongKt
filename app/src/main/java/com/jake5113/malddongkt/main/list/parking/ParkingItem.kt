package com.jake5113.malddongkt.main.list.parking


import com.google.gson.annotations.SerializedName

data class ParkingItem(
    @SerializedName("경도")
    var 경도: String,
    @SerializedName("공휴일운영시작시각")
    var 공휴일운영시작시각: String,
    @SerializedName("공휴일운영종료시각")
    var 공휴일운영종료시각: String,
    @SerializedName("관리기관명")
    var 관리기관명: String,
    @SerializedName("수정일자")
    var 수정일자: String,
    @SerializedName("연락처")
    var phoneNo: String,
    @SerializedName("요금정보")
    var 요금정보: String,
    @SerializedName("운영요일")
    var 운영요일: String,
    @SerializedName("위도")
    var 위도: String,
    @SerializedName("주차구획수")
    var 주차구획수: String,
    @SerializedName("주차장관리번호")
    var 주차장관리번호: String,
    @SerializedName("주차장구분")
    var 주차장구분: String,
    @SerializedName("주차장도로명주소")
    var 주차장도로명주소: String,
    @SerializedName("주차장명")
    var 주차장명: String,
    @SerializedName("주차장유형")
    var 주차장유형: String,
    @SerializedName("주차장지번주소")
    var 주차장지번주소: String,
    @SerializedName("지역구분")
    var 지역구분: String,
    @SerializedName("지역구분_sub")
    var 지역구분Sub: String,
    @SerializedName("지역중심좌표(X좌표)")
    var 지역중심좌표X좌표: String,
    @SerializedName("지역중심좌표(Y좌표)")
    var 지역중심좌표Y좌표: String,
    @SerializedName("지역코드")
    var 지역코드: String,
    @SerializedName("토요일운영시작시각")
    var 토요일운영시작시각: String,
    @SerializedName("토요일운영종료시각")
    var 토요일운영종료시각: String,
    @SerializedName("평일운영시작시각")
    var 평일운영시작시각: String,
    @SerializedName("평일운영종료시각")
    var 평일운영종료시각: String
)
