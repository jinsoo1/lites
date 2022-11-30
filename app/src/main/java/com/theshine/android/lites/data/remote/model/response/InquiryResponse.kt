package com.theshine.android.lites.data.remote.model.response

data class InquiryResponse(
    val inquiryToken: String,
    val title: String,
    val content: String,
    val createdAt: String,
    val answer: String?,
    val isAnswer : Int
)