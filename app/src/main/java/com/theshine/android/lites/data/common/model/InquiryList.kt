package com.theshine.android.lites.data.common.model

data class InquiryList(
    val inquiryToken: String,
    val title: String,
    val content: String,
    val createdAt: String,
    val answer: String?,
    val isAnswer : Boolean

)