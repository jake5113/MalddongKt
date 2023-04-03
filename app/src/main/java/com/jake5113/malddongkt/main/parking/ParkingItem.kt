package com.jake5113.malddongkt.main.parking


import com.google.gson.annotations.SerializedName

data class ParkingItem(
    @SerializedName("경도")
    val 경도: String,
    @SerializedName("공휴일운영시작시각")
    val 공휴일운영시작시각: String,
    @SerializedName("공휴일운영종료시각")
    val 공휴일운영종료시각: String,
    @SerializedName("관리기관명")
    val 관리기관명: String,
    @SerializedName("수정일자")
    val 수정일자: String,
    @SerializedName("연락처")
    val 연락처: String,
    @SerializedName("요금정보")
    val 요금정보: String,
    @SerializedName("운영요일")
    val 운영요일: String,
    @SerializedName("위도")
    val 위도: String,
    @SerializedName("주차구획수")
    val 주차구획수: String,
    @SerializedName("주차장관리번호")
    val 주차장관리번호: String,
    @SerializedName("주차장구분")
    val 주차장구분: String,
    @SerializedName("주차장도로명주소")
    val 주차장도로명주소: String,
    @SerializedName("주차장명")
    val 주차장명: String,
    @SerializedName("주차장유형")
    val 주차장유형: String,
    @SerializedName("주차장지번주소")
    val 주차장지번주소: String,
    @SerializedName("지역구분")
    val 지역구분: String,
    @SerializedName("지역구분_sub")
    val 지역구분Sub: String,
    @SerializedName("지역중심좌표(X좌표)")
    val 지역중심좌표X좌표: String,
    @SerializedName("지역중심좌표(Y좌표)")
    val 지역중심좌표Y좌표: String,
    @SerializedName("지역코드")
    val 지역코드: String,
    @SerializedName("토요일운영시작시각")
    val 토요일운영시작시각: String,
    @SerializedName("토요일운영종료시각")
    val 토요일운영종료시각: String,
    @SerializedName("평일운영시작시각")
    val 평일운영시작시각: String,
    @SerializedName("평일운영종료시각")
    val 평일운영종료시각: String
)