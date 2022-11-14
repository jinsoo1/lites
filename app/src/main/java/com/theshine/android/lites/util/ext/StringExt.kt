package com.theshine.android.lites.util.ext

import java.text.SimpleDateFormat
import java.util.*

object StringExt {
    private val shortDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
    private val longDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.KOREA)


    fun showDateAsShort(origin: String?): String {
        return shortDateFormat.format(origin?.let { longDateFormat.parse(it) } ?: return "")
    }

}