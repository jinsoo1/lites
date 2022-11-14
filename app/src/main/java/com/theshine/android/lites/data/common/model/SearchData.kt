package com.theshine.android.lites.data.common.model

import java.io.Serializable

data class SearchData(
    val id : String, //장소 ID
    val place_name : String, //장소명, 업체명
    val category_name : String, //카테고리 이름
    val category_group_code : String, // 중요 카테고리만 그룹핑한 카테고리 그룹 코드
    val category_group_name : String, // 중요 카테고리만 그룹핑한 카테고리 그룹명
    val phone : String, // 전화번호
    val address_name : String, // 전체 지번 주소
    val road_address_name : String, // 전체 도로명 주소
    val x : String, // X 좌표값, 경위도인 경우 longitude (경도)
    val y : String, // Y 좌표값, 경위도인 경우 latitude(위도)
    val place_url :	String, //장소 상세페이지 URL
    val distance : String // 중심좌표까지의 거리 (단, x,y 파라미터를 준 경우에만 존재)
) : Serializable