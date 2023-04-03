package com.jake5113.malddongkt.main.toilet

data class ToiletItem(
    val lnmAdres: String,
    val photo: List<String>,
    val toiletNm: String,
) {
    constructor(
        dataCd: String,
        diaperExhgTablYn: String,
        emdNm: String,
        emgncBellInstlYn: String,
        etcCn: String,
        femaleChildClosetCnt: String,
        femaleClosetCnt: String,
        femaleDspsnClosetCnt: String,
        filthPrcsMthdNm: String,
        laCrdnt: String,
        lnmAdres: String,
        loCrdnt: String,
        maleChildClosetCnt: String,
        maleChildUrinalCnt: String,
        maleClosetCnt: String,
        maleDspsnClosetCnt: String,
        maleDspsnUrinalCnt: String,
        maleUrinalCnt: String,
        mngrInsttNm: String,
        mwmnCmnuseToiletYn: String,
        opnTimeInfo: String,
        photo: List<String>,
        photoYn: String,
        regDt: String,
        rnAdres: String,
        seNm: String,
        telno: String,
        toiletEntrncCctvInstlYn: String,
        toiletInstlPlacePttnNm: String,
        toiletNm: String,
        toiletPosesnSeNm: String
    ) : this(lnmAdres, photo, toiletNm){

    }
}