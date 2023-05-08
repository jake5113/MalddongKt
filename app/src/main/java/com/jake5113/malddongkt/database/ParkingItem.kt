package com.jake5113.malddongkt.database


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import ted.gun0912.clustering.clustering.TedClusterItem
import ted.gun0912.clustering.geometry.TedLatLng

data class ParkingResponse(
    val currentCount: String,
    val data: MutableList<ParkingItem>
)

data class ParkingItem(
    @SerializedName("주차장명")
    var name: String,
    @SerializedName("주차장지번주소")
    var lnmAdres: String,

    var isFavorite: Boolean = false, // 좋아요 유무

    @SerializedName("연락처")
    var phoneNo: String ="",
    @SerializedName("요금정보")
    var price: String="",
    @SerializedName("위도")
    var latitude: String="",
    @SerializedName("경도")
    var longitude: String = "",
    @SerializedName("지역중심좌표(X좌표)")
    var x: String = "",
    @SerializedName("지역중심좌표(Y좌표)")
    var y: String = "",
    @SerializedName("주차구획수")
    var enableNum: String = "",
    @SerializedName("주차장관리번호")
    var managementNum: String = "",
    @SerializedName("주차장구분")
    var isPublic: String = "",
    @SerializedName("주차장도로명주소")
    var rnAdres: String = "",
    @SerializedName("주차장유형")
    var type: String = "",
    @SerializedName("지역구분")
    var region: String = "",
    @SerializedName("지역구분_sub")
    var regionSub: String = "",
    @SerializedName("지역코드")
    var regionCode: String = "",
    @SerializedName("관리기관명")
    var manageName: String = "",
    @SerializedName("수정일자")
    var modifyDate: String = "",
    @SerializedName("운영요일")
    var operatingDays: String = "",
    @SerializedName("평일운영시작시각")
    var startWeekday: String = "",
    @SerializedName("평일운영종료시각")
    var endWeekday: String = "",
    @SerializedName("토요일운영시작시각")
    var satStart: String = "",
    @SerializedName("토요일운영종료시각")
    var satEnd: String = "",
    @SerializedName("공휴일운영시작시각")
    var holidayStart: String = "",
    @SerializedName("공휴일운영종료시각")
    var holidayEnd: String = "",
) : Parcelable, TedClusterItem {
    constructor(parcel: Parcel) : this(
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readByte() != 0.toByte(),
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: ""
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(lnmAdres)
        parcel.writeByte(if (isFavorite) 1 else 0)
        parcel.writeString(phoneNo)
        parcel.writeString(price)
        parcel.writeString(latitude)
        parcel.writeString(longitude)
        parcel.writeString(x)
        parcel.writeString(y)
        parcel.writeString(enableNum)
        parcel.writeString(managementNum)
        parcel.writeString(isPublic)
        parcel.writeString(rnAdres)
        parcel.writeString(type)
        parcel.writeString(region)
        parcel.writeString(regionSub)
        parcel.writeString(regionCode)
        parcel.writeString(manageName)
        parcel.writeString(modifyDate)
        parcel.writeString(operatingDays)
        parcel.writeString(startWeekday)
        parcel.writeString(endWeekday)
        parcel.writeString(satStart)
        parcel.writeString(satEnd)
        parcel.writeString(holidayStart)
        parcel.writeString(holidayEnd)
    }
    override fun describeContents(): Int = 0
    companion object CREATOR : Parcelable.Creator<ParkingItem> {
        override fun createFromParcel(parcel: Parcel): ParkingItem = ParkingItem(parcel)
        override fun newArray(size: Int): Array<ParkingItem?> = arrayOfNulls(size)
    }

    constructor(name: String, lnmAdres: String, isFavorite: Boolean) : this(
        name, lnmAdres, isFavorite, ""
    ) {
        this.name = name
        this.lnmAdres = lnmAdres
        this.isFavorite = isFavorite
    }

    override fun getTedLatLng(): TedLatLng = TedLatLng(latitude.toDouble(), longitude.toDouble())
}
