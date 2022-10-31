package com.theshine.android.lites.data.common.model

data class SharedInfoData(
    val contentsToken : String,
    val profile : String,
    val name : String,
    val createdAt : String,
    val image : String,
    val title : String,
    val contents : String,
    val likeCounts : Int,
    val commentCounts : Int,
    val viewCounts : Int

    )